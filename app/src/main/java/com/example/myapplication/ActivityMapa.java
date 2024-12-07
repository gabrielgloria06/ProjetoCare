package com.example.myapplication;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

public class ActivityMapa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AlertDialog alerta;
    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mapa, container, false);

        // Configura o Spinner para seleção de filtro
        Spinner spinnerFiltro = view.findViewById(R.id.spinner_filtro);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipos_locais, R.layout.list_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerFiltro.setAdapter(adapter);

        // Configura o mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Configura o listener do Spinner para aplicar o filtro
        spinnerFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mMap != null) {
                    mMap.clear(); // Limpa todos os marcadores
                    buscarHospitaisProximos(mMap, (String) parent.getItemAtPosition(position)); // Chama a função com o filtro selecionado
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ImageView info = view.findViewById(R.id.imgInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInfo();
            }
        });

        return view;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        pegarPermissao();
        updateLocationUi();
        pegarLocalizacaoDispositivo(); // Obtém a localização do dispositivo e move a câmera para lá

        // Chama a função inicial para buscar hospitais, ou o tipo padrão do filtro
        buscarHospitaisProximos(mMap, "Hospital");
    }

    public void pegarPermissao() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            }
        }

        updateLocationUi();
    }

    private void updateLocationUi() {
        if (mMap == null) {
            return;
        }

        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                pegarPermissao();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void pegarLocalizacaoDispositivo() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            Location currentLocation = task.getResult();
                            if (currentLocation != null) {
                                LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                                mMap.addCircle(new CircleOptions().center(currentLatLng).visible(true));
                            }
                        } else {
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-34, 151), 15));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void buscarHospitaisProximos(GoogleMap googleMap, String filtro) {
        if (!Places.isInitialized()) {
            Places.initialize(getContext(), "AIzaSyBVnFt4Ao4NfzoyxYTjSCZe4DO4ujZbaR4");
        }
        PlacesClient placesClient = Places.createClient(getContext());

        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(-3.158254, -60.131372),
                new LatLng(-3.037994, -59.854956)
        );

        String query = filtro.toLowerCase();

        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setLocationBias(bounds)
                .build();

        placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener(response -> {
                    for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                        String placeId = prediction.getPlaceId();

                        List<Place.Field> campos = Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG);
                        FetchPlaceRequest fetchRequest = FetchPlaceRequest.newInstance(placeId, campos);

                        placesClient.fetchPlace(fetchRequest)
                                .addOnSuccessListener(fetchResponse -> {
                                    Place lugar = fetchResponse.getPlace();
                                    LatLng coordenadas = lugar.getLatLng();

                                    if (coordenadas != null) {
                                        int iconResource = R.drawable.hospital; // Default
                                        if (query.equals("clínica")) {
                                            iconResource = R.drawable.clinica;
                                        } else if (query.equals("centro médico")) {
                                            iconResource = R.drawable.centro_medico;
                                        }

                                        MarkerOptions marcador = new MarkerOptions()
                                                .icon(BitmapDescriptorFactory.fromResource(iconResource))
                                                .position(coordenadas)
                                                .title(lugar.getName());
                                        googleMap.addMarker(marcador);
                                    }
                                })
                                .addOnFailureListener(Throwable::printStackTrace);
                    }
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }

    public void mostrarInfo() {
        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.caixa_info, null);

        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TransparentBackgroundTheme);
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }
}