package com.neusoft.xlm.controller;

import com.neusoft.xlm.entity.Movies;
import com.neusoft.xlm.service.MovieDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class MovieDataController {
    @Resource
    private MovieDataService movieDataService;

    /**
     * 跳转到搜索页面
     * @return
     */
    @GetMapping("/search")
    public String toSearch() {
        return "search";
    }

    /**
     * 通过电影名查询
     * @param searchIpt
     * @param model
     * @return
     */
    @PostMapping("/searchMovieByName")
    public String searchMovieByName(String searchIpt, Model model) {
        List<Movies> moviesList = movieDataService.queryByName(searchIpt);
        for (int i = 0; i < moviesList.size(); i++) {
            List list = Arrays.asList(moviesList.get(i).getImglist().split(","));
            moviesList.get(i).setImageList(list);
        }
        model.addAttribute("moviesList", moviesList);
        return "search";
    }

    /**
     * 跳转到电影表格页面
     * @param model
     * @return
     */
    @GetMapping("/tables")
    public String toTables(Model model) {
        List<Movies> moviesList = movieDataService.queryMovie();
        for (int i = 0; i < moviesList.size(); i++) {
            List list = Arrays.asList(moviesList.get(i).getImglist().split(","));
            moviesList.get(i).setImageList(list);
        }
        model.addAttribute("moviesList", moviesList);
        return "movieDataTables";
    }


}
