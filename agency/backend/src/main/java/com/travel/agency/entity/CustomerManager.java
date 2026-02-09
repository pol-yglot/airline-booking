package com.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 고객사 담당자 엔티티
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerManager {
    
    private Long managerId;
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String position;
    private Character mainYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
