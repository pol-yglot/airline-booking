import { BrowserRouter, Routes, Route } from 'react-router-dom';
import MainPage from "./pages/main/MainPage.tsx";

// 여기에서 api와 화면을 매핑한다.
function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/main" element={<MainPage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
