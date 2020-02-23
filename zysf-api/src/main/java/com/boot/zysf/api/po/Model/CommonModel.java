package com.boot.zysf.api.po.Model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CommonModel<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonModel() {}

    public CommonModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonModel(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonModel{"
                + "code="
                + code
                + ", message='"
                + message
                + '\''
                + ", data="
                + data
                + '}';
    }
}
