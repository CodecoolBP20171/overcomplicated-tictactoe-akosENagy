package com.codecool.aiservice;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static spark.Spark.get;
import static spark.Spark.port;

public class AiService {

    private static final String API_URL = "http://tttapi.herokuapp.com/api/v1/%s/O";

    public void run() {
        port(60004);

        get("/aimove", (req, res) -> getAiMove(req.queryParams("gamestate")));
    }

    private String getAiMove(String gamestate) throws IOException {

        URL url = new URL(String.format(API_URL, gamestate));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String responseString = in.readLine();

        JSONObject responseJSON = new JSONObject(responseString);

        return responseJSON.get("recommendation").toString();
    }
}
