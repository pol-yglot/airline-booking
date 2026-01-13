import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const TravelTermsPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">여행약관</h1>
                        <p className="hero-desc">
                            여행약관을 확인하세요.
                        </p>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default TravelTermsPage;