const Footer = () => {
    return (
        <footer id="footer">
            <div className="inr">
                {/* SNS */}
                <div className="service_info_wrap">
                    <div className="fr">
                        <ul className="sns_list">
                            <li><a href="https://www.facebook.com/HanaTour.fb" target="_blank">facebook</a></li>
                            <li><a href="https://www.instagram.com/hanatour_official" target="_blank">instagram</a></li>
                            <li><a href="https://blog.naver.com/hanatourkr" target="_blank">blog</a></li>
                            <li><a href="https://pf.kakao.com/_ftrPI" target="_blank">kakaoplus</a></li>
                            <li><a href="https://www.youtube.com/user/HanaTour" target="_blank">youtube</a></li>
                        </ul>
                    </div>
                </div>

                {/* 회사 정보 */}
                <div className="company_info_wrap">
                    <ul className="company_provision">
                        <li><a href="/company-introduction">회사소개</a></li>
                        <li><a href="/privacy-policy">이용약관</a></li>
                        <li><a href="/terms-of-service"><strong>개인정보처리방침</strong></a></li>
                        <li><a href="/travel-terms">여행약관</a></li>
                    </ul>

                    <address className="footer_licensee">
                        <strong>(주)차미투어</strong> 대표 : 홍길동 <br />
                        주소 : (03161) 서울특별시 종로구 인사동 5길 41
                    </address>

                    <p className="footer_licensee">
                        사업자등록번호 : 101-81-48344
                    </p>

                    <p className="customer_center">
                        고객센터 1577-1233 / 해외항공권문의 1899-1833
                    </p>

                    <p className="copy">
                        COPYRIGHTⓒ CHAMITOUR SERVICE INC. ALL RIGHTS RESERVED
                    </p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
