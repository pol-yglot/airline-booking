## 🎯 진행 상황

### ✅ 완료된 항목
- Spring Security 기반 로그인 / 로그아웃
- JWT 인증 (JwtTokenProvider, JwtAuthenticationFilter)
- 회원 관리 (조회, 등록, 수정, 탈퇴)
- 여행 상품 관리 (조회, 등록, 수정, 삭제)
- 프론트엔드 로그인 페이지

---

### 📌 마이페이지 (회원 관리) - 진행 중

* [ ] Create MyPage.tsx component
* [ ] Create profile image upload API & storage
* [ ] Create customer inquiry API (`/api/customer/{id}`)
* [ ] Create customer update API (`/api/customer/{id}`)
* [ ] Create customer delete API (`/api/customer/{id}`)
* [ ] Add profile image preview
* [ ] Add form validation for customer update
* [ ] Handle file upload for profile image
* [ ] Protect MyPage route (authenticated only)
* [ ] Add logout confirmation on delete account

---

## 💡 기술 스택

- **Backend**: Spring Boot 3.5.9, Java 17, MyBatis, H2, JWT (JJWT 0.12.3)
- **Frontend**: React 19.2.0, TypeScript 5.9.3, Vite, Axios, Tailwind CSS
- **인증**: JWT 토큰 (30분 만료), BCryptPasswordEncoder, CORS 설정
- **테스트 계정**: admin / admin123
