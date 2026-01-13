import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const CompanyIntroductionPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">회사소개</h1>
                        <p className="hero-desc">
                            저희 회사에 대해 소개합니다.
                        </p>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default CompanyIntroductionPage;