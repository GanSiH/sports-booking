//package rjsoft.service.impl;
//
//import rjsoft.dao.SBookingDao;
//import rjsoft.po.SBooking;
//import rjsoft.service.SBookingService;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * 预约表(SBooking)表服务实现类
// *
// * @author GSH
// * @since 2020-11-25 16:58:24
// */
//@Service("bookingService")
//public class SBookingServiceImpl implements SBookingService {
//    @Resource
//    private SBookingDao bookingDao;
//
//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    @Override
//    public SBooking queryById(Integer id) {
//        return this.bookingDao.queryById(id);
//    }
//
//    /**
//     * 查询多条数据
//     *
//     * @param offset 查询起始位置
//     * @param limit  查询条数
//     * @return 对象列表
//     */
//    @Override
//    public List<SBooking> queryAllByLimit(int offset, int limit) {
//        return this.bookingDao.queryAllByLimit(offset, limit);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param SBooking 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public SBooking insert(SBooking SBooking) {
//        this.bookingDao.insert(SBooking);
//        return SBooking;
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param SBooking 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public SBooking update(SBooking SBooking) {
//        this.bookingDao.update(SBooking);
//        return this.queryById(SBooking.getId());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param id 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(Integer id) {
//        return this.bookingDao.deleteById(id) > 0;
//    }
//}