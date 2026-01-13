import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const PrivacyPolicyPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">개인정보처리방침</h1>
                        <p className="hero-desc">
                            개인정보 처리방침을 확인하세요.
                        </p>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default PrivacyPolicyPage;