package com.neusoft.xlm.service;

import com.neusoft.xlm.entity.Movies;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MovieDataService {

    List<Movies> queryByName(String searchIpt);


    List<Movies> queryMovie();


    int deleteByTitle(String title);

}
