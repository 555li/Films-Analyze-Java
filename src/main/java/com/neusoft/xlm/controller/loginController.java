package com.neusoft.xlm.controller;

import com.neusoft.xlm.service.MoviesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class loginController {
    @Resource
    private MoviesService moviesService;

    /**
     * 如果是请求路径是"/"，则跳转到登录页面
     * @param request
     * @return
     */
    @GetMapping("/")
    public String previous(HttpServletRequest request) {
        return "login";
    }

    /**
     * 如果是请求路径是"/login"，则跳转到登录页面
     * @param request
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        return "login";
    }

    /**
     * 如果是请求路径是"/adminLogin"，则跳转到管理员登录页面
     * @param request
     * @return
     */
    @GetMapping("/adminLogin")
    public String adminLogin(HttpServletRequest request) {
        return "adminLogin";
    }

    /**
     * 跳转到验证码登录页面
     * @return
     */
    @GetMapping("/loginCode")
    public String loginCode() {
        return "loginCode";
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @GetMapping("/registry")
    public String registry() {
        return "register";
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @GetMapping("/updatePassword")
    public String updatePassword() {
        return "updatePassword";
    }

    /**
     * 跳转到免责说明页面
     * @return
     */
    @GetMapping("/disclaimers")
    public String disclaimers() {
        return "disclaimers";
    }
    /**
     * 跳转到隐私政策页面
     * @return
     */
    @GetMapping("/privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy";
    }

}

