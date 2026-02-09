package com.travel.agency.service;

import com.travel.agency.entity.CustomerManager;
import com.travel.agency.mapper.CustomerManagerMapper;
import com.travel.agency.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 고객사 담당자 비즈니스 로직 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerManagerService {
    
    private final CustomerManagerMapper customerManagerMapper;
    private final CustomerMapper customerMapper;
    
    /**
     * 고객사별 담당자 조회
     */
    public List<CustomerManager> getManagersByCustomerId(Long customerId) {
        log.info("조회: 고객사 {} 담당자", customerId);
        validateCustomerExists(customerId);
        return customerManagerMapper.selectByCustomerId(customerId);
    }
    
    /**
     * 담당자 ID로 조회
     */
    public Optional<CustomerManager> getManagerById(Long managerId) {
        log.info("조회: 담당자 ID = {}", managerId);
        return customerManagerMapper.selectById(managerId);
    }
    
    /**
     * 담당자 등록
     */
    public CustomerManager createManager(Long customerId, CustomerManager manager) {
        log.info("등록: 담당자 = {}", manager.getName());
        
        validateCustomerExists(customerId);
        
        // 이메일 중복 확인
        if (customerManagerMapper.selectByEmail(manager.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 이메일입니다: " + manager.getEmail());
        }
        
        manager.setCustomerId(customerId);
        if (manager.getMainYn() == null) {
            manager.setMainYn('N');
        }
        
        customerManagerMapper.insert(manager);
        return manager;
    }
    
    /**
     * 담당자 정보 수정
     */
    public CustomerManager updateManager(Long managerId, CustomerManager managerDetails) {
        log.info("수정: 담당자 ID = {}", managerId);
        
        CustomerManager existing = customerManagerMapper.selectById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 담당자입니다: " + managerId));
        
        // 이메일 변경 시 중복 확인
        if (!existing.getEmail().equals(managerDetails.getEmail())) {
            if (customerManagerMapper.selectByEmail(managerDetails.getEmail()).isPresent()) {
                throw new IllegalArgumentException("이미 등록된 이메일입니다: " + managerDetails.getEmail());
            }
        }
        
        existing.setName(managerDetails.getName());
        existing.setEmail(managerDetails.getEmail());
        existing.setPhone(managerDetails.getPhone());
        existing.setDepartment(managerDetails.getDepartment());
        existing.setPosition(managerDetails.getPosition());
        existing.setMainYn(managerDetails.getMainYn());
        
        customerManagerMapper.update(existing);
        return existing;
    }
    
    /**
     * 담당자 삭제
     */
    public void deleteManager(Long managerId) {
        log.info("삭제: 담당자 ID = {}", managerId);
        
        customerManagerMapper.selectById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 담당자입니다: " + managerId));
        
        customerManagerMapper.delete(managerId);
    }
    
    private void validateCustomerExists(Long customerId) {
        customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
    }
}
