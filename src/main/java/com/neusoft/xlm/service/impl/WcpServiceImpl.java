package com.neusoft.xlm.service.impl;

import com.neusoft.xlm.dao.WcpDao;
import com.neusoft.xlm.service.WcpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WcpServiceImpl implements WcpService {
    @Resource
    private WcpDao wcpDao;

    @Override
    public List<String> queryWCPCastsData() {
        return this.wcpDao.queryWCPCastsData();
    }

    @Override
    public List<String> queryWCPTitlesData() {
        return this.wcpDao.queryWCPTitleData();
    }

    @Override
    public List<String> queryWCPSummaryData() {
        return this.wcpDao.queryWCPSummaryData();
    }
}
