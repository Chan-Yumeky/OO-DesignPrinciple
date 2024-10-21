package com.example;

import okhttp3.Response;
import java.io.IOException;

public interface ResponseParser {
    String extractOrderId(Response response) throws IOException;
    String extractRefundId(Response response) throws IOException;
}
