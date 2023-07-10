package com.neusoft.xlm.controller;

import com.neusoft.xlm.entity.PwdUpdateRes;
import com.neusoft.xlm.entity.users.User;
import com.neusoft.xlm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-03-22 16:28:52
 */
@Controller
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    HttpSession session = null;

    /**
     * 登录主页
     *
     * @return 查询结果
     */
    @PostMapping("/index")
    public String queryByEmail(String email, String password, HttpServletRequest request) {
        session = request.getSession();
        User user = userService.queryByEmail(email);
        if (user.getPassword().equals(password)) {
            session.setAttribute("email", email);
            return "index";
        } else {
            session.setAttribute("msg", "密码错误");
            return "errorPage";
        }
    }

    @PostMapping("/loginByCode")
    public String queryByEmailCode(String email, HttpServletRequest request) {
        session = request.getSession();
        User user = userService.queryByEmail(email);
        if(user==null){
            session.setAttribute("msg", "用户不存在");
            return "errorPage";
        }
        else {
            if (user.getPassword() == null || user.getPassword().length() == 0) {
                session.setAttribute("msg", "用户不存在");
                return "errorPage";
            } else {
                session.setAttribute("email", email);
                return "index";
            }
        }
    }

    /**
     * 注册用户
     *
     * @return 注册用户
     */
    @PostMapping("/register/{email}/{password}")
    @ResponseBody
    public PwdUpdateRes register(@PathVariable String email, @PathVariable String password) {
        User user = new User(email, password);
        PwdUpdateRes pwdUpdateRes = new PwdUpdateRes();
        int res = userService.insert(user);
        if (res > 0) {
            pwdUpdateRes.setCode("success");
            pwdUpdateRes.setMsg("注册成功");
        } else {
            pwdUpdateRes.setCode("failed");
            pwdUpdateRes.setMsg("注册失败");
        }
        return pwdUpdateRes;
    }

    /**
     * 修改密码
     *
     * @return 修改密码
     */
    @PostMapping("/editPassword/{email}/{password}")
    @ResponseBody
    public PwdUpdateRes edit(@PathVariable String email, @PathVariable String password) {
        int res = userService.update(email, password);
        PwdUpdateRes pwdUpdateRes = new PwdUpdateRes();
        if (res > 0) {
            pwdUpdateRes.setCode("success");
            pwdUpdateRes.setMsg("修改成功");

        } else {
            pwdUpdateRes.setCode("failed");
            pwdUpdateRes.setMsg("修改失败");

        }
        return pwdUpdateRes;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @GetMapping("/loginOut")
    public String LoginOut(HttpServletRequest request){
        request.getSession().removeAttribute("email");
        return "login";
    }


}

