package com.neusoft.xlm.service;

import com.neusoft.xlm.entity.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-03-22 16:28:52
 */
@Service
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<User> queryByPage(User user, PageRequest pageRequest);

    /**
     * 修改数据
     *
     * @return 实例对象
     */
    int update(String email, String password);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    User queryByEmail(String email);

    int insert(User user);
}
