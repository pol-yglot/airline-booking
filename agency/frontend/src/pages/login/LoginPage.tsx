/**
 * LoginPage.tsx
 * ----------------------------------------
 * BizTrip 공통 레이아웃 기반 로그인 화면
 */

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { FormEvent } from "react";

import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";
import { loginAPI, saveToken } from "../../api/auth.api";

const LoginPage = () => {

    // 사용자명 상태 (username 필드)
    const [username, setUsername] = useState<string>("");

    // 비밀번호 상태
    const [password, setPassword] = useState<string>("");

    // 로딩 상태
    const [loading, setLoading] = useState<boolean>(false);

    // 에러 메시지
    const [error, setError] = useState<string>("");

    // 페이지 이동
    const navigate = useNavigate();

    /**
     * 로그인 폼 제출
     */
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");
        setLoading(true);

        try {
            console.log("🔐 로그인 시도:", { username, password });
            
            // 백엔드 로그인 API 호출
            const response = await loginAPI({ username, password });
            
            console.log("✅ 로그인 성공:", response);
            
            // 토큰을 localStorage에 저장
            saveToken(response);
            
            console.log("💾 토큰 저장 완료");
            
            // 로그인 성공 → 메인 페이지로 이동
            navigate("/main");
        } catch (err: any) {
            // 에러 메시지 표시
            console.error("❌ 로그인 에러 상세:", err);
            console.error("Response 상태:", err.response?.status);
            console.error("Response 데이터:", err.response?.data);
            console.error("에러 메시지:", err.message);
            
            const errorMessage = err.response?.data?.message || err.message || "로그인에 실패했습니다.";
            setError(errorMessage);
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            <Header />

            <div className="main">
                <FloatingSidebar />

                {/* Hero 영역 */}
                <section className="hero">
                    <div className="hero-inner">

                        <div className="hero-grid">

                            {/* 좌측: 타이틀 영역 */}
                            <div className="hero-text">
                                <h1 className="hero-title">로그인</h1>
                                <p className="hero-desc">
                                    BizTrip 서비스를 이용하려면 로그인하세요.
                                </p>
                            </div>

                            {/* 우측: 로그인 폼 */}
                            <div className="hero-form">
                                <form className="login-form" onSubmit={handleSubmit}>

                                    {/* 에러 메시지 표시 */}
                                    {error && (
                                        <div className="form-error" style={{ 
                                            color: 'red', 
                                            marginBottom: '1rem',
                                            padding: '0.5rem',
                                            backgroundColor: '#ffe6e6',
                                            borderRadius: '4px'
                                        }}>
                                            {error}
                                        </div>
                                    )}

                                    <div className="form-group">
                                        <label>사용자명</label>
                                        <input
                                            type="text"
                                            placeholder="사용자명을 입력하세요 (예: admin)"
                                            value={username}
                                            onChange={(e) => setUsername(e.target.value)}
                                            required
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label>비밀번호</label>
                                        <input
                                            type="password"
                                            placeholder="비밀번호를 입력하세요 (예: admin123)"
                                            value={password}
                                            onChange={(e) => setPassword(e.target.value)}
                                            required
                                        />
                                    </div>

                                    <button 
                                        className="btn-primary" 
                                        type="submit"
                                        disabled={loading}
                                    >
                                        {loading ? "로그인 중..." : "로그인"}
                                    </button>

                                </form>
                            </div>

                        </div>

                    </div>
                </section>

            </div>

            <Footer />
        </>
    );
};

export default LoginPage;
