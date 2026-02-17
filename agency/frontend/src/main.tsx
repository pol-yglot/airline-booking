import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

console.log('--- CURRENT ENV VARIABLES ---');
console.log('VITE_API_BASE_URL      :', import.meta.env.VITE_API_BASE_URL);
console.log('VITE_API_PROXY_TARGET  :', import.meta.env.VITE_API_PROXY_TARGET);
console.log('MODE (NODE_ENV)        :', import.meta.env.MODE);
console.log('PROD?', import.meta.env.PROD);
console.log('-----------------------------');

ReactDOM.createRoot(document.getElementById('root')!).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>
);
