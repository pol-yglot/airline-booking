package com.travel.agency.controller;

import com.travel.agency.entity.BusinessTraveler;
import com.travel.agency.service.BusinessTravelerService;
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
import java.util.List;

/**
 * 출장자 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customers/{customerId}/travelers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "출장자 관리", description = "출장자 정보 관리 API")
public class BusinessTravelerController {
    
    private final BusinessTravelerService businessTravelerService;
    
    /**
     * 고객사별 출장자 조회
     * GET /api/customers/{customerId}/travelers
     */
    @GetMapping
    @Operation(summary = "출장자 목록 조회", description = "고객사별 전체 출장자를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<BusinessTraveler>> getTravelersByCustomerId(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 출장자 조회", customerId);
        try {
            List<BusinessTraveler> travelers = businessTravelerService.getTravelersByCustomerId(customerId);
            return ResponseEntity.ok(travelers);
        } catch (IllegalArgumentException e) {
            log.error("출장자 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 활성화된 출장자만 조회
     * GET /api/customers/{customerId}/travelers/active
     */
    @GetMapping("/active")
    @Operation(summary = "활성 출장자 조회", description = "고객사의 활성화된 출장자만 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<BusinessTraveler>> getActiveTravelers(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 활성 출장자 조회", customerId);
        try {
            List<BusinessTraveler> travelers = businessTravelerService.getActiveTravelers(customerId);
            return ResponseEntity.ok(travelers);
        } catch (IllegalArgumentException e) {
            log.error("활성 출장자 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 출장자 ID로 조회
     * GET /api/customers/{customerId}/travelers/{travelerId}
     */
    @GetMapping("/{travelerId}")
    @Operation(summary = "출장자 상세 조회", description = "출장자 ID로 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "출장자 없음")
    })
    public ResponseEntity<BusinessTraveler> getTravelerById(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "출장자 ID", example = "1")
            @PathVariable Long travelerId) {
        log.info("GET: 출장자 조회, customerId = {}, travelerId = {}", customerId, travelerId);
        return businessTravelerService.getTravelerById(travelerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 출장자 등록
     * POST /api/customers/{customerId}/travelers
     */
    @PostMapping
    @Operation(summary = "출장자 등록", description = "고객사에 새로운 출장자를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<BusinessTraveler> createTraveler(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 출장자 정보", required = true)
            @RequestBody BusinessTraveler traveler) {
        log.info("POST: 출장자 등록, customerId = {}, name = {}", customerId, traveler.getName());
        try {
            BusinessTraveler createdTraveler = businessTravelerService.createTraveler(customerId, traveler);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTraveler);
        } catch (IllegalArgumentException e) {
            log.error("출장자 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 출장자 정보 수정
     * PUT /api/customers/{customerId}/travelers/{travelerId}
     */
    @PutMapping("/{travelerId}")
    @Operation(summary = "출장자 정보 수정", description = "출장자 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 실패"),
            @ApiResponse(responseCode = "404", description = "출장자 없음")
    })
    public ResponseEntity<BusinessTraveler> updateTraveler(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "출장자 ID", example = "1")
            @PathVariable Long travelerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 출장자 정보", required = true)
            @RequestBody BusinessTraveler travelerDetails) {
        log.info("PUT: 출장자 수정, customerId = {}, travelerId = {}", customerId, travelerId);
        try {
            BusinessTraveler updatedTraveler = businessTravelerService.updateTraveler(travelerId, travelerDetails);
            return ResponseEntity.ok(updatedTraveler);
        } catch (IllegalArgumentException e) {
            log.error("출장자 수정 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 출장자 삭제
     * DELETE /api/customers/{customerId}/travelers/{travelerId}
     */
    @DeleteMapping("/{travelerId}")
    @Operation(summary = "출장자 삭제", description = "출장자를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "출장자 없음")
    })
    public ResponseEntity<Void> deleteTraveler(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "출장자 ID", example = "1")
            @PathVariable Long travelerId) {
        log.info("DELETE: 출장자 삭제, customerId = {}, travelerId = {}", customerId, travelerId);
        try {
            businessTravelerService.deleteTraveler(travelerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("출장자 삭제 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 출장자 비활성화
     * PATCH /api/customers/{customerId}/travelers/{travelerId}/deactivate
     */
    @PatchMapping("/{travelerId}/deactivate")
    @Operation(summary = "출장자 비활성화", description = "출장자를 비활성화합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "비활성화 성공"),
            @ApiResponse(responseCode = "404", description = "출장자 없음")
    })
    public ResponseEntity<Void> deactivateTraveler(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "출장자 ID", example = "1")
            @PathVariable Long travelerId) {
        log.info("PATCH: 출장자 비활성화, customerId = {}, travelerId = {}", customerId, travelerId);
        try {
            businessTravelerService.deactivateTraveler(travelerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("출장자 비활성화 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
