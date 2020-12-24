package com.linln.admin.system.validator;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author GSH
 */
@Data
public class BookingValid implements Serializable {
    @NotNull(message = "手机号不能为空")
    private String username;

    @NotNull(message = "姓名不能为空")
//    @Size(min = 2, message = "用户昵称：请输入至少2个字符")
    private String nickname;

    @NotNull(message = "人数不能为空")
    private Integer number;

    @NotNull(message = "预约日期不能为空")
    private String bookingDate;

    @NotNull(message = "预约时间不能为空")
    private Integer bookingTime;

    @NotNull(message = "场馆不能为空")
    private Integer venue;
}
