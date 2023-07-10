package com.neusoft.xlm.service.impl;

import com.neusoft.xlm.dao.MoviesDao;
import com.neusoft.xlm.entity.Movies;
import com.neusoft.xlm.entity.address.MovieCountByAddress;
import com.neusoft.xlm.entity.cast.MovieDataByCast;
import com.neusoft.xlm.entity.country.MovieCountByCountryDTO;
import com.neusoft.xlm.entity.country.MovieRateByCountry;
import com.neusoft.xlm.entity.director.MovieDataByDirector;
import com.neusoft.xlm.entity.lang.MovieCountByLang;
import com.neusoft.xlm.entity.rate.MovieDataByRate;
import com.neusoft.xlm.entity.stars.MovieCountByStars;
import com.neusoft.xlm.entity.utils.PieChartUtilDTO;
import com.neusoft.xlm.entity.year.MovieCountByYear;
import com.neusoft.xlm.entity.year.MovieRateByYear;
import com.neusoft.xlm.service.MoviesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.util.List;

/**
 * (Movies)表服务实现类
 *
 * @author makejava
 * @since 2023-03-22 16:24:45
 */
@Service
public class MoviesServiceImpl implements MoviesService {
    @Resource
    private MoviesDao moviesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Movies queryById(Integer id) {
        return this.moviesDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param movies      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Movies> queryByPage(Movies movies, PageRequest pageRequest) {
        long total = this.moviesDao.count(movies);
        return new PageImpl<>(this.moviesDao.queryAllByLimit(movies, (Pageable) pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param movies 实例对象
     * @return 实例对象
     */
    @Override
    public Movies insert(Movies movies) {
        this.moviesDao.insert(movies);
        return movies;
    }

    /**
     * 修改数据
     *
     * @param movies 实例对象
     * @return 实例对象
     */
    @Override
    public Movies update(Movies movies) {
        this.moviesDao.update(movies);
        return this.queryById(movies.getId());
    }

    @Override
    public int getLength() {
        return this.moviesDao.getLength();
    }

    @Override
    public double getMaxRate() {
        return this.moviesDao.getMaxRate();
    }

    @Override
    public String getMaxCast() {
        return this.moviesDao.getMaxCast();
    }

    @Override
    public String getMaxCountry() {
        return this.moviesDao.getMaxCountry();
    }

    @Override
    public String getMaxLang() {
        return this.moviesDao.getMaxLang();
    }

    @Override
    public int getTypeCount() {
        return this.moviesDao.getTypeCount();
    }

    @Override
    public List<PieChartUtilDTO> getPieChartByMovieCategory() {
        return this.moviesDao.selectCountByCategory();
    }


    @Override
    public List<MovieDataByRate> getScore() {
        return this.moviesDao.selectCountByRate();
    }

    @Override
    public List<MovieCountByYear> getMovieCountByYear() {
        return this.moviesDao.selectMovieCountByYear();
    }

    @Override
    public List<PieChartUtilDTO> getMoiveDataByTime() {
        return this.moviesDao.selectMovieDataByTime();
    }

    @Override
    public MovieCountByStars getMovieRateByName(String title) {
        return this.moviesDao.selectMovieRateByName(title);
    }

    @Override
    public List<MovieRateByYear> getRateByYearChart() {
        return this.moviesDao.selectRateByYear();
    }

    @Override
    public List<MovieCountByAddress> getMovieDataByAddress() {
        return this.moviesDao.selectMovieDataByAddress();
    }

    @Override
    public List<MovieCountByLang> getMovieDataByLang() {
        return this.moviesDao.selectMovieDataByLang();
    }

    @Override
    public List<MovieDataByDirector> getMovieDataByDirectors() {
        return this.moviesDao.selectMovieDataByDirectors();
    }

    @Override
    public List<MovieDataByCast> getMovieDataByCast() {
        return this.moviesDao.selectMovieDataByCast();
    }


    @Override
    public List<MovieRateByCountry> getMovieRateByCountry() {
        return this.moviesDao.getMovieRateByCountry();
    }

    @Override
    public List<MovieCountByCountryDTO> getMoiveDataByForeign() {
        return this.moviesDao.getMoiveDataByForeign();
    }
}
