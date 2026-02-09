package com.travel.agency.controller;

import com.travel.agency.entity.TravelPolicy;
import com.travel.agency.service.TravelPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 출장 정책 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customers/{customerId}/travel-policy")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "출장 정책 관리", description = "출장 정책 정보 관리 API")
public class TravelPolicyController {
    
    private final TravelPolicyService travelPolicyService;
    
    /**
     * 고객사별 출장 정책 조회
     * GET /api/customers/{customerId}/travel-policy
     */
    @GetMapping
    @Operation(summary = "출장 정책 조회", description = "고객사의 출장 정책을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "정책 없음")
    })
    public ResponseEntity<TravelPolicy> getPolicyByCustomerId(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 출장 정책 조회", customerId);
        try {
            return travelPolicyService.getPolicyByCustomerId(customerId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            log.error("출장 정책 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 정책 ID로 조회
     * GET /api/customers/{customerId}/travel-policy/{policyId}
     */
    @GetMapping("/{policyId}")
    @Operation(summary = "출장 정책 상세 조회", description = "정책 ID로 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "정책 없음")
    })
    public ResponseEntity<TravelPolicy> getPolicyById(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "정책 ID", example = "1")
            @PathVariable Long policyId) {
        log.info("GET: 출장 정책 조회, customerId = {}, policyId = {}", customerId, policyId);
        return travelPolicyService.getPolicyById(policyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 정책 등록
     * POST /api/customers/{customerId}/travel-policy
     */
    @PostMapping
    @Operation(summary = "출장 정책 등록", description = "새로운 출장 정책을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<TravelPolicy> createPolicy(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 정책 정보", required = true)
            @RequestBody TravelPolicy policy) {
        log.info("POST: 출장 정책 등록, customerId = {}", customerId);
        try {
            policy.setCustomerId(customerId);
            TravelPolicy createdPolicy = travelPolicyService.createPolicy(policy);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicy);
        } catch (IllegalArgumentException e) {
            log.error("출장 정책 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 정책 수정
     * PUT /api/customers/{customerId}/travel-policy/{policyId}
     */
    @PutMapping("/{policyId}")
    @Operation(summary = "출장 정책 수정", description = "출장 정책을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 실패"),
            @ApiResponse(responseCode = "404", description = "정책 없음")
    })
    public ResponseEntity<TravelPolicy> updatePolicy(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "정책 ID", example = "1")
            @PathVariable Long policyId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 정책 정보", required = true)
            @RequestBody TravelPolicy policyDetails) {
        log.info("PUT: 출장 정책 수정, customerId = {}, policyId = {}", customerId, policyId);
        try {
            TravelPolicy updatedPolicy = travelPolicyService.updatePolicy(policyId, policyDetails);
            return ResponseEntity.ok(updatedPolicy);
        } catch (IllegalArgumentException e) {
            log.error("출장 정책 수정 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 정책 삭제
     * DELETE /api/customers/{customerId}/travel-policy/{policyId}
     */
    @DeleteMapping("/{policyId}")
    @Operation(summary = "출장 정책 삭제", description = "출장 정책을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "정책 없음")
    })
    public ResponseEntity<Void> deletePolicy(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "정책 ID", example = "1")
            @PathVariable Long policyId) {
        log.info("DELETE: 출장 정책 삭제, customerId = {}, policyId = {}", customerId, policyId);
        try {
            travelPolicyService.deletePolicy(policyId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("출장 정책 삭제 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
