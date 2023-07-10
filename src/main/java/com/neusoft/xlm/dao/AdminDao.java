package com.neusoft.xlm.dao;

import com.neusoft.xlm.entity.users.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
    Admin selectByAccount(String account);
}
