package com.example;

import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public interface HttpClient {
    Response sendRequest(Request request) throws IOException;
}
