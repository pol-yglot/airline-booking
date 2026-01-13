const Header = () => {
    return (
        <header id="header">
            <div className="header-inner">

                {/* Logo */}
                <h1 className="logo">
                    {/* 메인 대시보드 이동 */}
                    <a href="/main">BizTrip</a>
                </h1>

                {/* Navigation */}
                <nav className="gnb">
                    <ul>
                        {/* AI 고객여정 */}
                        <li><a href="/ai-journey">AI 고객여정</a></li>

                        {/* 출장 신청 */}
                        <li><a href="/trip-application">출장 신청</a></li>

                        {/* 출장 관리 */}
                        <li><a href="/trip-management">출장 관리</a></li>

                        {/* 정산 · 리포트 */}
                        <li><a href="/settlement-report">정산 · 리포트</a></li>

                        {/* 고객센터 */}
                        <li><a href="/customer-center">고객센터</a></li>
                    </ul>
                </nav>

                {/* Right Menu */}
                <div className="header-util">
                    {/* 로그인 (아직 미구현 상태 가정) */}
                    <a href="/login" className="login">로그인</a>

                    {/* 도입 문의 */}
                    <a href="/inquiry" className="signup">도입 문의</a>
                </div>
            </div>
        </header>
    );
};

export default Header;
