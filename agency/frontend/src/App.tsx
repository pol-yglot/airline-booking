import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
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
import ProtectedRoute from "./routes/ProtectedRoute.tsx";
import MyPage from "./pages/mypage/MyPage.tsx";

// 여기에서 api와 화면을 매핑한다.
function App() {
    return (
        <BrowserRouter>
            <Routes>

                {/* 로그인 - 공개 라우트 */}
                <Route path="/login" element={<LoginPage />} />

                {/* 메인 대시보드 - 보호된 라우트 */}
                <Route path="/main" element={
                    <ProtectedRoute>
                        <MainPage />
                    </ProtectedRoute>
                } />

                {/* 마이페이지 - 보호된 라우트 */}
                <Route path="/mypage" element={
                    <ProtectedRoute>
                        <MyPage />
                    </ProtectedRoute>
                } />

                {/* AI 고객여정 - 보호된 라우트 */}
                <Route path="/ai-journey" element={
                    <ProtectedRoute>
                        <AIJourneyPage />
                    </ProtectedRoute>
                } />

                {/* 출장 신청 - 보호된 라우트 */}
                <Route path="/trip-application" element={
                    <ProtectedRoute>
                        <TripApplicationPage />
                    </ProtectedRoute>
                } />

                {/* 출장 관리 - 보호된 라우트 */}
                <Route path="/trip-management" element={
                    <ProtectedRoute>
                        <TripManagementPage />
                    </ProtectedRoute>
                } />

                {/* 정산 · 리포트 - 보호된 라우트 */}
                <Route path="/settlement-report" element={
                    <ProtectedRoute>
                        <SettlementReportPage />
                    </ProtectedRoute>
                } />

                {/* 고객센터 - 보호된 라우트 */}
                <Route path="/customer-center" element={
                    <ProtectedRoute>
                        <CustomerCenterPage />
                    </ProtectedRoute>
                } />

                {/* 도입 문의 - 공개 라우트 */}
                <Route path="/inquiry" element={<InquiryPage />} />

                {/* 회사 소개 - 공개 라우트 */}
                <Route path="/company-introduction" element={<CompanyIntroductionPage />} />

                {/* 개인정보 처리방침 - 공개 라우트 */}
                <Route path="/privacy-policy" element={<PrivacyPolicyPage />} />

                {/* 서비스 이용약관 - 공개 라우트 */}
                <Route path="/terms-of-service" element={<TermsOfServicePage />} />

                {/* 출장 서비스 약관 - 공개 라우트 */}
                <Route path="/travel-terms" element={<TravelTermsPage />} />

                {/* 기본 리디렉트 */}
                <Route path="/" element={<Navigate to="/main" replace />} />

            </Routes>
        </BrowserRouter>
    );
}

export default App;
