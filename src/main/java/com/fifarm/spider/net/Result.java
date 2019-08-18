package com.fifarm.spider.net;

public class Result {

    private int responseCode;
    private String response;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Result{" +
                "responseCode=" + responseCode +
                ", response='" + response + '\'' +
                '}';
    }

}
