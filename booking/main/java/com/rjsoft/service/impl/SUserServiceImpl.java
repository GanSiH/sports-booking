package rjsoft.service.impl;

import rjsoft.dao.SUserDao;
import rjsoft.po.SUser;
import rjsoft.service.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(SUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-16 11:37:04
 */
@Service("sUserService")
public class SUserServiceImpl implements SUserService {

    @Autowired
    SUserDao userDao;

    //注册
    public String register(SUser sUser){
        List<SUser> users = userDao.selectAll(sUser);
        if (users.isEmpty()) {
            userDao.insert(sUser);
            return "注册成功";
        } else {
            return "账户已存在";
        }
    }

    //登录
    public String login(SUser sUser){
        List<SUser> users = userDao.selectAll(sUser);
        if (users.isEmpty()) {
            userDao.insert(sUser);
            return "账号或用户名错误";
        } else if (users.size() == 1){
            return "账户已存在";
        } else {
            return "为止错误";
        }
    }

    @Override
    public List<SUser> selectAll(SUser user){
        return userDao.selectAll(user);
    }
}