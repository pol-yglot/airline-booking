package com.travel.agency.service;

import com.travel.agency.entity.Customer;
import com.travel.agency.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 고객사 비즈니스 로직 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    
    private final CustomerMapper customerMapper;
    
    /**
     * 모든 고객사 조회
     */
    public List<Customer> getAllCustomers() {
        log.info("조회: 모든 고객사");
        return customerMapper.selectAll();
    }
    
    /**
     * 고객사 ID로 조회
     */
    public Optional<Customer> getCustomerById(Long customerId) {
        log.info("조회: 고객사 ID = {}", customerId);
        return customerMapper.selectById(customerId);
    }
    
    /**
     * 사업자번호로 조회
     */
    public Optional<Customer> getCustomerByBusinessNumber(String businessNumber) {
        log.info("조회: 사업자번호 = {}", businessNumber);
        return customerMapper.selectByBusinessNumber(businessNumber);
    }
    
    /**
     * 고객사 등록
     */
    public Customer createCustomer(Customer customer) {
        log.info("등록: 고객사 = {}", customer.getCompanyName());
        
        // 사업자번호 중복 확인
        if (customerMapper.selectByBusinessNumber(customer.getBusinessNumber()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사업자번호입니다: " + customer.getBusinessNumber());
        }
        
        customer.setStatus("ACTIVE");
        customerMapper.insert(customer);
        return customer;
    }
    
    /**
     * 고객사 정보 수정
     */
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        log.info("수정: 고객사 ID = {}", customerId);
        
        Customer existing = customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
        
        // 사업자번호 변경 시 중복 확인
        if (!existing.getBusinessNumber().equals(customerDetails.getBusinessNumber())) {
            if (customerMapper.selectByBusinessNumber(customerDetails.getBusinessNumber()).isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 사업자번호입니다: " + customerDetails.getBusinessNumber());
            }
        }
        
        existing.setCompanyName(customerDetails.getCompanyName());
        existing.setBusinessNumber(customerDetails.getBusinessNumber());
        existing.setCompanyType(customerDetails.getCompanyType());
        existing.setIndustry(customerDetails.getIndustry());
        existing.setEmployeeCount(customerDetails.getEmployeeCount());
        existing.setStatus(customerDetails.getStatus());
        
        customerMapper.update(existing);
        return existing;
    }
    
    /**
     * 고객사 삭제
     */
    public void deleteCustomer(Long customerId) {
        log.info("삭제: 고객사 ID = {}", customerId);
        
        customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
        
        customerMapper.delete(customerId);
    }
}
