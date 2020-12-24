package rjsoft.service;

import rjsoft.po.SUser;

import java.util.List;

/**
 * 用户表(SUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-16 11:36:48
 */
public interface SUserService{

    List<SUser> selectAll(SUser user);
}