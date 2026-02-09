import api from './axios';

export interface CustomerData {
    id?: number;
    companyName: string;
    businessNumber: string;
    ceoName: string;
    ceoEmail: string;
    businessType: string;
    businessArea: string;
    phone: string;
    address: string;
    detailAddress?: string;
    logoUrl?: string;
    profileImageUrl?: string;
}

/**
 * 현재 로그인 사용자 정보 조회
 */
export const getCurrentUser = () => {
    return api.get('/auth/me');
};

/**
 * 고객사 상세 조회
 */
export const getCustomerById = (customerId: number) => {
    return api.get(`/customers/${customerId}`);
};

/**
 * 고객사 정보 수정
 */
export const updateCustomer = (customerId: number, customerData: Partial<CustomerData>) => {
    return api.put(`/customers/${customerId}`, customerData);
};

/**
 * 고객사 삭제 (회원 탈퇴)
 */
export const deleteCustomer = (customerId: number) => {
    return api.delete(`/customers/${customerId}`);
};

/**
 * 프로필 이미지 업로드
 */
export const uploadProfileImage = (customerId: number, formData: FormData) => {
    return api.post(`/customers/${customerId}/upload-image`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
};
