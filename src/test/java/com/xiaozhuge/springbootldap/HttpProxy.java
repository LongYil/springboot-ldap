package com.xiaozhuge.springbootldap;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class HttpProxy {

    private static final String TARGET_HOST = "10.10.101.140"; // kubectl proxy主机
    private static final int TARGET_PORT = 8001; // kubectl proxy端口

    public static void main(String[] args) throws IOException {
        int proxyPort = 9099; // 自定义代理服务器监听的端口
        HttpServer server = HttpServer.create(new InetSocketAddress(proxyPort), 0);
        server.createContext("/", new ProxyHandler());
        server.setExecutor(null); // 使用默认的线程池
        server.start();
        System.out.println("Proxy server is running on port " + proxyPort);
    }

    static class ProxyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 读取请求的请求体
            InputStream requestBody = exchange.getRequestBody();
            byte[] requestBodyBytes = requestBody.readAllBytes();

            System.out.println("Received HTTP Request:");
            System.out.println("Method: " + exchange.getRequestMethod());
            System.out.println("URL: " + exchange.getRequestURI());
            System.out.println("Headers: " + exchange.getRequestHeaders());
            System.out.println("Body: " + new String(requestBodyBytes));

            // 转发请求到kubectl proxy
            URL targetUrl = new URL("http://" + TARGET_HOST + ":" + TARGET_PORT + exchange.getRequestURI());
            HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection(Proxy.NO_PROXY);
            connection.setRequestMethod(exchange.getRequestMethod());
            connection.setRequestProperty("Content-Type", "application/json"); // 设置请求头
            connection.setDoOutput(true);

            // 写入请求体
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBodyBytes);
            outputStream.flush();
            outputStream.close();

            // 获取kubectl proxy返回的响应
            int responseCode = connection.getResponseCode();
            InputStream responseStream = responseCode == 200 ? connection.getInputStream() : connection.getErrorStream();
            byte[] responseBytes = responseStream.readAllBytes();
            responseStream.close();

            // 设置响应头
            exchange.getResponseHeaders().putAll(connection.getHeaderFields());
            exchange.sendResponseHeaders(responseCode, responseBytes.length);

            // 返回响应体
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(responseBytes);
            responseBody.close();
        }
    }
}
