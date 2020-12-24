package rjsoft.po;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * s_booking
 * @author 
 */
@Data
public class SBooking implements Serializable {
    private Integer id;

    /**
     * 预约人id
     */
    private Integer userId;

    /**
     * 场馆id
     */
    private Integer venueId;

    /**
     * 预约日期
     */
    private Date bookingDate;

    /**
     * 预约时间段
     */
    private String bookingPeriod;

    /**
     * 预约人数
     */
    private Integer number;

    /**
     * 操作时间
     */
    private Date operTime;

    private static final long serialVersionUID = 1L;
}