import { Navigate } from 'react-router-dom';
import { isAuthenticated } from '../api/auth.api';

interface ProtectedRouteProps {
  children: React.ReactNode;
}

/**
 * 보호된 라우트 컴포넌트
 * 토큰이 없으면 로그인 페이지로 리디렉트
 */
const ProtectedRoute = ({ children }: ProtectedRouteProps) => {
  if (!isAuthenticated()) {
    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
};

export default ProtectedRoute;
