package com.szf.common;

import lombok.Data;

@Data
public class JsonResult {

    private int code = 1;

    private String status = SUCCESS_STATUS;

    private String message = SUCCESS_MESSAGE;

    public static String ERROR_STATUS = "error";

    public static String SUCCESS_STATUS = "success";

    public static String NOT_LOGGED_STATUS = "notlogged";

    public static String ERROR_MESSAGE = "失败了！";

    public static String SUCCESS_MESSAGE = "成功了！";

    public static String NOT_LOGGED_MESSAGE = "未登陆！";

    public Object data;

    public JsonResult() {

    }

    public void toSuccess() {
        this.message = SUCCESS_MESSAGE;
        this.status = SUCCESS_STATUS;
    }

    public JsonResult(String status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public JsonResult(String status, String message, Object data) {
        this.setStatus(status);
        this.setMessage(message);
        this.setData(data);
    }

    public JsonResult setJsonResult(JsonResult jsonResult, String status, String message,Object data ) {
        jsonResult.setStatus(status);
        jsonResult.setMessage(message);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult getErrorResult() {
        return new JsonResult(ERROR_STATUS, ERROR_MESSAGE);
    }

    public static JsonResult getErrorResult(String message) {
        return new JsonResult(ERROR_STATUS, message);
    }

    public static JsonResult getSuccessResult() {
        return new JsonResult(SUCCESS_STATUS, SUCCESS_MESSAGE);
    }

    public static JsonResult getSuccessResult(Object data) {
        return new JsonResult(SUCCESS_STATUS, SUCCESS_MESSAGE, data);
    }

    public static JsonResult getNotloggedResult() {
        return new JsonResult(NOT_LOGGED_STATUS, NOT_LOGGED_MESSAGE);
    }

    public static JsonResult getNotloggedResult(String message) {
        return new JsonResult(NOT_LOGGED_STATUS, message);
    }

}
