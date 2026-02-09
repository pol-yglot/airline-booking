package com.travel.agency.service;

import com.travel.agency.entity.BusinessTraveler;
import com.travel.agency.mapper.BusinessTravelerMapper;
import com.travel.agency.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 출장자 비즈니스 로직 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BusinessTravelerService {
    
    private final BusinessTravelerMapper businessTravelerMapper;
    private final CustomerMapper customerMapper;
    
    /**
     * 고객사별 출장자 조회
     */
    public List<BusinessTraveler> getTravelersByCustomerId(Long customerId) {
        log.info("조회: 고객사 {} 출장자", customerId);
        validateCustomerExists(customerId);
        return businessTravelerMapper.selectByCustomerId(customerId);
    }
    
    /**
     * 출장자 ID로 조회
     */
    public Optional<BusinessTraveler> getTravelerById(Long travelerId) {
        log.info("조회: 출장자 ID = {}", travelerId);
        return businessTravelerMapper.selectById(travelerId);
    }
    
    /**
     * 활성화된 출장자만 조회
     */
    public List<BusinessTraveler> getActiveTravelers(Long customerId) {
        log.info("조회: 고객사 {} 활성 출장자", customerId);
        validateCustomerExists(customerId);
        return businessTravelerMapper.selectActiveByCustomerId(customerId);
    }
    
    /**
     * 출장자 등록
     */
    public BusinessTraveler createTraveler(Long customerId, BusinessTraveler traveler) {
        log.info("등록: 출장자 = {}", traveler.getName());
        
        validateCustomerExists(customerId);
        
        // 이메일 중복 확인
        if (businessTravelerMapper.selectByEmail(traveler.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다: " + traveler.getEmail());
        }
        
        traveler.setCustomerId(customerId);
        if (traveler.getActiveYn() == null) {
            traveler.setActiveYn('Y');
        }
        
        businessTravelerMapper.insert(traveler);
        return traveler;
    }
    
    /**
     * 출장자 정보 수정
     */
    public BusinessTraveler updateTraveler(Long travelerId, BusinessTraveler travelerDetails) {
        log.info("수정: 출장자 ID = {}", travelerId);
        
        BusinessTraveler existing = businessTravelerMapper.selectById(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출장자입니다: " + travelerId));
        
        // 이메일 변경 시 중복 확인
        if (!existing.getEmail().equals(travelerDetails.getEmail())) {
            if (businessTravelerMapper.selectByEmail(travelerDetails.getEmail()).isPresent()) {
                throw new IllegalArgumentException("이미 등록된 이메일입니다: " + travelerDetails.getEmail());
            }
        }
        
        existing.setName(travelerDetails.getName());
        existing.setEmail(travelerDetails.getEmail());
        existing.setPhone(travelerDetails.getPhone());
        existing.setPosition(travelerDetails.getPosition());
        existing.setTravelGrade(travelerDetails.getTravelGrade());
        existing.setActiveYn(travelerDetails.getActiveYn());
        
        businessTravelerMapper.update(existing);
        return existing;
    }
    
    /**
     * 출장자 삭제
     */
    public void deleteTraveler(Long travelerId) {
        log.info("삭제: 출장자 ID = {}", travelerId);
        
        businessTravelerMapper.selectById(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출장자입니다: " + travelerId));
        
        businessTravelerMapper.delete(travelerId);
    }
    
    /**
     * 출장자 비활성화
     */
    public void deactivateTraveler(Long travelerId) {
        log.info("비활성화: 출장자 ID = {}", travelerId);
        
        BusinessTraveler traveler = businessTravelerMapper.selectById(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 출장자입니다: " + travelerId));
        
        traveler.setActiveYn('N');
        businessTravelerMapper.update(traveler);
    }
    
    private void validateCustomerExists(Long customerId) {
        customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
    }
}
