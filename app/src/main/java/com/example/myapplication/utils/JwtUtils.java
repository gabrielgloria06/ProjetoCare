package com.example.myapplication.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class JwtUtils {

    // Método para decodificar o JWT usando a chave pública RSA
    public static Claims decodeJWT(String jwt, String publicKeyBase64) {
        try {
            byte[] publicKeyBytes = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            }

            // Converte a chave pública para RSAPublicKey
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);

            return Jwts.parserBuilder()
                    .setSigningKey(rsaPublicKey) // Usa a chave pública RSA aqui
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}