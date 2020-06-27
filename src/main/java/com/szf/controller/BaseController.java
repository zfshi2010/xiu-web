package com.szf.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    public String getCurrentUserName(){
        return request.getSession().getAttribute("username").toString();
    }

    public String getCurrentToken() {
        return request.getHeader("token");
    }
}
