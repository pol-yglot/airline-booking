import { useState } from "react";
import "../../styles/main.css";

interface RoomSelectorProps {
    onConfirm?: (rooms: number, adults: number, children: number) => void;
}

const RoomSelector = ({ onConfirm }: RoomSelectorProps) => {
    const [rooms, setRooms] = useState(1);
    const [adults, setAdults] = useState(2);
    const [children, setChildren] = useState(0);
    const [open, setOpen] = useState(false);

    const toggleDropdown = () => setOpen(!open);

    return (
        <div className="room-selector">
            <div className="summary" onClick={toggleDropdown}>
                객실 {rooms}개, 성인 {adults}명, 어린이 {children}명
                <span className="arrow">{open ? "▲" : "▼"}</span>
            </div>

            {open && (
                <div className="dropdown">
                    <div className="item">
                        <span>객실</span>
                        <div className="controls">
                            <button onClick={() => setRooms(Math.max(1, rooms - 1))}>-</button>
                            <span>{rooms}</span>
                            <button onClick={() => setRooms(rooms + 1)}>+</button>
                        </div>
                    </div>

                    <div className="item">
                        <span>성인 <small>만 18세 이상</small></span>
                        <div className="controls">
                            <button onClick={() => setAdults(Math.max(1, adults - 1))}>-</button>
                            <span>{adults}</span>
                            <button onClick={() => setAdults(adults + 1)}>+</button>
                        </div>
                    </div>

                    <div className="item">
                        <span>어린이 <small>만 17세 이하</small></span>
                        <div className="controls">
                            <button onClick={() => setChildren(Math.max(0, children - 1))}>-</button>
                            <span>{children}</span>
                            <button onClick={() => setChildren(children + 1)}>+</button>
                        </div>
                    </div>

                    <button
                        className="confirm-btn"
                        onClick={() => {
                            onConfirm?.(rooms, adults, children);
                            setOpen(false);
                        }}
                    >
                        확인
                    </button>
                </div>
            )}
        </div>
    );
};

export default RoomSelector;
