package com.neusoft.xlm.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WcpDao {
    List<String> queryWCPCastsData();

    List<String> queryWCPTitleData();

    List<String> queryWCPSummaryData();
}
