package rjsoft.dao;

import rjsoft.po.SDict;

public interface SDictDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SDict record);

    int insertSelective(SDict record);

    SDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SDict record);

    int updateByPrimaryKey(SDict record);
}