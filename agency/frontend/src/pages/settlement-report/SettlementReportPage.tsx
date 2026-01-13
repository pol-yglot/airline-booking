import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const SettlementReportPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">정산 · 리포트</h1>
                        <p className="hero-desc">
                            출장 비용을 정산하고 리포트를 확인하세요.
                        </p>
                        <div className="hero-actions">
                            <button className="btn-primary">정산하기</button>
                        </div>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default SettlementReportPage;