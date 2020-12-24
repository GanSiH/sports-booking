package com.linln.modules.system.repository;

import com.linln.modules.system.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * @author GSH
 * @date 2018/8/14
 */
public interface BookingRepository extends BaseRepository<Booking, Long> {

    Page<Booking> findAllByUserId(Long userId, Pageable pageable);

    List<Booking> findByBookingDateAndBookingTimeAndVenueId(Date bookingDate, String bookingTime, Integer venue);

    List<Booking> findByBookingDate(Date bookingDate);

    List<Booking> findByBookingDateAndBookingTime(Date bookingDate, String bookingTime);

    Integer countByUserIdAndBookingDate(Long userId, Date bookingDate);
}
