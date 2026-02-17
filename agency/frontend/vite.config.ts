import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig(({ mode }) => {
    // mode: local | development | production
    const env = loadEnv(mode, process.cwd(), 'VITE_'); // ✅ prefix 적용
    const isProd = mode === 'production'; // ✅ prod 모드 체크

    console.log('===============================');
    console.log('MODE        :', mode);
    console.log('API_BASE    :', env.VITE_API_BASE_URL);
    console.log('PROXY_TARGET:', env.VITE_API_PROXY_TARGET);
    console.log('===============================');

    return {
        plugins: [react()],
        resolve: {
            alias: {
                '@': path.resolve(__dirname, 'src'),
            },
        },
        server: isProd
            ? undefined
            : {
                proxy: {
                    [env.VITE_API_BASE_URL]: {
                        target: env.VITE_API_PROXY_TARGET,
                        secure: false,
                        changeOrigin: true,
                    }
                },
            },
        define: {
            'process.env': env,
        },
    };
});
