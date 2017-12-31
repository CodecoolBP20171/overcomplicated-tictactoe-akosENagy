package com.codecool.comicservice;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

import static spark.Spark.get;
import static spark.Spark.port;

public class ComicService {

    private static final String API_URL = "https://xkcd.com/%d/info.0.json";

    public void run() {
        port(60002);

        get("/getcomic", (req, res) -> getRandomComic());
    }

    private String getRandomComic() throws IOException {

        String formattedURL = String.format(API_URL, ThreadLocalRandom.current().nextInt(1, 1930));

        URL url = new URL(formattedURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String responseString = in.readLine();

        JSONObject comicJSON = new JSONObject(responseString);

        return (String) comicJSON.get("img");
    }
}
