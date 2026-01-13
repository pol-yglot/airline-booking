import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const AIJourneyPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">AI 고객 여정</h1>
                        <p className="hero-desc">
                            AI를 활용하여 최적의 고객 여정을 설계하세요.
                        </p>
                        <div className="hero-actions">
                            <button className="btn-primary">시작하기</button>
                        </div>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default AIJourneyPage;