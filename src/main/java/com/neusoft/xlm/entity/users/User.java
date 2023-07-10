package com.neusoft.xlm.entity.users;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-03-22 16:28:52
 */
public class User implements Serializable {
    private static final long serialVersionUID = -98481904458154232L;
    
    private Integer id;
    
    private String email;
    
    private String password;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User(){

    }
    public User(String email, String password) {
        this.email=email;
        this.password=password;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

