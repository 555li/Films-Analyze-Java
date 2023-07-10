package com.neusoft.xlm.service;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WcpService {
    List<String> queryWCPCastsData();

    List<String> queryWCPTitlesData();

    List<String> queryWCPSummaryData();

}
