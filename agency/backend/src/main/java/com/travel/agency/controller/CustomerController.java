package com.travel.agency.controller;

import com.travel.agency.entity.Customer;
import com.travel.agency.service.CustomerService;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 고객사 REST API Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true", maxAge = 3600)
@Tag(name = "고객사 관리", description = "고객사(법인) 정보 관리 API")
public class CustomerController {
    
    private final CustomerService customerService;
    
    /**
     * 모든 고객사 조회
     * GET /api/customers
     */
    @GetMapping
    @Operation(summary = "모든 고객사 조회", description = "등록된 모든 고객사 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = Customer.class)))
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.info("GET: 모든 고객사 조회");
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    /**
     * 고객사 ID로 조회
     * GET /api/customers/{customerId}
     */
    @GetMapping("/{customerId}")
    @Operation(summary = "고객사 상세 조회", description = "고객사 ID를 이용하여 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Customer> getCustomerById(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("GET: 고객사 조회, customerId = {}", customerId);
        var result = customerService.getCustomerById(customerId);
        log.info("고객사 조회 결과: {}", result);
        return result
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 사업자번호로 조회
     * GET /api/customers/search/business-number?businessNumber=1234567890
     */
    @GetMapping("/search/business-number")
    @Operation(summary = "사업자번호로 조회", description = "사업자번호를 이용하여 고객사를 검색합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Customer> getCustomerByBusinessNumber(
            @Parameter(description = "사업자번호", example = "1234567890")
            @RequestParam String businessNumber) {
        log.info("GET: 사업자번호 조회, businessNumber = {}", businessNumber);
        return customerService.getCustomerByBusinessNumber(businessNumber)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * 고객사 등록
     * POST /api/customers
     */
    @PostMapping
    @Operation(summary = "고객사 등록", description = "새로운 고객사(법인)를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "등록 실패 (사업자번호 중복 등)")
    })
    public ResponseEntity<Customer> createCustomer(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "등록할 고객사 정보", required = true)
            @RequestBody Customer customer) {
        log.info("POST: 고객사 등록, companyName = {}", customer.getCompanyName());
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (IllegalArgumentException e) {
            log.error("고객사 등록 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 고객사 정보 수정
     * PUT /api/customers/{customerId}
     */
    @PutMapping("/{customerId}")
    @Operation(summary = "고객사 정보 수정", description = "고객사의 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공"),
            @ApiResponse(responseCode = "400", description = "수정 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Customer> updateCustomer(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 고객사 정보", required = true)
            @RequestBody Customer customerDetails) {
        log.info("PUT: 고객사 수정, customerId = {}", customerId);
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerId, customerDetails);
            return ResponseEntity.ok(updatedCustomer);
        } catch (IllegalArgumentException e) {
            log.error("고객사 수정 실패: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * 고객사 삭제
     * DELETE /api/customers/{customerId}
     */
    @DeleteMapping("/{customerId}")
    @Operation(summary = "고객사 삭제", description = "고객사를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId) {
        log.info("DELETE: 고객사 삭제, customerId = {}", customerId);
        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            log.error("고객사 삭제 실패: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 프로필 이미지 업로드
     * POST /api/customers/{customerId}/upload-image
     */
    @PostMapping("/{customerId}/upload-image")
    @Operation(summary = "프로필 이미지 업로드", description = "고객사의 프로필 이미지를 업로드합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "업로드 성공"),
            @ApiResponse(responseCode = "400", description = "업로드 실패"),
            @ApiResponse(responseCode = "404", description = "고객사 없음")
    })
    public ResponseEntity<Map<String, String>> uploadProfileImage(
            @Parameter(description = "고객사 ID", example = "1")
            @PathVariable Long customerId,
            @RequestParam("file") MultipartFile file) {
        log.info("POST: 프로필 이미지 업로드, customerId = {}", customerId);

        try {
            // 파일 검증
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // 파일 크기 제한 (5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "파일 크기가 너무 큽니다 (최대 5MB)");
                return ResponseEntity.badRequest().body(response);
            }

            // 파일 타입 검증
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "이미지 파일만 업로드 가능합니다");
                return ResponseEntity.badRequest().body(response);
            }

            // 파일 저장 디렉토리 생성
            String uploadDir = "uploads/profiles";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일명 생성 (중복 방지)
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : ".jpg";
            String newFilename = UUID.randomUUID().toString() + fileExtension;
            Path filePath = uploadPath.resolve(newFilename);

            // 파일 저장
            Files.write(filePath, file.getBytes());
            log.info("프로필 이미지 저장: {}", filePath.toString());

            // 응답 생성
            Map<String, String> response = new HashMap<>();
            response.put("message", "이미지가 성공적으로 업로드되었습니다");
            response.put("filename", newFilename);
            response.put("url", "/uploads/profiles/" + newFilename);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("프로필 이미지 업로드 실패: {}", e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("error", "파일 업로드 중 오류가 발생했습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
