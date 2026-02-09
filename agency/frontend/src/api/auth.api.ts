import api from './axios';

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  type: string;
  username: string;
  customerId: number;
  expiresIn: number;
}

/**
 * 로그인 API
 */
export const loginAPI = async (credentials: LoginRequest): Promise<LoginResponse> => {
  const response = await api.post('/auth/login', credentials);
  return response.data;
};

/**
 * 로그아웃 API
 */
export const logoutAPI = async (): Promise<void> => {
  await api.post('/auth/logout');
};

/**
 * 토큰 검증 API
 */
export const verifyTokenAPI = async (): Promise<boolean> => {
  try {
    const response = await api.get('/auth/verify');
    return response.status === 200;
  } catch {
    return false;
  }
};

/**
 * localStorage에 토큰 저장
 */
export const saveToken = (response: LoginResponse): void => {
  localStorage.setItem('authToken', response.token);
  localStorage.setItem('tokenType', response.type);
  localStorage.setItem('username', response.username);
  localStorage.setItem('customerId', String(response.customerId));
  localStorage.setItem('expiresIn', String(response.expiresIn));
};

/**
 * localStorage에서 토큰 조회
 */
export const getToken = (): string | null => {
  return localStorage.getItem('authToken');
};

/**
 * localStorage에서 토큰 제거
 */
export const removeToken = (): void => {
  localStorage.removeItem('authToken');
  localStorage.removeItem('tokenType');
  localStorage.removeItem('username');
  localStorage.removeItem('expiresIn');
};

/**
 * 로그인 여부 확인
 */
export const isAuthenticated = (): boolean => {
  return getToken() !== null;
};
