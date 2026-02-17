import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig(({ mode }) => {
    // mode: local | dev | prod
    const env = loadEnv(mode, process.cwd());
    const isProd = mode === 'prod';

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
                    },
                },
            },
    };
});
