import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const InquiryPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">도입 문의</h1>
                        <p className="hero-desc">
                            서비스 도입에 대해 궁금한 점이 있으시면 문의해주세요.
                        </p>
                        <div className="hero-actions">
                            <button className="btn-primary">문의하기</button>
                        </div>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default InquiryPage;