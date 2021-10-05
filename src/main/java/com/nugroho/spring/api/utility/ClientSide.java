package com.nugroho.spring.api.utility;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientSide {

    public static String postJsonRequest(URL url, Map<String, String> headers, String body) throws IOException {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.addRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        headers.forEach((key, data) -> {
            con.addRequestProperty(key, data);
        });

        con.setReadTimeout(20);
        var os = con.getOutputStream();
        byte[] input = body.getBytes("utf-8");
        os.write(input, 0, input.length);

        var br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        
        return response.toString();
    }
    
}
