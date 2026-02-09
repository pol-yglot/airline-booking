package com.travel.agency.mapper;

import com.travel.agency.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
 * 고객사 MyBatis Mapper
 */
@Mapper
public interface CustomerMapper {
    
    /**
     * 모든 고객사 조회
     */
    List<Customer> selectAll();
    
    /**
     * 고객사 ID로 조회
     */
    Optional<Customer> selectById(Long customerId);
    
    /**
     * 고객사 등록
     */
    int insert(Customer customer);
    
    /**
     * 고객사 수정
     */
    int update(Customer customer);
    
    /**
     * 고객사 삭제
     */
    int delete(Long customerId);
    
    /**
     * 사업자번호로 조회
     */
    Optional<Customer> selectByBusinessNumber(String businessNumber);
}
