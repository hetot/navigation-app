package com.dsr.navigationapp.utils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigReader {
    private static ConfigReader instance = null;
    private JSONObject configJson = null;

    private ConfigReader() {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            StringBuilder responseStrBuilder = new StringBuilder();
            while ((line = br.readLine()) != null) {

                responseStrBuilder.append(line);
            }
            is.close();
            configJson = new JSONObject(responseStrBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getConfigPath() {
        return configJson.getString("root_dir");
    }
}
