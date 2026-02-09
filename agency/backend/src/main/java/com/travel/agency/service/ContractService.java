package com.travel.agency.service;

import com.travel.agency.entity.Contract;
import com.travel.agency.mapper.ContractMapper;
import com.travel.agency.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * 계약 비즈니스 로직 서비스
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractService {
    
    private final ContractMapper contractMapper;
    private final CustomerMapper customerMapper;
    
    /**
     * 고객사별 계약 조회
     */
    public List<Contract> getContractsByCustomerId(Long customerId) {
        log.info("조회: 고객사 {} 계약", customerId);
        validateCustomerExists(customerId);
        return contractMapper.selectByCustomerId(customerId);
    }
    
    /**
     * 계약 ID로 조회
     */
    public Optional<Contract> getContractById(Long contractId) {
        log.info("조회: 계약 ID = {}", contractId);
        return contractMapper.selectById(contractId);
    }
    
    /**
     * 고객사의 활성 계약 조회
     */
    public Optional<Contract> getActiveContract(Long customerId) {
        log.info("조회: 고객사 {} 활성 계약", customerId);
        validateCustomerExists(customerId);
        return contractMapper.selectActiveContractByCustomerId(customerId);
    }
    
    /**
     * 계약 등록
     */
    public Contract createContract(Contract contract) {
        log.info("등록: 고객사 {} 계약", contract.getCustomerId());
        
        validateCustomerExists(contract.getCustomerId());
        
        contractMapper.insert(contract);
        return contract;
    }
    
    /**
     * 계약 정보 수정
     */
    public Contract updateContract(Long contractId, Contract contractDetails) {
        log.info("수정: 계약 ID = {}", contractId);
        
        Contract existing = contractMapper.selectById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계약입니다: " + contractId));
        
        existing.setStartDate(contractDetails.getStartDate());
        existing.setEndDate(contractDetails.getEndDate());
        existing.setContractType(contractDetails.getContractType());
        existing.setDiscountRate(contractDetails.getDiscountRate());
        existing.setBillingCycle(contractDetails.getBillingCycle());
        existing.setCreditLimit(contractDetails.getCreditLimit());
        
        contractMapper.update(existing);
        return existing;
    }
    
    /**
     * 계약 삭제
     */
    public void deleteContract(Long contractId) {
        log.info("삭제: 계약 ID = {}", contractId);
        
        contractMapper.selectById(contractId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계약입니다: " + contractId));
        
        contractMapper.delete(contractId);
    }
    
    private void validateCustomerExists(Long customerId) {
        customerMapper.selectById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객사입니다: " + customerId));
    }
}
