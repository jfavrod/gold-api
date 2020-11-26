package com.jasonfavrod.gold.entities;

import java.util.ArrayList;
import java.util.List;

public class Response {
    protected int code;
    protected List data;
    protected boolean error;
    protected String message;

    public Response() {
        code = 500;
        data = new ArrayList();
        error = true;
        message = "Default response";
    }

    public Response(List data) {
        this.code = 200;
        this.data = data;
        this.error = false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
