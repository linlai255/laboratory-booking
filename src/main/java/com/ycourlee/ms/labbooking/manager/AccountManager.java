package com.ycourlee.ms.labbooking.manager;

import com.ycourlee.ms.labbooking.mapper.UserMapper;
import com.ycourlee.ms.labbooking.util.KeyPool;
import com.ycourlee.root.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yongjiang
 */
@Component
public class AccountManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Redis redis;

    public boolean haveAliveCodeCurrentPhone(String phone) {
        return StringUtil.isNotEmpty(redis.get(KeyPool.verifyCode(phone)));
    }
}
