package com.travel.agency.mapper;

import com.travel.agency.entity.BusinessTraveler;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Optional;

/**
 * 출장자 MyBatis Mapper
 */
@Mapper
public interface BusinessTravelerMapper {
    
    /**
     * 고객사별 출장자 조회
     */
    List<BusinessTraveler> selectByCustomerId(Long customerId);
    
    /**
     * 출장자 ID로 조회
     */
    Optional<BusinessTraveler> selectById(Long travelerId);
    
    /**
     * 출장자 등록
     */
    int insert(BusinessTraveler businessTraveler);
    
    /**
     * 출장자 수정
     */
    int update(BusinessTraveler businessTraveler);
    
    /**
     * 출장자 삭제
     */
    int delete(Long travelerId);
    
    /**
     * 이메일로 조회 (중복 확인용)
     */
    Optional<BusinessTraveler> selectByEmail(String email);
    
    /**
     * 활성화된 출장자만 조회
     */
    List<BusinessTraveler> selectActiveByCustomerId(Long customerId);
}
