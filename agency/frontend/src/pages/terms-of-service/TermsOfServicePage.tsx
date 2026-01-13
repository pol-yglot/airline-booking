import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const TermsOfServicePage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">이용약관</h1>
                        <p className="hero-desc">
                            서비스 이용약관을 확인하세요.
                        </p>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default TermsOfServicePage;