import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const TripManagementPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">출장 관리</h1>
                        <p className="hero-desc">
                            진행 중인 출장을 효율적으로 관리하세요.
                        </p>
                        <div className="hero-actions">
                            <button className="btn-primary">관리하기</button>
                        </div>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default TripManagementPage;