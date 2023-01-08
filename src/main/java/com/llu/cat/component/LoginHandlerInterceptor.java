package com.llu.cat.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llu.cat.entity.SignAdmin;
import com.llu.cat.entity.SignUser;
import com.llu.cat.services.SignAdminService;
import com.llu.cat.services.SignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @create 2022-02-26 10:46
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //未登录
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }else {
            return  true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
