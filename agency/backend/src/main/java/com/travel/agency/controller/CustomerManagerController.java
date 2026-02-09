package com.travel.agency.controller;

import com.travel.agency.entity.CustomerManager;
import com.travel.agency.service.CustomerManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * 고객사 담당자 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customers/{customerId}/managers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "고객사 담당자 관리", description = "고객사 담당자 정보 관리 API")
public class CustomerManagerController {
    
    private final CustomerManagerService customerManagerService;
    
    /**
     * 고객사별 담당자 조회
     * GET /api/customers/{customerId}/managers
     */
    @GetMapping
    @Operation(summary = "담당자 목록 조회", description = "고객사별 담당자 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<CustomerManager>> getManagersByCustomerId(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 담당자 조회", customerId);
        try {
            List<CustomerManager> managers = customerManagerService.getManagersByCustomerId(customerId);
            return ResponseEntity.ok(managers);
        } catch (IllegalArgumentException e) {
            log.error("담당자 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 담당자 ID로 조회
     * GET /api/customers/{customerId}/managers/{managerId}
     */
    @GetMapping("/{managerId}")
    @Operation(summary = "담당자 상세 조회", description = "담당자 ID로 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "담당자 없음")
    })
    public ResponseEntity<CustomerManager> getManagerById(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "담당자 ID", example = "1")
            @PathVariable Long managerId) {
        log.info("GET: 담당자 조회, customerId = {}, managerId = {}", customerId, managerId);
        return customerManagerService.getManagerById(managerId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 담당자 등록
     * POST /api/customers/{customerId}/managers
     */
    @PostMapping
    @Operation(summary = "담당자 등록", description = "고객사에 새로운 담당자를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<CustomerManager> createManager(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 담당자 정보", required = true)
            @RequestBody CustomerManager manager) {
        log.info("POST: 담당자 등록, customerId = {}, name = {}", customerId, manager.getName());
        try {
            CustomerManager createdManager = customerManagerService.createManager(customerId, manager);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdManager);
        } catch (IllegalArgumentException e) {
            log.error("담당자 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 담당자 정보 수정
     * PUT /api/customers/{customerId}/managers/{managerId}
     */
    @PutMapping("/{managerId}")
    @Operation(summary = "담당자 정보 수정", description = "담당자 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 실패"),
            @ApiResponse(responseCode = "404", description = "담당자 없음")
    })
    public ResponseEntity<CustomerManager> updateManager(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "담당자 ID", example = "1")
            @PathVariable Long managerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 담당자 정보", required = true)
            @RequestBody CustomerManager managerDetails) {
        log.info("PUT: 담당자 수정, customerId = {}, managerId = {}", customerId, managerId);
        try {
            CustomerManager updatedManager = customerManagerService.updateManager(managerId, managerDetails);
            return ResponseEntity.ok(updatedManager);
        } catch (IllegalArgumentException e) {
            log.error("담당자 수정 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 담당자 삭제
     * DELETE /api/customers/{customerId}/managers/{managerId}
     */
    @DeleteMapping("/{managerId}")
    @Operation(summary = "담당자 삭제", description = "담당자를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "담당자 없음")
    })
    public ResponseEntity<Void> deleteManager(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "담당자 ID", example = "1")
            @PathVariable Long managerId) {
        log.info("DELETE: 담당자 삭제, customerId = {}, managerId = {}", customerId, managerId);
        try {
            customerManagerService.deleteManager(managerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("담당자 삭제 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
