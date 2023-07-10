package com.neusoft.xlm.entity.stars;

import java.util.List;

public class MovieNameAndStars {
    String title;
    List<MovieCountByStarsDTO> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieCountByStarsDTO> getList() {
        return list;
    }

    public void setList(List<MovieCountByStarsDTO> list) {
        this.list = list;
    }
}
