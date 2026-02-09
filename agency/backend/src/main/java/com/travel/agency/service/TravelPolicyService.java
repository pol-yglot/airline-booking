package com.travel.agency.service;

import com.travel.agency.entity.TravelPolicy;
import com.travel.agency.mapper.TravelPolicyMapper;
import com.travel.agency.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * 출장 정책 비즈니스 로직 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TravelPolicyService {
    
    private final TravelPolicyMapper travelPolicyMapper;
    private final CustomerMapper customerMapper;
    
    /**
     * 고객사별 출장 정책 조회
     */
    public Optional<TravelPolicy> getPolicyByCustomerId(Long customerId) {
        log.info("조회: 고객사 {} 출장 정책", customerId);
        validateCustomerExists(customerId);
        return travelPolicyMapper.selectByCustomerId(customerId);
    }
    
    /**
     * 정책 ID로 조회
     */
    public Optional<TravelPolicy> getPolicyById(Long policyId) {
        log.info("조회: 정책 ID = {}", policyId);
        return travelPolicyMapper.selectById(policyId);
    }
    
    /**
     * 정책 등록
     */
    public TravelPolicy createPolicy(TravelPolicy policy) {
        log.info("등록: 고객사 {} 출장 정책", policy.getCustomerId());
        
        validateCustomerExists(policy.getCustomerId());
        
        // 기존 정책이 있으면 삭제
        travelPolicyMapper.selectByCustomerId(policy.getCustomerId())
                .ifPresent(existing -> travelPolicyMapper.delete(existing.getPolicyId()));
        
        travelPolicyMapper.insert(policy);
        return policy;
    }
    
    /**
     * 정책 수정
     */
    public TravelPolicy updatePolicy(Long policyId, TravelPolicy policyDetails) {
        log.info("수정: 정책 ID = {}", policyId);
        
        TravelPolicy existing = travelPolicyMapper.selectById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정책입니다: " + policyId));
        
        existing.setAllowedFlightClass(policyDetails.getAllowedFlightClass());
        existing.setAllowedHotelGrade(policyDetails.getAllowedHotelGrade());
        existing.setApprovalRequiredYn(policyDetails.getApprovalRequiredYn());
        existing.setApprovalLimitAmount(policyDetails.getApprovalLimitAmount());
        
        travelPolicyMapper.update(existing);
        return existing;
    }
    
    /**
     * 정책 삭제
     */
    public void deletePolicy(Long policyId) {
        log.info("삭제: 정책 ID = {}", policyId);
        
        travelPolicyMapper.selectById(policyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정책입니다: " + policyId));
        
        travelPolicyMapper.delete(policyId);
    }
    
    private void validateCustomerExists(Long customerId) {
        customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
    }
}
