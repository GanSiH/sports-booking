package rjsoft.dao;

import rjsoft.po.SVenue;

public interface SVenueDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SVenue record);

    int insertSelective(SVenue record);

    SVenue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SVenue record);

    int updateByPrimaryKey(SVenue record);
}