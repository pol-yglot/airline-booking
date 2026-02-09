-- ===================================
-- 기업출장 전문 여행사 데이터베이스 스키마
-- ===================================

-- 1. 고객사 테이블
CREATE TABLE IF NOT EXISTS customer (
    customer_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_name VARCHAR(255) NOT NULL,
    business_number VARCHAR(20) NOT NULL UNIQUE,
    company_type VARCHAR(50),
    industry VARCHAR(100),
    employee_count INT,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. 고객사 담당자 테이블
CREATE TABLE IF NOT EXISTS customer_manager (
    manager_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    department VARCHAR(100),
    position VARCHAR(100),
    main_yn CHAR(1) DEFAULT 'N',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- 3. 출장자 테이블
CREATE TABLE IF NOT EXISTS business_traveler (
    traveler_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    position VARCHAR(100),
    travel_grade VARCHAR(50),
    active_yn CHAR(1) DEFAULT 'Y',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- 4. 계약 테이블
CREATE TABLE IF NOT EXISTS contract (
    contract_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    contract_type VARCHAR(50),
    discount_rate DECIMAL(5, 2),
    billing_cycle VARCHAR(20),
    credit_limit DECIMAL(15, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- 5. 출장 정책 테이블
CREATE TABLE IF NOT EXISTS travel_policy (
    policy_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL UNIQUE,
    allowed_flight_class VARCHAR(50),
    allowed_hotel_grade VARCHAR(50),
    approval_required_yn CHAR(1) DEFAULT 'Y',
    approval_limit_amount DECIMAL(15, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- 6. 예약 테이블
CREATE TABLE IF NOT EXISTS booking (
    booking_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    traveler_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    travel_start_date DATE NOT NULL,
    travel_end_date DATE NOT NULL,
    booking_status VARCHAR(50) DEFAULT 'PENDING',
    total_amount DECIMAL(15, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (traveler_id) REFERENCES business_traveler(traveler_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- 7. 청구서 테이블
CREATE TABLE IF NOT EXISTS invoice (
    invoice_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id BIGINT NOT NULL,
    invoice_date DATE NOT NULL,
    invoice_amount DECIMAL(15, 2),
    payment_status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- ===================================
-- 인덱스 설정
-- ===================================
CREATE INDEX IF NOT EXISTS idx_customer_manager_customer_id ON customer_manager(customer_id);
CREATE INDEX IF NOT EXISTS idx_business_traveler_customer_id ON business_traveler(customer_id);
CREATE INDEX IF NOT EXISTS idx_contract_customer_id ON contract(customer_id);
CREATE INDEX IF NOT EXISTS idx_booking_traveler_id ON booking(traveler_id);
CREATE INDEX IF NOT EXISTS idx_booking_customer_id ON booking(customer_id);
CREATE INDEX IF NOT EXISTS idx_invoice_customer_id ON invoice(customer_id);

-- ===================================
-- 기본 데이터 삽입
-- ===================================
-- 기본 고객사 (ID: 1)
MERGE INTO customer KEY(customer_id) 
VALUES (1, 'BizTrip Co., Ltd.', '1234567890', '대기업', 'IT/Software', 500, 'ACTIVE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
