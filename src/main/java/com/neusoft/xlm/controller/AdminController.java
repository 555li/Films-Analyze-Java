package com.neusoft.xlm.controller;

import com.neusoft.xlm.entity.users.Admin;
import com.neusoft.xlm.entity.Movies;
import com.neusoft.xlm.service.AdminService;
import com.neusoft.xlm.service.MovieDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Resource
    private MovieDataService movieDataService;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private Process process;
    HttpSession session = null;

    /**
     * 跳转到爬虫页面
     *
     * @return
     */
    @GetMapping("/movieReptile")
    public String movieReptile() {
        return "movieReptile";
    }

    /**
     * 点击爬虫按钮之后，开始爬取
     *
     * @return
     */
    @GetMapping("/crawl")
    public ModelAndView crawl() throws IOException {
        String msg = "";
        if (process == null) {
            try {
                File file = new File("D:\\电影数据可视化分析系统JAVA\\电影数据可视化分析\\src\\main\\resources\\python\\moiveReptile.py");
                if (!file.exists()) {
                    throw new FileNotFoundException("Python脚本文件不存在！");
                }
                ProcessBuilder pb = new ProcessBuilder("python", file.getAbsolutePath());
                process = pb.start();
                process.waitFor();
            } catch (Exception e) {
                msg = "爬虫启动失败：" + e.getMessage();
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                    process = null;
                }
            }
            msg = "爬虫已启动";
        } else {
            msg = "爬虫已经在运行";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        mv.addObject("msg", msg);
        return mv;
    }

    /**
     * 停止爬虫
     *
     * @return
     */
    @GetMapping("/stop")
    public ModelAndView stop() {
        String msg = "";
        if (process != null) {
            process.destroy();
            process = null;
            msg = "爬虫已停止";
        } else {
            msg = "爬虫已经停止";
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("movieReptile");
        mv.addObject("msg", msg);
        return mv;
    }

    /**
     * 管理员登录控制器方法
     *
     * @param account
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/admin")
    public String admin(String account, String password, HttpServletRequest request) {
        session = request.getSession();
        Admin admin = adminService.queryByAccount(account);
        if (admin.getPassword().equals(password)) {
            session.setAttribute("account", account);
            return "adminIndex";
        } else {
            session.setAttribute("msg", "密码错误");
            return "errorPage";
        }
    }

    /**
     * 管理员电影数据表
     *
     * @param model
     * @return
     */
    @GetMapping("/adminTables")
    public String toTables(Model model) {
        List<Movies> moviesList = movieDataService.queryMovie();
        for (int i = 0; i < moviesList.size(); i++) {
            List list = Arrays.asList(moviesList.get(i).getImglist().split(","));
            moviesList.get(i).setImageList(list);
        }
        model.addAttribute("moviesList", moviesList);
        return "adminMovieTables";
    }

    /**
     * 根据电影名称删除电影
     *
     * @param title
     * @param response
     */
    @GetMapping("delete/{title}")
    public void deleteById(@PathVariable String title, HttpServletResponse response) {
        int res = movieDataService.deleteByTitle(title);
        if(res!=0){
            try {
                response.sendRedirect("/adminTables");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
