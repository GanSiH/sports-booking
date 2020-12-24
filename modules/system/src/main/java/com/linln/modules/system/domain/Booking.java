package com.linln.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linln.common.enums.StatusEnum;
import com.linln.common.utils.StatusUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author GSH
 */
@Data
@Entity
@Table(name = "s_booking", schema = "sjsportsbooking", catalog = "")
//@ToString(exclude = {"users", "createBy", "updateBy"})
//@EqualsAndHashCode(exclude = {"users", "createBy", "updateBy"})
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "update s_booking" + StatusUtil.SLICE_DELETE)
@Where(clause = StatusUtil.NOT_DELETE)
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "venue_id")
    private Integer venueId;

    @Basic
    @Column(name = "booking_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date bookingDate;

    @Basic
    @Column(name = "booking_time")
    private String bookingTime;

    @Basic
    @Column(name = "number")
    private Integer number;

    @Basic
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "create_date")
    @LastModifiedDate
    private Date createDate;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "create_by")
    @JsonIgnore
    private User createBy;

    @Basic
    @Column(name = "update_date")
    @LastModifiedDate
    private Date updateDate;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "update_by")
    @JsonIgnore
    private User updateBy;

    @Basic
    @Column(name = "status")
    private Byte status = StatusEnum.OK.getCode();
}
