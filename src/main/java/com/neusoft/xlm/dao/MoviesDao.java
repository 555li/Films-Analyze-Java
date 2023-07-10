package com.neusoft.xlm.dao;

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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * (Movies)表数据库访问层
 *
 * @author makejava
 * @since 2023-03-22 16:24:42
 */
@Mapper
public interface MoviesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Movies queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param movies   查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Movies> queryAllByLimit(Movies movies, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param movies 查询条件
     * @return 总行数
     */
    long count(Movies movies);

    /**
     * 新增数据
     *
     * @param movies 实例对象
     * @return 影响行数
     */
    int insert(Movies movies);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Movies> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Movies> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Movies> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Movies> entities);

    /**
     * 修改数据
     *
     * @param movies 实例对象
     * @return 影响行数
     */
    int update(Movies movies);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int getLength();

    double getMaxRate();

    String getMaxCast();

    String getMaxCountry();

    String getMaxLang();

    int getTypeCount();

    List<MovieDataByRate> selectCountByRate();

    List<PieChartUtilDTO> selectCountByCategory();

    List<MovieCountByYear> selectMovieCountByYear();

    List<PieChartUtilDTO> selectMovieDataByTime();

    MovieCountByStars selectMovieRateByName(String title);

    List<MovieRateByYear> selectRateByYear();

    List<MovieCountByAddress> selectMovieDataByAddress();

    List<MovieCountByLang> selectMovieDataByLang();

    List<MovieDataByDirector> selectMovieDataByDirectors();

    List<MovieDataByCast> selectMovieDataByCast();

    List<MovieRateByCountry> getMovieRateByCountry();

    List<MovieCountByCountryDTO> getMoiveDataByForeign();


}

