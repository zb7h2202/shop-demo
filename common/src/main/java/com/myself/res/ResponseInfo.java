package com.myself.res;

import java.io.Serializable;

public class ResponseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int stats;
    private String message = "success";
    private Object data;

    public ResponseInfo() {
    }

    public ResponseInfo(String message) {
        this.message = message;
    }

    public static ResponseInfo succ(Object data){
        return (new ResponseInfo()).data(data);
    }

    private ResponseInfo data(Object data){
        this.data = data;
        return this;
    }

    public int getStats() {
        return stats;
    }

    public void setStats(int stats) {
        this.stats = stats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
