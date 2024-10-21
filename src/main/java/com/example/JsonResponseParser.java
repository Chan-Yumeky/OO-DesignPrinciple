package com.example;

import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;

public class JsonResponseParser implements ResponseParser{

    public String extractOrderId(Response response) throws IOException {
        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        JSONObject data = json.getJSONObject("data");
        return data.optString("orderid", null);
    }

    public String extractRefundId(Response response) throws IOException {
        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        JSONObject data = json.getJSONObject("data");
        return data.optString("orderid", null);
    }
}
