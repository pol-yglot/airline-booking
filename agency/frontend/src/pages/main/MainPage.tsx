import { useEffect, useState } from "react";
import type { AxiosResponse } from "axios";
import { getMain } from "../../api/main.api";

import Footer from "../../components/layout/Footer";
import Header from "../../components/layout/Header";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

import "../../styles/main.css";
import "@/styles/header.css";
import "@/styles/footer.css";
import "@/styles/floatingSidebar.css";

const MainPage = () => {
    const [message, setMessage] = useState("");

    useEffect(() => {
        getMain()
            .then((res: AxiosResponse) => {
                setMessage(res.data.message);
            })
            .catch((err: AxiosResponse) => {
                console.error(err);
            });
    }, []);

    return (
        <>
            <Header />
    <div className="main">
        {/* 플로팅 사이드바 */}
        <FloatingSidebar />

        {/* Hero */}
        <section className="hero">
            <div className="hero-inner">
                <div>
                    <h1 className="hero-title">
                        기업 출장의 모든 여정,
                        <br />
                        하나의 플랫폼으로 관리하세요
                    </h1>

                    <p className="hero-desc">
                        기업 출장 항공권, 숙박 등 기업 출장에 특화된 통합 관리 서비스입니다.
                    </p>

                    <div className="hero-actions">
                        <button className="btn-primary">출장 신청</button>
                        <button className="btn-outline">출장 관리</button>
                    </div>

                    <p className="server-message">{message}</p>

                </div>

                <div className="hero-image">
                    <img
                        src="https://dummyimage.com/500x350/e5e7eb/000000&text=Business+Trip"
                        alt="출장 서비스 이미지"
                    />
                </div>
            </div>
        </section>

        {/* Features */}
        <section className="features">
            <h2 className="features-title">
                기업 출장에 최적화된 핵심 기능
            </h2>

            <div className="feature-grid">
                <div className="feature-card">
                    <h3>AI 고객 여정</h3>
                    <p>고객니즈에 맞춘 출장여정 한눈에 보기 프로세스 제공</p>
                </div>

                <div className="feature-card">
                    <h3>출장 목적 기반 추천</h3>
                    <p>출장 목적(회의, 전시회, 영업 등)에 따라 AI가 출장 목적에 맞는 이동 패턴 추천</p>
                </div>

                <div className="feature-card">
                    <h3>출장 생성 · 관리</h3>
                    <p>출장 일감 생성 및 관리 프로세스 제공</p>
                </div>

                <div className="feature-card">
                    <h3>예약 통합 관리</h3>
                    <p>항공, 숙박, 교통 예약을 한 화면에서 통합 관리</p>
                </div>

                <div className="feature-card">
                    <h3>출장 히스토리 기반 재예약</h3>
                    <p>과거 출장 이력을 기반으로 한 번의 클릭으로 빠른 재예약</p>
                </div>

                <div className="feature-card">
                    <h3>정산 · 리포트</h3>
                    <p>출장 비용 자동 정산 및 부서·프로젝트별 리포트 제공</p>
                </div>
            </div>
        </section>

        {/* CTA */}
        <section className="cta">
            <div className="cta-inner">
                <h2 className="cta-title">
                    지금 바로 기업 출장 관리를 시작하세요
                </h2>
                <p className="cta-desc">
                    복잡한 출장 관리, 이제 시스템으로 해결하세요.
                </p>
                <button className="btn-cta">도입 문의</button>
            </div>
        </section>
    </div>
            <Footer />
            </>
    );
};

export default MainPage;
