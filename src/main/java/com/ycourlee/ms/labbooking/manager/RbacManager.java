package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class RbacManager {

    @Autowired
    private UserMapper         userMapper;
    @Autowired
    private RoleMapper         roleMapper;
    @Autowired
    private ResourceMapper     resourceMapper;
    @Autowired
    private UserRoleMapper     userRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;


    public void createUser(String username, String verifyCode) {

    }

}
