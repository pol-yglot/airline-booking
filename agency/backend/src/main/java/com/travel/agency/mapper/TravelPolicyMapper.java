package com.travel.agency.mapper;

import com.travel.agency.entity.TravelPolicy;
import org.apache.ibatis.annotations.Mapper;
import java.util.Optional;

/**
 * 출장 정책 MyBatis Mapper
 */
@Mapper
public interface TravelPolicyMapper {
    
    /**
     * 고객사 ID로 조회
     */
    Optional<TravelPolicy> selectByCustomerId(Long customerId);
    
    /**
     * 정책 ID로 조회
     */
    Optional<TravelPolicy> selectById(Long policyId);
    
    /**
     * 정책 등록
     */
    int insert(TravelPolicy travelPolicy);
    
    /**
     * 정책 수정
     */
    int update(TravelPolicy travelPolicy);
    
    /**
     * 정책 삭제
     */
    int delete(Long policyId);
}
