package com.travel.agency.mapper;

import com.travel.agency.entity.Contract;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
 * 계약 MyBatis Mapper
 */
@Mapper
public interface ContractMapper {
    
    /**
     * 고객사별 계약 조회
     */
    List<Contract> selectByCustomerId(Long customerId);
    
    /**
     * 계약 ID로 조회
     */
    Optional<Contract> selectById(Long contractId);
    
    /**
     * 계약 등록
     */
    int insert(Contract contract);
    
    /**
     * 계약 수정
     */
    int update(Contract contract);
    
    /**
     * 계약 삭제
     */
    int delete(Long contractId);
    
    /**
     * 고객사의 활성 계약 조회
     */
    Optional<Contract> selectActiveContractByCustomerId(Long customerId);
}
