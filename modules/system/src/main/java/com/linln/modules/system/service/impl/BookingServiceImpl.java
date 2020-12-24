package com.linln.modules.system.service.impl;

import com.linln.common.data.PageSort;
import com.linln.common.utils.DateUtil;
import com.linln.modules.system.domain.Booking;
import com.linln.modules.system.repository.BookingRepository;
import com.linln.modules.system.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GSH
 */
@Service
public class BookingServiceImpl implements BookingService {

    static SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * 获取全部的预约列表
     */
    @Override
    public Page<Booking> findAll() {
        PageRequest page = PageSort.pageRequest(Sort.Direction.ASC);
        return bookingRepository.findAll(page);
    }

    /**
     * 获取当前用户的预约列表
     */
    @Override
    public Page<Booking> findAllByUserId(Long userId) {
        Pageable page = PageSort.pageRequest(Sort.Direction.ASC);
        return bookingRepository.findAllByUserId(userId, page);
    }

    //取消预约
    @Override
    public Integer cancel(Long id){
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        return bookingRepository.updateStatus((byte) 3, ids);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findByIdAndStatus(id, (byte) 1);
    }

    @Override
    public List<String> getBookingDates() {
        Date today = DateUtil.getToday();
        List<String> dateList = DateUtil.getAddDayDateList(today, 3);
        return dateList;
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    //是否被预约（true：被预约）
    @Override
    public boolean isBooked(Booking booking) {
        List<Booking> bookinglist = bookingRepository.findByBookingDateAndBookingTimeAndVenueId(booking.getBookingDate(), booking.getBookingTime(), booking.getVenueId());
        return bookinglist.size() > 0;
    }

    //同一手机号，在同一天内预约数量
    @Override
    public Integer countByUserIdAndBookingDate(Booking booking) {
        Integer bookinglist = bookingRepository.countByUserIdAndBookingDate(booking.getUserId(), booking.getBookingDate());
        return bookinglist;
    }

    @Override
    public Integer countByUserIdAndBookingDateAndBookingTime(Booking booking) {
        Integer bookinglist = bookingRepository.countByUserIdAndBookingDateAndBookingTime(booking.getUserId(), booking.getBookingDate(), booking.getBookingTime());
        return bookinglist;
    }

    @Override
    public Map<String, Integer> findByBookingDateAndVenueId(String bookingDate, Integer venue) throws ParseException {
        Map<String, Integer> map = new HashMap<>();
        List<Booking> bookinglist = bookingRepository.findByBookingDateAndVenueId(sdf_1.parse(bookingDate), venue);
        for (Booking booking : bookinglist) {
            map.put(booking.getBookingTime(), 0);
        }
        return map;
    }

    @Override
    public Map<String, Integer> findByBookingDate(String bookingDate) throws ParseException {
        Map<String, Integer> map = new HashMap<>();
        List<Booking> bookinglist = bookingRepository.findByBookingDate(sdf_1.parse(bookingDate));
        for (Booking booking : bookinglist) {
            map.put(booking.getBookingTime(), 0);
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> findByBookingDateAndBookingTime(String bookingDate, String bookingTime) throws ParseException {
        Map<Integer, Integer> map = new HashMap<>();
        List<Booking> bookinglist = bookingRepository.findByBookingDateAndBookingTime(sdf_1.parse(bookingDate), bookingTime);
        for (Booking booking : bookinglist) {
            map.put(booking.getVenueId(), 0);
        }
        return map;
    }
}
