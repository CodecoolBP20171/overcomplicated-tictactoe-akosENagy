package com.codecool.avatarservice;

import java.io.IOException;
import static spark.Spark.get;
import static spark.Spark.port;

public class AvatarService {

    private static final String API_URL = "https://robohash.org/%s.png";

    public void run() {
        port(60003);

        get("/getavatar", (req, res) -> getAvatar(req.queryParams("username")));
    }

    private String getAvatar(String username) throws IOException {

        if (username == null) {
            username = "default";
        }

        return String.format(API_URL, username);
    }
}
