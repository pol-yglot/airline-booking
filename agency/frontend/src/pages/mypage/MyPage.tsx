import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const MyPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">마이 페이지</h1>
                        <p className="hero-desc">
                            내 정보 조회
                        </p>
                    </div>
                </section>
            </div>
            <Footer />
        </>
    );
};

export default MyPage;