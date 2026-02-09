package com.travel.agency.mapper;

import com.travel.agency.entity.CustomerManager;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
 * 고객사 담당자 MyBatis Mapper
 */
@Mapper
public interface CustomerManagerMapper {
    
    /**
     * 고객사별 담당자 조회
     */
    List<CustomerManager> selectByCustomerId(Long customerId);
    
    /**
     * 담당자 ID로 조회
     */
    Optional<CustomerManager> selectById(Long managerId);
    
    /**
     * 담당자 등록
     */
    int insert(CustomerManager customerManager);
    
    /**
     * 담당자 수정
     */
    int update(CustomerManager customerManager);
    
    /**
     * 담당자 삭제
     */
    int delete(Long managerId);
    
    /**
     * 이메일로 조회 (중복 확인용)
     */
    Optional<CustomerManager> selectByEmail(String email);
}
