package com.szf.config;

import com.szf.entity.UserLog;
import com.szf.enums.ActionType;
import com.szf.repository.UserLogRepository;
import com.szf.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        final String requestUri = httpServletRequest.getRequestURI();
        Object username = session.getAttribute("username");
        String method = httpServletRequest.getMethod();
        if(username != null && StringUtils.isNotEmpty(username.toString())){
            if ("POST".equalsIgnoreCase(method) || "DELETE".equals(method)) {
                UserLog userLog = new UserLog();
                if ("DELETE".equals(method)) {
                    userLog.setActionDesc(username.toString() + "请求了删除地址：" + requestUri);
                    userLog.setActionType(ActionType.DEL);
                } else {
                    if (requestUri.contains("update")) {
                        userLog.setActionType(ActionType.UPDATE);
                        userLog.setActionDesc(username.toString() + "请求了修改地址：" + requestUri);
                    } else {
                        userLog.setActionType(ActionType.ADD);
                        userLog.setActionDesc(username.toString() + "请求了添加地址：" + requestUri);
                    }
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                userLog.setActionTime(dtf.format(LocalDateTime.now()));
                userLog.setInternetIp(getRequestIp(httpServletRequest));
                userLog.setUsername(username.toString());
                new Thread(() -> {
                    userLogRepository.save(userLog);
                }).start();
            }

            return true;
        } else{
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin/login.html");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    private String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}