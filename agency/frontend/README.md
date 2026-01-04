### 최소 설치 패키지 

- react
  ```npm install react react-dom```
- TypeScript (Vite + React TS용)
  ```TypeScript (Vite + React TS용)```
- Vite React 플러그인
  ```npm install -D @vitejs/plugin-react```
- React Router (페이지 라우팅)
  ```npm install react-router-dom```
- axios (백엔드 연동 핵심)
  ```npm install axios```
- 타입 선언
  ```npm install -D @types/react @types/react-dom```


### 🔎 프론트 구조 설계 원칙

| 폴더        | 의미            |
|-------------|-----------------|
| pages       | URL 단위 화면   |
| components  | 재사용 UI       |
| api         | 서버 통신만     |
| hooks       | 로직 재사용     |
| store       | 로그인 상태    |
| styles      | 디자인 통합    |
