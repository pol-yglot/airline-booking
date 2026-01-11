const FloatingSidebar = () => {
    const handleScrollTop = () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth',
        });
    };

    const handleChatbotClick = () => {
        // MVP 단계에서는 임시 처리
        alert('AI 출장 도우미 연결 예정입니다.');
    };

    return (
        <div className="floating-sidebar">
            <button
                className="floating-btn top-btn"
                onClick={handleScrollTop}
                aria-label="맨 위로 이동"
            >
                ↑
            </button>

            <button
                className="floating-btn chatbot-btn"
                onClick={handleChatbotClick}
                aria-label="AI 출장 도우미"
            >
                💬
            </button>
        </div>
    );
};

export default FloatingSidebar;
