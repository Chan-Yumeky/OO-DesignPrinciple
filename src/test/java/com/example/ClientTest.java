package com.example;

public class ClientTest {

    public static void main(String[] args) {
        // 创建 HttpClient 和 JsonResponseParser 实例
        HttpClient httpClient = new OkHttpClientImp();
        ResponseParser responseParser = new JsonResponseParser();

        // 注入 Client
        Client client = new Client(httpClient, responseParser);

        // 创建 ClientTest 实例
        ClientTest test = new ClientTest();

        // 调用测试方法
        test.testIsAvailable(client);
        test.testPay(client);
        test.testRefunds(client);
        test.testQueryOrder(client);
        test.testQueryRefunds(client);
    }

    //测试支付接口是否可用
    public void testIsAvailable(Client client) {
        try {
            if (client.isAvailable()) {
                System.out.println("支付接口可用");
            } else {
                System.out.println("支付接口不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试付款功能
    public void testPay(Client client) {
        try {
            if (client.isAvailable()) {
                String appid = "20221120074";  // 替换为你的学号
                double amount = 100.00;  // 设置测试金额

                // 调用支付接口
                client.pay("20170100", appid, amount);

                // 打印订单ID
                System.out.println("支付成功，订单ID: " + client.getOrderID());
            } else {
                System.out.println("支付接口不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试退款功能
    public void testRefunds(Client client) {
        try {
            if (client.isAvailable()) {
                String appid = "20221120074";  // 替换为你的学号
                double amount = 50.00;  // 设置退款金额

                // 调用退款接口
                client.refunds("20170100", appid, amount);

                // 打印退款单ID
                System.out.println("退款成功，退款单ID: " + client.getRefundId());
            } else {
                System.out.println("支付接口不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试查询订单功能
    public void testQueryOrder(Client client) {
        try {
            if (client.isAvailable()) {
                // 从客户端获取订单ID
                String orderid = client.getOrderID();

                // 调用查询订单接口
                if (orderid != null) {
                    String orderInfo = client.query(orderid);
                    System.out.println("订单信息: " + orderInfo);
                } else {
                    System.out.println("没有有效的订单ID");
                }
            } else {
                System.out.println("支付接口不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试查询退款单功能
    public void testQueryRefunds(Client client) {
        try {
            if (client.isAvailable()) {
                // 从客户端获取退款单ID
                String refundid = client.getRefundId();

                // 调用查询退款单接口
                if (refundid != null) {
                    String refundInfo = client.queryRefunds(refundid);
                    System.out.println("退款单信息: " + refundInfo);
                } else {
                    System.out.println("没有有效的退款单ID");
                }
            } else {
                System.out.println("支付接口不可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
