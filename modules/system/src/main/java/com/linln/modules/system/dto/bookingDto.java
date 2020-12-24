package com.linln.modules.system.dto;

import lombok.Data;

import java.util.Date;

/**
 * Create By GSH on .
 */
@Data
public class bookingDto {

    private Date bookingDate;

    private Integer bookingTimeId;

    private String bookingTime;

    private Integer venueId;

    private String venue;

    private Integer enabled = 1;
}
