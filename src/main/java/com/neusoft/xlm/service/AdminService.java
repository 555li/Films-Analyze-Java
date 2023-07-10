package com.neusoft.xlm.service;

import com.neusoft.xlm.entity.users.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    Admin queryByAccount(String account);
}
