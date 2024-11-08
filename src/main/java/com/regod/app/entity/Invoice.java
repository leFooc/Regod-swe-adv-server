package com.regod.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Table(name = "invoice")
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Invoice {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String billID;
    @NonNull
    private String paidDate;
    @NonNull
    private String paidAmount;
    @NonNull
    private String imgURL;
}
