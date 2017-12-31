package com.codecool.funfactservice;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.get;
import static spark.Spark.port;


public class FunfactService {

    private static final String API_URL = "https://api.chucknorris.io/jokes/random";

    public void run() {
        port(60001);

        get("/random", (req, res) -> getFunfact());
    }

    private String getFunfact() throws IOException {

        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String responseString = in.readLine();

        JSONObject funJSON = new JSONObject(responseString);
        String funfactString = (String) funJSON.get("value");

        System.out.println(funfactString);
        return funfactString;
    }
}
