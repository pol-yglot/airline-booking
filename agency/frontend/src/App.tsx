import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainPage from "./pages/main/MainPage.tsx";
import AIJourneyPage from "./pages/ai-journey/AIJourneyPage.tsx";
import TripApplicationPage from "./pages/trip-application/TripApplicationPage.tsx";
import TripManagementPage from "./pages/trip-management/TripManagementPage.tsx";
import SettlementReportPage from "./pages/settlement-report/SettlementReportPage.tsx";
import CustomerCenterPage from "./pages/customer-center/CustomerCenterPage.tsx";
import InquiryPage from "./pages/inquiry/InquiryPage.tsx";
import LoginPage from "./pages/login/LoginPage.tsx";
import CompanyIntroductionPage from "./pages/company-introduction/CompanyIntroductionPage.tsx";
import PrivacyPolicyPage from "./pages/privacy-policy/PrivacyPolicyPage.tsx";
import TermsOfServicePage from "./pages/terms-of-service/TermsOfServicePage.tsx";
import TravelTermsPage from "./pages/travel-terms/TravelTermsPage.tsx";

// 여기에서 api와 화면을 매핑한다.
function App() {
    return (
        <BrowserRouter>
            <Routes>

                {/* 로그인 */}
                <Route path="/login" element={<LoginPage />} />

                {/* 메인 대시보드 */}
                <Route path="/main" element={<MainPage />} />

                {/* AI 고객여정 */}
                <Route path="/ai-journey" element={<AIJourneyPage />} />

                {/* 출장 신청 */}
                <Route path="/trip-application" element={<TripApplicationPage />} />

                {/* 출장 관리 */}
                <Route path="/trip-management" element={<TripManagementPage />} />

                {/* 정산 · 리포트 */}
                <Route path="/settlement-report" element={<SettlementReportPage />} />

                {/* 고객센터 */}
                <Route path="/customer-center" element={<CustomerCenterPage />} />

                {/* 도입 문의 */}
                <Route path="/inquiry" element={<InquiryPage />} />

                {/* 회사 소개 */}
                <Route path="/company-introduction" element={<CompanyIntroductionPage />} />

                {/* 개인정보 처리방침 */}
                <Route path="/privacy-policy" element={<PrivacyPolicyPage />} />

                {/* 서비스 이용약관 */}
                <Route path="/terms-of-service" element={<TermsOfServicePage />} />

                {/* 출장 서비스 약관 */}
                <Route path="/travel-terms" element={<TravelTermsPage />} />

            </Routes>
        </BrowserRouter>
    );
}

export default App;
