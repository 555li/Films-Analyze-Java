package com.neusoft.xlm.service.impl;

import com.neusoft.xlm.dao.AdminDao;
import com.neusoft.xlm.entity.users.Admin;
import com.neusoft.xlm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin queryByAccount(String account) {
        return adminDao.selectByAccount(account);
    }
}
