package com.neusoft.xlm.service;


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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (Movies)表服务接口
 *
 * @author makejava
 * @since 2023-03-22 16:24:44
 */
public interface MoviesService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Movies queryById(Integer id);

    /**
     * 分页查询
     *
     * @param movies      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Movies> queryByPage(Movies movies, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param movies 实例对象
     * @return 实例对象
     */
    Movies insert(Movies movies);

    /**
     * 修改数据
     *
     * @param movies 实例对象
     * @return 实例对象
     */
    Movies update(Movies movies);


    int getLength();

    double getMaxRate();

    String getMaxCast();

    String getMaxCountry();

    String getMaxLang();

    List<MovieDataByRate> getScore();
    int getTypeCount();

    List<PieChartUtilDTO> getPieChartByMovieCategory();

    List<MovieCountByYear> getMovieCountByYear();

    List<PieChartUtilDTO> getMoiveDataByTime();

    MovieCountByStars getMovieRateByName(String title);

    List<MovieRateByYear> getRateByYearChart();

    List<MovieCountByAddress> getMovieDataByAddress();

    List<MovieCountByLang> getMovieDataByLang();

    List<MovieDataByDirector> getMovieDataByDirectors();

    List<MovieDataByCast> getMovieDataByCast();

    List<MovieRateByCountry> getMovieRateByCountry();

    List<MovieCountByCountryDTO> getMoiveDataByForeign();


}
