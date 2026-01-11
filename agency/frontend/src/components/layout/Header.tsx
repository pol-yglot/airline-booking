const Header = () => {
    return (
        <header id="header">
            <div className="header-inner">
                {/* Logo */}
                <h1 className="logo">
                    <a href="/main">BizTrip</a>
                </h1>

                {/* Navigation */}
                <nav className="gnb">
                    <ul>
                        <li><a href="#">AI 고객여정</a></li>
                        <li><a href="#">출장 신청</a></li>
                        <li><a href="#">출장 관리</a></li>
                        <li><a href="#">정산 · 리포트</a></li>
                        <li><a href="#">고객센터</a></li>
                    </ul>
                </nav>

                {/* Right Menu */}
                <div className="header-util">
                    <a href="#" className="login">로그인</a>
                    <a href="#" className="signup">도입 문의</a>
                </div>
            </div>
        </header>
    );
};

export default Header;
