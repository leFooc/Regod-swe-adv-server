package com.regod.app.dto.request;

import lombok.Data;

@Data
public class BillCreationRequest {
    private String billName;
    private String departmentName;
    private String createDate;
    private String dueDate;
}
