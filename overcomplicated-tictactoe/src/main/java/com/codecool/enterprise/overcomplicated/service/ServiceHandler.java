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
    private static final String COMICAPI_URL = "http://localhost:60002/getcomic";
    private static final String AVATARAPI_URL = "http://localhost:60003/getavatar";

    public String getAvatar(String username) {
        return getResource(AVATARAPI_URL + "?username=" + username);
    }

    public String getFunfact() {
        return getResource(FUNFACTAPI_URL);
    }

    public String getComic() {
        return getResource(COMICAPI_URL);
    }

    private String getResource(String URI) {

        URL url;
        HttpURLConnection con;
        BufferedReader in;
        String responseString;
        try {
            url = new URL(URI);
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
