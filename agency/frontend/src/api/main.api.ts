import api from './axios';

export const getMain = () => {
    return api.get('/main');
};
