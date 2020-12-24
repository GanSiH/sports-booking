package rjsoft.service;

import rjsoft.po.SBooking;

import java.util.List;

/**
 * 预约表(SBooking)表服务接口
 *
 * @author GSH
 * @since 2020-11-25 16:58:24
 */
public interface SBookingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SBooking queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SBooking> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param SBooking 实例对象
     * @return 实例对象
     */
    SBooking insert(SBooking SBooking);

    /**
     * 修改数据
     *
     * @param SBooking 实例对象
     * @return 实例对象
     */
    SBooking update(SBooking SBooking);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}