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
public class ResponseData {

    private Object wsResponse;
    private String errorCode;
    private String message;
    private Object object;

    public ResponseData(String status, String msg, Object data) {
       this.wsResponse=data;
       this.message=msg;
       this.errorCode=status;
    }


    public Object getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(Object wsResponse) {
        this.wsResponse = wsResponse;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
