package com.neusoft.xlm.service.impl;

import com.neusoft.xlm.dao.MovieDataDao;
import com.neusoft.xlm.entity.Movies;
import com.neusoft.xlm.service.MovieDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieDataServiceImpl implements MovieDataService {
    @Resource
    private MovieDataDao movieDataDao;

    @Override
    public List<Movies> queryByName(String searchIpt) {
        return this.movieDataDao.queryByName(searchIpt);
    }

    @Override
    public List<Movies> queryMovie() {
        return this.movieDataDao.queryMovie();
    }

    @Override
    public int deleteByTitle(String title) {
        return this.movieDataDao.deleteByTitle(title);
    }

}
