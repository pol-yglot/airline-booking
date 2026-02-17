# ✈️ Travel Agency Reservation System

항공사 예약 서비스를 위한 **여행사 종합 예약·관리 플랫폼**입니다.
관리자 및 내부 사용자를 대상으로 한 **B2B 여행 예약 시스템**을 목표로 합니다.

---

## 📋 프로젝트 개요

* 항공사 예약 및 여행 상품을 통합 관리하는 웹 기반 시스템
* 인증/권한 기반 관리자 기능 제공
* 프론트엔드(SPA)와 백엔드(API 서버) 분리 아키텍처

---

## 🚀 빠른 시작

### 🔹 백엔드 실행 (Spring Boot)

```bash
# 프로젝트 루트에서
cd backend

# 방법 1: Maven Wrapper 사용 (권장)
.\mvnw spring-boot:run

# 방법 2: JAR 빌드 후 실행
.\mvnw clean package -DskipTests
java -jar target/agency-0.0.1-SNAPSHOT.jar
```

**백엔드 서버**: http://localhost:8082
**Swagger UI**: http://localhost:8082/swagger-ui.html
**H2 Console**: http://localhost:8082/h2-console

### 🔹 프론트엔드 실행 (React)

```bash
# 프로젝트 루트에서
cd frontend

# 의존성 설치
npm install

# 개발 서버 실행
npm run dev

# 프로덕션 빌드
npm run build
```

**프론트엔드 서버**: http://localhost:5173

---

## 🏗️ 기술 스택

### 🔹 Backend

| 항목              | 기술                |
| --------------- | ----------------- |
| Framework       | Spring Boot 3.5.9 |
| Language        | Java 17           |
| ORM             | MyBatis 3.0.5     |
| Database        | H2 (In-Memory)    |
| Template Engine | Thymeleaf         |
| Security        | Spring Security 6 |
| API Doc         | Springdoc OpenAPI 2.0.4 |
| Build Tool      | Maven             |

---

### 🔹 Frontend

| 항목         | 기술                      |
| ---------- | ----------------------- |
| Framework  | React 19.2.0            |
| Language   | TypeScript 5.9.3        |
| Bundler    | Vite (rolldown-vite)    |
| Router     | React Router DOM 7.11.0 |
| API Client | Axios 1.13.2            |
| Styling    | Tailwind CSS 3.4.17     |
| Linter     | ESLint                  |

---

## 📁 프로젝트 구조

```text
project-root
 ├─ backend    # Spring Boot API 서버
 └─ frontend   # React SPA 클라이언트
```

---

## 🧪 API 테스트

### Swagger UI
백엔드 실행 후 다음 주소에서 API 문서 확인 및 테스트 가능:
- **URL**: http://localhost:8082/swagger-ui.html
- **API JSON**: http://localhost:8082/v3/api-docs

### 테스트 데이터
H2 데이터베이스에 다음 테스트 데이터가 자동으로 로드됩니다:

**고객사 (3개)**
- 삼성전자 (사업자번호: 1234567890)
- 현대자동차 (사업자번호: 0987654321)
- LG전자 (사업자번호: 1122334455)

**관련 데이터**
- 고객사 담당자 (4명)
- 출장자 (5명)
- 계약 정보 (3개)
- 출장 정책 (3개)

### API 엔드포인트 예시
```bash
# 모든 고객사 조회
GET http://localhost:8082/api/customers

# 고객사 상세 조회
GET http://localhost:8082/api/customers/1

# 고객사 담당자 조회
GET http://localhost:8082/api/customers/1/managers

# 출장자 조회
GET http://localhost:8082/api/customers/1/travelers

# 계약 조회
GET http://localhost:8082/api/customers/1/contracts

# 출장 정책 조회
GET http://localhost:8082/api/customers/1/travel-policy
```

---

## 🎯 주요 기능 (TODO)

* ✅ Spring Security 기반 로그인 / 로그아웃
* ✅ 회원 관리

  * 회원 조회
  * 회원 등록
  * 회원 수정
  * 회원 탈퇴
* ✅ 여행 상품 관리

  * 상품 조회
  * 상품 등록
  * 상품 수정
  * 상품 삭제

> ※ 향후 항공 예약, 정산, 결제 기능 확장 예정

---

## 🔌 아키텍처

* **Server**

  * Spring Boot API Server (Port: `8082`)
  * Swagger/OpenAPI 문서 지원
* **Client**

  * React SPA + Vite (Port: `5173`)
* **API 통신**

  * Axios 기반 REST API
  * CORS 설정 적용
* **인증**

  * Spring Security
  * Thymeleaf 로그인 페이지 연동
* **UI**

  * React Router 기반 페이지 라우팅
  * Tailwind CSS 반응형 UI 구성

---

## 💡 특징

* 프론트/백엔드 분리된 **모던 웹 아키텍처**
* TypeScript 기반 **타입 안정성 확보**
* Tailwind CSS 유틸리티 클래스 기반 빠른 UI 구성
* H2 In-Memory DB 사용으로 **개발 및 테스트 환경 간소화**
* 확장 가능한 구조 (항공 예약, 결제, 정산 도메인 추가 용이)

---

## 테스트 데이터 

### 계정 
- ID : admin
- PW : admin123

### 서버정보 
```
nslookup chamitour.kr
Server:         127.0.0.53
Address:        127.0.0.53#53

Non-authoritative answer:
Name:   chamitour.kr
Address: 139.150.11.208
```