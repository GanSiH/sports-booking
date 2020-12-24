package rjsoft.dao;

import rjsoft.po.SBooking;

public interface SBookingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SBooking record);

    int insertSelective(SBooking record);

    SBooking selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SBooking record);

    int updateByPrimaryKey(SBooking record);
}