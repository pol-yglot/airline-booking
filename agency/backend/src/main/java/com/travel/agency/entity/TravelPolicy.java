package com.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 출장 정책 엔티티
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelPolicy {
    
    private Long policyId;
    private Long customerId;
    private String allowedFlightClass;
    private String allowedHotelGrade;
    private Character approvalRequiredYn;
    private BigDecimal approvalLimitAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
