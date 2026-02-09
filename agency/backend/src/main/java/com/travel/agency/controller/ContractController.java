package com.travel.agency.controller;

import com.travel.agency.entity.Contract;
import com.travel.agency.service.ContractService;
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
 * 계약 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customers/{customerId}/contracts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "계약 관리", description = "계약 정보 관리 API")
public class ContractController {
    
    private final ContractService contractService;
    
    /**
     * 고객사별 계약 조회
     * GET /api/customers/{customerId}/contracts
     */
    @GetMapping
    @Operation(summary = "계약 목록 조회", description = "고객사의 모든 계약을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<List<Contract>> getContractsByCustomerId(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 계약 조회", customerId);
        try {
            List<Contract> contracts = contractService.getContractsByCustomerId(customerId);
            return ResponseEntity.ok(contracts);
        } catch (IllegalArgumentException e) {
            log.error("계약 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 활성 계약 조회
     * GET /api/customers/{customerId}/contracts/active
     */
    @GetMapping("/active")
    @Operation(summary = "활성 계약 조회", description = "현재 유효한 활성 계약을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "활성 계약 없음")
    })
    public ResponseEntity<Contract> getActiveContract(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 {} 활성 계약 조회", customerId);
        try {
            return contractService.getActiveContract(customerId)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            log.error("활성 계약 조회 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 계약 ID로 조회
     * GET /api/customers/{customerId}/contracts/{contractId}
     */
    @GetMapping("/{contractId}")
    @Operation(summary = "계약 상세 조회", description = "계약 ID로 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "계약 없음")
    })
    public ResponseEntity<Contract> getContractById(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "계약 ID", example = "1")
            @PathVariable Long contractId) {
        log.info("GET: 계약 조회, customerId = {}, contractId = {}", customerId, contractId);
        return contractService.getContractById(contractId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 계약 등록
     * POST /api/customers/{customerId}/contracts
     */
    @PostMapping
    @Operation(summary = "계약 등록", description = "새로운 계약을 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Contract> createContract(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 계약 정보", required = true)
            @RequestBody Contract contract) {
        log.info("POST: 계약 등록, customerId = {}, contractType = {}", customerId, contract.getContractType());
        try {
            contract.setCustomerId(customerId);
            Contract createdContract = contractService.createContract(contract);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
        } catch (IllegalArgumentException e) {
            log.error("계약 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 계약 정보 수정
     * PUT /api/customers/{customerId}/contracts/{contractId}
     */
    @PutMapping("/{contractId}")
    @Operation(summary = "계약 정보 수정", description = "계약 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 실패"),
            @ApiResponse(responseCode = "404", description = "계약 없음")
    })
    public ResponseEntity<Contract> updateContract(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "계약 ID", example = "1")
            @PathVariable Long contractId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 계약 정보", required = true)
            @RequestBody Contract contractDetails) {
        log.info("PUT: 계약 수정, customerId = {}, contractId = {}", customerId, contractId);
        try {
            Contract updatedContract = contractService.updateContract(contractId, contractDetails);
            return ResponseEntity.ok(updatedContract);
        } catch (IllegalArgumentException e) {
            log.error("계약 수정 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 계약 삭제
     * DELETE /api/customers/{customerId}/contracts/{contractId}
     */
    @DeleteMapping("/{contractId}")
    @Operation(summary = "계약 삭제", description = "계약을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "계약 없음")
    })
    public ResponseEntity<Void> deleteContract(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @Parameter(description = "계약 ID", example = "1")
            @PathVariable Long contractId) {
        log.info("DELETE: 계약 삭제, customerId = {}, contractId = {}", customerId, contractId);
        try {
            contractService.deleteContract(contractId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("계약 삭제 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
