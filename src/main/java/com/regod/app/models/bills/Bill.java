package com.regod.app.models.bills;
import com.regod.app.models.product.OrderProduct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Bill {
    //@Getter @Setter
    private int id;
    private String departmentName;
    private String billName;
    private int totalPrice;
    private String dueDate;
    private String status;

    //auto






    //private
}
