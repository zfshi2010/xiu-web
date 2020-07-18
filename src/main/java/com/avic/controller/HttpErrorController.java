package com.avic.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HttpErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @RequestMapping(path  = ERROR_PATH )
    public String error(HttpServletRequest request, HttpServletResponse response){

        return "phone/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
