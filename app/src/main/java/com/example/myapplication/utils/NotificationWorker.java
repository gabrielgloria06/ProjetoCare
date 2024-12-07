package com.example.myapplication.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.App;
import com.example.myapplication.ListaLembretesActivity;
import com.example.myapplication.R;

public class NotificationWorker extends Worker {

    public static final String CHANNEL_ID = "LembreteChannel";
    public static final String LEMBRETE_NAME = "lembrete_name";

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String lembreteName = getInputData().getString(LEMBRETE_NAME);

        createNotificationChannel();
        sendNotification(lembreteName);

        return Result.success();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = getApplicationContext().getSystemService(NotificationManager.class);
            if (manager != null && manager.getNotificationChannel(CHANNEL_ID) == null) {
                CharSequence name = "Lembretes";
                String description = "Notificações de lembretes";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

                // Defina o padrão de vibração (sem som)
                long[] vibrationPattern = {0, 500, 200, 500}; // Pausar, vibrar 500ms, pausar, vibrar 500ms
                channel.setVibrationPattern(vibrationPattern); // Definindo a vibração
                channel.setSound(null, null); // Desativando o som

                manager.createNotificationChannel(channel);
            }
        }
    }

    private void sendNotification(String lembreteName) {
        // Intent para abrir a ListaLembretesActivity ao clicar na notificação
        Intent intent = new Intent(getApplicationContext(), ListaLembretesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // PendingIntent para a notificação
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.logo) // Ícone da notificação
                .setContentTitle("Lembrete")
                .setContentText(lembreteName)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setLargeIcon(BitmapFactory.decodeResource(App.getAppContext().getResources(), R.drawable.logo)) // Ícone grande (opcional)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 500, 200, 500}) // Vibração
                .setSound(null) // Sem som
                .setContentIntent(pendingIntent); // Define o comportamento ao clicar

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify((int) System.currentTimeMillis(), builder.build());
        }
    }
}
