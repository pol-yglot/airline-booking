import axios from 'axios';

const api = axios.create({
    //baseURL: 'http://localhost:8082/api',
    baseURL: import.meta.env.VITE_API_BASE_URL,
    timeout: 5000,
    withCredentials: true,
});

/**
 * 요청 인터셉터: Authorization 헤더에 JWT 토큰 추가
 */
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('authToken');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

/**
 * 응답 인터셉터: 401 응답 처리
 */
api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            // 토큰 만료 또는 인증 실패
            localStorage.removeItem('authToken');
            localStorage.removeItem('tokenType');
            localStorage.removeItem('username');
            localStorage.removeItem('expiresIn');
            window.location.href = '/login';
        }
        return Promise.reject(error);
    }
);

export default api;
