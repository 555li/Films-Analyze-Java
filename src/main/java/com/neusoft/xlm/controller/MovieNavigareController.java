package com.neusoft.xlm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 电影数据页面跳转控制层
 */
@Controller
public class MovieNavigareController {
    /**
     * 跳转到事件分析页面
     *
     * @return
     */
    @GetMapping("time_t")
    public String toTime_t() {
        return "time_t";
    }

    /**
     * 跳转到类型分析页面
     *
     * @return
     */
    @GetMapping("type_t")
    public String toType_t() {
        return "type_t";
    }

    /**
     * 跳转到评分分析页面
     *
     * @return
     */
    @GetMapping("rate_t")
    public String toRate_t() {
        return "rate_t";
    }

    /**
     * 跳转到地图分析页面
     *
     * @return
     */
    @GetMapping("address_t")
    public String toAddress_t() {
        return "address_t";
    }

    /**
     * 跳转到演员和导演分析页面
     *
     * @return
     */
    @GetMapping("actor_t")
    public String toActor_t() {
        return "actor_t";
    }

    /**
     * 跳转到中外电影分析页面
     *
     * @return
     */
    @GetMapping("cvsf")
    public String tocVsF() {
        return "chinaVsForeign";
    }
}
