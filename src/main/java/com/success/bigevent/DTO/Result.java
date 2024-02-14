package com.success.bigevent.DTO;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class Result<T> implements Serializable {

    /**
     * 返回状态码
     */
    private int code;
    /**
     * 返回消息提示
     */
    private String message;
    /**
     * 数据体
     */
    private T data;
    /**
     * 成功标识
     */
    private boolean success;

    public Result() {
    }

    public Result(int code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.success = false;
        return result;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = HttpStatus.OK.value();
        result.message = "ok";
        result.success = true;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = HttpStatus.OK.value();
        result.message = "ok";
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.code = HttpStatus.OK.value();
        result.message = message;
        result.success = true;
        result.data = data;
        return result;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }
}

