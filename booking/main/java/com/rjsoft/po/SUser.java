package rjsoft.po;

import java.io.Serializable;
import lombok.Data;

/**
 * s_user
 * @author 
 */
@Data
public class SUser implements Serializable {
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 预约资格（失约3次失去资格）
     */
    private Integer qualification;

    private static final long serialVersionUID = 1L;
}