package com.thtf.common.core.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ---------------------------
 * 通用响应对象
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;
    private Long timestamp;

    public ResponseResult() {
    }

    public ResponseResult(ResponseCode responseCode){
        this.code = responseCode.code();
        this.message = responseCode.message();
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseResult(int code, String msg){
        this.code = code;
        this.message = msg;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseResult(ResponseCode responseCode, T data){
        this.code = responseCode.code();
        this.message = responseCode.message();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static <T> ResponseResult SUCCESS(T data){
        return new ResponseResult(CommonCode.SUCCESS, data);
    }

    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }
    public static ResponseResult SERVER_ERROR(){
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
