package com.neusoft.xlm.controller;

import com.neusoft.xlm.entity.address.MovieCountByAddress;
import com.neusoft.xlm.entity.address.MovieCountByAddressDTO;
import com.neusoft.xlm.entity.cast.MovieDataByCast;
import com.neusoft.xlm.entity.cast.MovieDataByCastDTO;
import com.neusoft.xlm.entity.country.MovieCountByCountryDTO;
import com.neusoft.xlm.entity.country.MovieRateByCountry;
import com.neusoft.xlm.entity.country.MovieRateByCountryDTO;
import com.neusoft.xlm.entity.director.MovieDataByDirector;
import com.neusoft.xlm.entity.director.MovieDataByDirectorDTO;
import com.neusoft.xlm.entity.indexData.MainMoviesDataDTO;
import com.neusoft.xlm.entity.lang.MovieCountByLang;
import com.neusoft.xlm.entity.lang.MovieCountByLangDTO;
import com.neusoft.xlm.entity.rate.MovieDataByRate;
import com.neusoft.xlm.entity.stars.MovieCountByStars;
import com.neusoft.xlm.entity.stars.MovieCountByStarsDTO;
import com.neusoft.xlm.entity.stars.MovieNameAndStars;
import com.neusoft.xlm.entity.utils.PieChartUtilDTO;
import com.neusoft.xlm.entity.year.MovieCountByYear;
import com.neusoft.xlm.entity.year.MovieCountByYearDTO;
import com.neusoft.xlm.entity.year.MovieRateByYear;
import com.neusoft.xlm.entity.year.MovieRateByYearDTO;
import com.neusoft.xlm.service.MoviesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Movies)表控制层
 *
 * @author xlm
 * @since 2023-03-22 16:24:42
 */
@Controller
public class MoviesController {
    /**
     * 服务对象
     */
    @Resource
    private MoviesService moviesService;

    /**
     * 获取电影个数，豆瓣最高评分、出厂最多演员、制片国家最多数、最多电影语言
     *
     * @return
     */
    @PostMapping("/MainMoiveData")
    @ResponseBody
    public MainMoviesDataDTO getMainMoiveData() {
        int len = moviesService.getLength();
        double rate = moviesService.getMaxRate();
        String cast = moviesService.getMaxCast();
        String country = moviesService.getMaxCountry();
        String lang = moviesService.getMaxLang();
        int typeCount = moviesService.getTypeCount();
        MainMoviesDataDTO mainMoviesDataDTO = new MainMoviesDataDTO(
                len, rate, cast, country, lang, typeCount
        );
        return mainMoviesDataDTO;
    }

    /**
     * 获取前端电影种类饼状图数据，type_t的饼状图
     *
     * @return
     */
    @PostMapping("/PieChartByMovieCategory")
    @ResponseBody
    public List<PieChartUtilDTO> getPieChartByMovieCategory() {
        List<PieChartUtilDTO> lists = moviesService.getPieChartByMovieCategory();
        return lists;
    }

    /**
     * 获取历年电影数量折线图数据，时间分析表time_t的折线图
     *
     * @return
     */
    @PostMapping("/MovieCountByYear")
    @ResponseBody
    public MovieCountByYearDTO getMovieCountByYear() {
        MovieCountByYearDTO mvbyldto = new MovieCountByYearDTO();
        List<MovieCountByYear> movieCountData = moviesService.getMovieCountByYear();
        List<String> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < movieCountData.size(); i++) {
            rows.add(movieCountData.get(i).getYear());
        }
        for (int i = 0; i < movieCountData.size(); i++) {
            cols.add(movieCountData.get(i).getCount());
        }
        mvbyldto.setRows(rows);
        mvbyldto.setCols(cols);
        return mvbyldto;
    }

    /**
     * 获取前端电影时间饼状图数据，时间分析表time_t的饼状图
     *
     * @return
     */
    @PostMapping("/MoiveDataByTime")
    @ResponseBody
    public List<PieChartUtilDTO> getMoiveDataByTime() {
        List<PieChartUtilDTO> lists = moviesService.getMoiveDataByTime();
        return lists;
    }

    /**
     * 获取电影评分折线图数据，rate_t的折线图
     *
     * @return
     */
    @PostMapping("/CinematicLine")
    @ResponseBody
    public PieChartUtilDTO getCinematicLine() {
        PieChartUtilDTO pieChartUtilDTO = new PieChartUtilDTO();
        List<MovieDataByRate> maxRate = moviesService.getScore();
        List<Double> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < maxRate.size(); i++) {
            rows.add(maxRate.get(i).getRate());
        }
        for (int i = 0; i < maxRate.size(); i++) {
            cols.add(maxRate.get(i).getCount());
        }
        pieChartUtilDTO.setRows(rows);
        pieChartUtilDTO.setCols(cols);

        return pieChartUtilDTO;
    }

    /**
     * 通过名称查找电影五星到一星的比例,评分页面rate_t的饼状图
     *
     * @return
     */
    @PostMapping("/queryRateByMovieName/{movieName}")
    @ResponseBody
    public MovieNameAndStars searchMovie(@PathVariable String movieName) {
        MovieCountByStars movieCountByStars = moviesService.getMovieRateByName(movieName);
        MovieCountByStarsDTO starAndTitledto1 = new MovieCountByStarsDTO();
        MovieCountByStarsDTO starAndTitledto2 = new MovieCountByStarsDTO();
        MovieCountByStarsDTO starAndTitledto3 = new MovieCountByStarsDTO();
        MovieCountByStarsDTO starAndTitledto4 = new MovieCountByStarsDTO();
        MovieCountByStarsDTO starAndTitledto5 = new MovieCountByStarsDTO();
        List<MovieCountByStarsDTO> list = new ArrayList<>();

        starAndTitledto1.setName("五星");
        starAndTitledto1.setValue(movieCountByStars.getFiveStar());
        list.add(starAndTitledto1);
        starAndTitledto2.setName("四星");
        starAndTitledto2.setValue(movieCountByStars.getFourStar());
        list.add(starAndTitledto2);
        starAndTitledto3.setName("三星");
        starAndTitledto3.setValue(movieCountByStars.getThreeStar());
        list.add(starAndTitledto3);
        starAndTitledto4.setName("二星");
        starAndTitledto4.setValue(movieCountByStars.getTwoStar());
        list.add(starAndTitledto4);
        starAndTitledto5.setName("一星");
        starAndTitledto5.setValue(movieCountByStars.getOneStar());
        list.add(starAndTitledto5);

        MovieNameAndStars movieNameAndStars = new MovieNameAndStars();
        System.out.println(movieCountByStars.getTitle());
        movieNameAndStars.setTitle(movieCountByStars.getTitle());
        movieNameAndStars.setList(list);
        return movieNameAndStars;
    }

    /**
     * 查找每个年份的电影平均评分,评分页面rate_t的柱状图
     *
     * @return
     */
    @PostMapping("RateByYearChart")
    @ResponseBody
    public MovieRateByYearDTO getRateByYearChart() {
        List<MovieRateByYear> lists = moviesService.getRateByYearChart();
        MovieRateByYearDTO movieRateByYearDTO = new MovieRateByYearDTO();
        List<Double> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            rows.add(lists.get(i).getRate());
        }
        for (int i = 0; i < lists.size(); i++) {
            cols.add(lists.get(i).getYear());
        }
        movieRateByYearDTO.setRows(rows);
        movieRateByYearDTO.setCols(cols);

        return movieRateByYearDTO;
    }

    /**
     * 查找每个地区的电影数据,adress_t的水平柱状图
     *
     * @return
     */
    @PostMapping("MovieDataByAddress")
    @ResponseBody
    public MovieCountByAddressDTO getMovieDataByAddress() {
        List<MovieCountByAddress> lists = moviesService.getMovieDataByAddress();
        MovieCountByAddressDTO movieCountByAddressDTO = new MovieCountByAddressDTO();
        List<Integer> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            rows.add(lists.get(i).getCount());
        }
        for (int i = 0; i < lists.size(); i++) {
            cols.add(lists.get(i).getAddress());
        }
        movieCountByAddressDTO.setRows(rows);
        movieCountByAddressDTO.setCols(cols);

        return movieCountByAddressDTO;
    }

    /**
     * 查找每个语言的电影数据,adress_t的发散饼状图
     *
     * @return
     */
    @PostMapping("MovieDataByLang")
    @ResponseBody
    public MovieCountByLangDTO getMovieDataByLang() {
        List<MovieCountByLang> lists = moviesService.getMovieDataByLang();
        MovieCountByLangDTO movieCountByLangDTO = new MovieCountByLangDTO();
        List<Integer> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            rows.add(lists.get(i).getCount());
        }
        for (int i = 0; i < lists.size(); i++) {
            cols.add(lists.get(i).getLang());
        }
        movieCountByLangDTO.setRows(rows);
        movieCountByLangDTO.setCols(cols);

        return movieCountByLangDTO;
    }

    /**
     * 查找导演作品前20,actor_t的水平柱状图
     *
     * @return
     */
    @PostMapping("MovieDataByDirectors")
    @ResponseBody
    public MovieDataByDirectorDTO getMovieDataByDirectors() {
        List<MovieDataByDirector> lists = moviesService.getMovieDataByDirectors();
        MovieDataByDirectorDTO movieDataByDirectorDTO = new MovieDataByDirectorDTO();
        List<String> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            rows.add(lists.get(i).getDirName());
        }
        for (int i = 0; i < lists.size(); i++) {
            cols.add(lists.get(i).getCount());
        }
        movieDataByDirectorDTO.setRows(rows);
        movieDataByDirectorDTO.setCols(cols);

        return movieDataByDirectorDTO;
    }

    /**
     * 查找演员参演前20,actor_t的折线图
     *
     * @return
     */
    @PostMapping("MovieDataByCast")
    @ResponseBody
    public MovieDataByCastDTO getMovieDataByCast() {
        List<MovieDataByCast> lists = moviesService.getMovieDataByCast();
        MovieDataByCastDTO movieDataByCastDTO = new MovieDataByCastDTO();
        List<String> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            rows.add(lists.get(i).getCastName());
        }
        for (int i = 0; i < lists.size(); i++) {
            cols.add(lists.get(i).getCount());
        }
        movieDataByCastDTO.setRows(rows);
        movieDataByCastDTO.setCols(cols);

        return movieDataByCastDTO;
    }

    /**
     * 获取中外评分数据,chinaVsForeign的折线图
     *
     * @return
     */
    @PostMapping("MovieRateByCountry")
    @ResponseBody
    public MovieRateByCountryDTO getMovieRateByCountry() {
        MovieRateByCountryDTO mvbyldto = new MovieRateByCountryDTO();
        List<MovieRateByCountry> movieCountData = moviesService.getMovieRateByCountry();
        List<String> rows = new ArrayList<>();
        List<Double> cols = new ArrayList<>();
        for (int i = 0; i < movieCountData.size(); i++) {
            rows.add(movieCountData.get(i).getCountry_type());
        }
        for (int i = 0; i < movieCountData.size(); i++) {
            cols.add(movieCountData.get(i).getRate());
        }
        mvbyldto.setRows(rows);
        mvbyldto.setCols(cols);
        return mvbyldto;
    }

    /**
     * 获取中外电影数量,chinaVsForeign的饼状图
     *
     * @return
     */
    @PostMapping("/MovieCountByCountry")
    @ResponseBody
    public List<MovieCountByCountryDTO> getMoiveDataByForeign() {
        List<MovieCountByCountryDTO> lists = moviesService.getMoiveDataByForeign();
        return lists;
    }

}

