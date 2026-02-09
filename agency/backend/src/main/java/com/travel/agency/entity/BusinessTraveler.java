package com.travel.agency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 출장자 엔티티
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessTraveler {
    
    private Long travelerId;
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String position;
    private String travelGrade;
    private Character activeYn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
