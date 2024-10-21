package com.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OkHttpClientImp implements HttpClient {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public Response sendRequest(Request request) throws IOException {
        return client.newCall(request).execute();
    }
}
