import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const TripApplicationPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">출장 신청</h1>
                        <p className="hero-desc">
                            새로운 출장을 신청하고 관리하세요.
                        </p>
                        <div className="hero-actions">
                            <button className="btn-primary">신청하기</button>
                        </div>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default TripApplicationPage;