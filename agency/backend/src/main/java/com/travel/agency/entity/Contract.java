package com.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 계약 엔티티
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    
    private Long contractId;
    private Long customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String contractType;
    private BigDecimal discountRate;
    private String billingCycle;
    private BigDecimal creditLimit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
