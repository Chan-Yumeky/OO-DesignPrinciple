package com.example;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class Client {
    private static final String BASE_URL = "http://121.36.106.217:9005/wechat/";
    private String token;
    private String orderId;
    private String refundId;

    private final HttpClient httpClient;  // 使用接口而不是具体实现
    private final ResponseParser responseParser;  // 使用接口而不是具体实现

    public Client(HttpClient httpClient, ResponseParser responseParser) {  // 构造器依赖注入
        this.httpClient = httpClient;
        this.responseParser = responseParser;
    }

    public String getOrderID() {
        return orderId;
    }

    public String getRefundId() {
        return refundId;
    }

    // 1. 查询支付接口是否可用
    public boolean isAvailable() throws IOException {
        if (token == null){
            Request request = new Request.Builder()
                    .url(BASE_URL + "isRunning")
                    .build();

            try (Response response = httpClient.sendRequest(request)) {  // 使用 HttpClient 接口发送请求
                String jsonData = response.body().string();
                JSONObject json = new JSONObject(jsonData);
                JSONObject data = json.getJSONObject("data");
                token = data.optString("token", null);
            }
        }
        return token != null;
    }

    // 2. 付款
    public void pay(String mchid, String appid, double amount) throws IOException {
        if (token == null) throw new IllegalStateException("Token 为空，无法进行支付。");

        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("mchid", mchid)
                .add("content", "没什么内容但是请求参数列表有不得不写TvT")
                .add("appid", appid) // 学号
                .add("amount", String.valueOf(amount))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "transaction/pay")
                .post(body)
                .build();

        try (Response response = httpClient.sendRequest(request)) {  // 使用 HttpClient接口发送请求
            orderId = responseParser.extractOrderId(response);  // 使用 JsonResponseParser获取orderId
        }
    }

    // 3. 退款
    public void refunds(String mchid, String appid, double amount) throws IOException {
        if (token == null) throw new IllegalStateException("Token 为空，无法进行退款。");

        RequestBody body = new FormBody.Builder()
                .add("token", token)
                .add("mchid", mchid)
                .add("content", "没什么内容但是请求参数列表有不得不写TvT")
                .add("appid", appid)
                .add("amount", String.valueOf(amount))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "transaction/refunds")
                .post(body)
                .build();

        try (Response response = httpClient.sendRequest(request)) {  // 使用HttpClient接口发送请求
            refundId = responseParser.extractRefundId(response);  // 使用 JsonResponseParser获取orderId
        }
    }

    // 4. 查询订单
    public String query(String orderid) throws IOException {
        if (token == null) throw new IllegalStateException("Token 为空，无法查询订单。");

        HttpUrl url = HttpUrl.parse(BASE_URL + "order/query").newBuilder()
                .addQueryParameter("token", token)
                .addQueryParameter("orderID", orderid)
                .build();

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.sendRequest(request)) {  // 使用 HttpClient 接口发送请求
            return response.body().string();
        }
    }

    // 5. 查询退款单
    public String queryRefunds(String refundid) throws IOException {
        if (token == null) throw new IllegalStateException("Token 为空，无法查询退款单。");

        HttpUrl url = HttpUrl.parse(BASE_URL + "order/queryRefunds").newBuilder()
                .addQueryParameter("token", token)
                .addQueryParameter("orderID", refundid)
                .build();

        Request request = new Request.Builder().url(url).build();

        try (Response response = httpClient.sendRequest(request)) {  // 使用 HttpClient 接口发送请求
            return response.body().string();
        }
    }
}
