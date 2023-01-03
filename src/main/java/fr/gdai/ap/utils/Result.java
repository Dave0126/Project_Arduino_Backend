package fr.gdai.ap.utils;

public class Result<T> {
    private Integer resultCode;
    private T data;
    private String msg;

    public Result(Integer resultCode, T data, String msg) {
        this.resultCode = resultCode;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer resultCode, T data) {
        this.resultCode = resultCode;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}