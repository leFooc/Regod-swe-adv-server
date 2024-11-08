package com.regod.app.dto.request;

import lombok.Data;

@Data
public class ModifyOrderDto {
    private String paidDate;
    private String paidAmount;
    private String imgURL;
}
