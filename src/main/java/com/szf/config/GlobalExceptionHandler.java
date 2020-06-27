package com.szf.config;

import com.szf.common.JsonResult;
import com.szf.exception.NotloggedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (e instanceof NotloggedException) {
            return JsonResult.getNotloggedResult(e.getMessage());
        } else {
            return JsonResult.getErrorResult(e.getMessage());
        }
    }
}
