import { useNavigate } from 'react-router-dom';
import { isAuthenticated, removeToken } from '../../api/auth.api';

const Header = () => {
    const navigate = useNavigate();
    const isLoggedIn = isAuthenticated();
    const username = localStorage.getItem('username');

    /**
     * 로그아웃 처리
     */
    const handleLogout = () => {
        removeToken();
        navigate('/login');
    };

    return (
        <header id="header">
            <div className="header-inner">

                {/* Logo */}
                <h1 className="logo">
                    {/* 메인 대시보드 이동 */}
                    <a href="/main">BizTrip</a>
                </h1>

                {/* Navigation */}
                {isLoggedIn && (
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
                )}

                {/* Right Menu */}
                <div className="header-util">
                    {isLoggedIn ? (
                        <>
                            {/* 사용자명 표시 */}
                            <span style={{ marginRight: '1rem', fontSize: '0.9rem' }}>
                                안녕하세요, <strong>{username}</strong>님
                            </span>

                            {/* 마이페이지 링크 */}
                            <a 
                                href="/mypage"
                                style={{
                                    marginRight: '0.5rem',
                                    fontSize: '0.9rem',
                                    color: '#666',
                                    textDecoration: 'underline'
                                }}
                            >
                                마이페이지
                            </a>
                            
                            {/* 로그아웃 버튼 */}
                            <button 
                                onClick={handleLogout}
                                className="logout"
                                style={{
                                    background: 'none',
                                    border: 'none',
                                    cursor: 'pointer',
                                    color: '#666',
                                    textDecoration: 'underline',
                                    fontSize: '0.9rem'
                                }}
                            >
                                로그아웃
                            </button>
                        </>
                    ) : (
                        <>
                            {/* 로그인 */}
                            <a href="/login" className="login">로그인</a>

                            {/* 도입 문의 */}
                            <a href="/inquiry" className="signup">도입 문의</a>
                        </>
                    )}
                </div>
            </div>
        </header>
    );
};

export default Header;
