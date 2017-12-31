package com.codecool.enterprise.overcomplicated.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Component
public class ServiceHandler {

    private static final String FUNFACTAPI_URL = "http://localhost:60001/random";

    public String getFunfact() {

        URL url;
        HttpURLConnection con;
        BufferedReader in;
        String responseString;
        try {
            url = new URL(FUNFACTAPI_URL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            responseString = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(responseString);
        return responseString;
    }


}
