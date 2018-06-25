/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

/**
 *
 * @author Asus
 */
public class RequestData {

    private Object sessionId;
    private String wsCode;
    private String apiKey;
    private Object wsRequest;

    public Object getSessionId() {
        return sessionId;
    }

    public void setSessionId(Object sessionId) {
        this.sessionId = sessionId;
    }

    public String getWsCode() {
        return wsCode;
    }

    public void setWsCode(String wsCode) {
        this.wsCode = wsCode;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Object getWsRequest() {
        return wsRequest;
    }

    public void setWsRequest(Object wsRequest) {
        this.wsRequest = wsRequest;
    }

}
