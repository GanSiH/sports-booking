package com.linln.modules.system.service;

import com.linln.modules.system.domain.Booking;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author GSH
 */
public interface BookingService {

    Page<Booking> findAll();

    Page<Booking> findAllByUserId(Long userId);

//    List<Booking> getBookingTimes(Map<String, String> bookingTimes);

    //取消预约
    Integer cancel(Long id) throws ParseException;

    Booking findById(Long id);

    List<String> getBookingDates();

    Booking save(Booking booking);

    //是否被预约（true：被预约）
    boolean isBooked(Booking booking);

    //同一手机号，在同一天内预约数量
    Integer countByUserIdAndBookingDate(Booking booking);

    Integer countByUserIdAndBookingDateAndBookingTime(Booking booking);

    Map<String, Integer> findByBookingDateAndVenueId(String bookingDate, Integer venue) throws ParseException;

    Map<String, Integer> findByBookingDate(String bookingDate) throws ParseException;

    Map<Integer, Integer> findByBookingDateAndBookingTime(String bookingDate, String bookingTime) throws ParseException;
}
