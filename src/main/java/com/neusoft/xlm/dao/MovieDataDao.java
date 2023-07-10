package com.neusoft.xlm.dao;

import com.neusoft.xlm.entity.Movies;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MovieDataDao {

    List<Movies> queryByName(String searchIpt);

    List<Movies> queryMovie();

    int deleteByTitle(String title);

}
