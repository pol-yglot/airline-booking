import Header from "../../components/layout/Header";
import Footer from "../../components/layout/Footer";
import FloatingSidebar from "../../components/layout/FloatingSidebar";

const CustomerCenterPage = () => {
    return (
        <>
            <Header />
            <div className="main">
                <FloatingSidebar />
                <section className="hero">
                    <div className="hero-inner">
                        <h1 className="hero-title">고객센터</h1>
                        <p className="hero-desc">
                            궁금한 점이 있으시면 언제든지 문의해주세요.
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

export default CustomerCenterPage;