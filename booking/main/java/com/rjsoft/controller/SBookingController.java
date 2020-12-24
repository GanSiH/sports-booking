//package rjsoft.controller;
//
//import rjsoft.service.SBookingService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
///**
// * 预约表(Booking)表控制层
// *
// * @author GSH
// * @since 2020-11-25 16:58:50
// */
//@RestController
//@RequestMapping("booking")
//public class SBookingController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private SBookingService bookingService;
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("selectOne")
//    public Booking selectOne(Integer id) {
//        return this.bookingService.queryById(id);
//    }
//
//}