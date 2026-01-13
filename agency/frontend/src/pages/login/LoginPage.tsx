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

const LoginPage = () => {

    // 이메일 상태
    const [email, setEmail] = useState<string>("");

    // 비밀번호 상태
    const [password, setPassword] = useState<string>("");

    // 페이지 이동
    const navigate = useNavigate();

    /**
     * 로그인 폼 제출
     */
    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        // TODO: Spring Boot 로그인 API 연동 예정
        console.log("Login attempt:", { email, password });

        // 임시 성공 처리 → 메인 이동
        navigate("/main");
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

                                    <div className="form-group">
                                        <label>이메일</label>
                                        <input
                                            type="email"
                                            placeholder="이메일을 입력하세요"
                                            value={email}
                                            onChange={(e) => setEmail(e.target.value)}
                                        />
                                    </div>

                                    <div className="form-group">
                                        <label>비밀번호</label>
                                        <input
                                            type="password"
                                            placeholder="비밀번호를 입력하세요"
                                            value={password}
                                            onChange={(e) => setPassword(e.target.value)}
                                        />
                                    </div>

                                    <button className="btn-primary" type="submit">
                                        로그인
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
