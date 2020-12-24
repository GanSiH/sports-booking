package com.linln.admin.system.controller;

import com.linln.admin.system.validator.BookingValid;
import com.linln.common.utils.ResultVoUtil;
import com.linln.common.vo.ResultVo;
import com.linln.component.actionLog.action.RoleAction;
import com.linln.component.actionLog.annotation.ActionLog;
import com.linln.component.actionLog.annotation.EntityParam;
import com.linln.component.thymeleaf.utility.DictUtil;
import com.linln.modules.system.domain.Booking;
import com.linln.modules.system.domain.User;
import com.linln.modules.system.dto.*;
import com.linln.modules.system.service.BookingService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GSH
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

    static SimpleDateFormat sdf_1 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat sdf_2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private BookingService bookingService;


    @GetMapping("/index")
//    @RequiresPermissions("booking:index")
    public String findAll(Model model){
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        Long userId = principal.getId();
        Page<Booking> page = bookingService.findAllByUserId(userId);
        // 封装数据
        model.addAttribute("list", page.getContent());
        model.addAttribute("page", page);
        return "/booking/index";
    }

    /**
     * 跳转到预约页面
     */
    @GetMapping("/add")
//    @RequiresPermissions("booking:add")
    public String toAdd(Model model){
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        Map<String, String> bookingTimes = DictUtil.value("BOOKING_TIME");
        Map<String, String> venueTypes = DictUtil.value("VENUE_TYPE");
        List<String> bookingDates = bookingService.getBookingDates();
        model.addAttribute("nickname", principal.getNickname());
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("bookingDates", bookingDates);
        model.addAttribute("bookingTimes", bookingTimes);
        model.addAttribute("venueTypes", venueTypes);
        return "/booking/add";
    }

    /**
     * 取消预约
     */
    @RequestMapping("/cancel/{id}")
    @ResponseBody
//        @RequiresPermissions("booking:edit")
    public ResultVo cancel(@PathVariable("id") String id) throws ParseException {
        Map<String, String> bookingTimes = DictUtil.value("BOOKING_TIME");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, 12);
        Booking booking = bookingService.findById(Long.parseLong(id));
        String datetime = sdf_1.format(booking.getBookingDate())+" "+bookingTimes.get(booking.getBookingTime());
        if (sdf_2.parse(datetime).compareTo(cal.getTime()) < 0 ) {
            return ResultVoUtil.error("只有在预约时间开始前12小时方可取消预约");
        }
        if (bookingService.cancel(Long.parseLong(id)) == 1) {
            return ResultVoUtil.success( "成功");
        } else {
            return ResultVoUtil.error("失败，请重新操作");
        }
    }

    /**
     * 保存预约的数据
     * @param valid 验证对象
     * @param booking 实体对象
     */
    @PostMapping("/save")
//    @RequiresPermissions({"booking:add", "booking:edit"})
    @ResponseBody
    @ActionLog(key = RoleAction.ROLE_SAVE, action = RoleAction.class)
    public ResultVo save(@Validated BookingValid valid, @EntityParam Booking booking){
        if (bookingService.isBooked(booking)) {
            return ResultVoUtil.error("已被预约，请重新操作");
        }
        if (bookingService.countByUserIdAndBookingDate(booking) >= 2) {
            return ResultVoUtil.error("同一手机号，在同一天内不可预约三场以上");
        }
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        Long userId = principal.getId();
        booking.setUserId(userId);
        booking.setVenueId(valid.getVenue());
        bookingService.save(booking);
        return ResultVoUtil.SAVE_SUCCESS;
    }

    /**
     * 预约下拉框联动 查询预约时间
     * @param bookingDate
     * @return
     * @throws ParseException
     */
    @PostMapping("/findBookingTimeByBookingDate")
    @ResponseBody
    public List<bookingDto> findBookingTimeByBookingDate(@RequestParam("bookingDate") String bookingDate) throws ParseException {
        List<bookingDto> list = new ArrayList<>();
        Map<String, String> bookingTimes = DictUtil.value("BOOKING_TIME");
        Map<String, Integer> bookingTimesEnable = bookingService.findByBookingDate(bookingDate);
        for (int i = 1; i <= bookingTimes.size(); i++) {
            bookingDto bookingDto = new bookingDto();
            bookingDto.setBookingTimeId(i);
            bookingDto.setBookingTime(bookingTimes.get(String.valueOf(i)));
            bookingDto.setEnabled(bookingTimesEnable.get(String.valueOf(i))==null?1:bookingTimesEnable.get(String.valueOf(i)));
            list.add(bookingDto);
        }
        return list;
    }

    /**
     * 预约下拉框联动 查询场馆
     * @param bookingDate
     * @param bookingTime
     * @return
     * @throws ParseException
     */
    @PostMapping("/findVenueByBookingTime")
    @ResponseBody
    public List<bookingDto> findVenueByBookingTime(@RequestParam("bookingDate") String bookingDate, @RequestParam("bookingTime") String bookingTime) throws ParseException {
        List<bookingDto> list = new ArrayList<>();
        Map<String, String> venueTypes = DictUtil.value("VENUE_TYPE");
        Map<Integer, Integer> venueTypesEnable = bookingService.findByBookingDateAndBookingTime(bookingDate, bookingTime);
        for (int i = 1; i <= venueTypes.size(); i++) {
            bookingDto bookingDto = new bookingDto();
            bookingDto.setVenueId(i);
            bookingDto.setVenue(venueTypes.get(String.valueOf(i)));
            bookingDto.setEnabled(venueTypesEnable.get(i)==null?0:venueTypesEnable.get(i));
            list.add(bookingDto);
        }
        return list;
    }
}
