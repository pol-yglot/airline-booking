package com.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 고객사(법인) 엔티티
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    
    private Long customerId;
    private String companyName;
    private String businessNumber;
    private String companyType;
    private String industry;
    private Integer employeeCount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
