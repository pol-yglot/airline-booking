import { useEffect, useState } from 'react';
import type {AxiosResponse} from "axios";
import {getMain} from "../../api/main.api.ts";

const MainPage = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        getMain()
            .then((res: AxiosResponse) => { // dto가 없는 경우 AxiosResponse 타입 사용
                setMessage(res.data.message);
            })
            .catch((err: AxiosResponse) => {
                console.error(err);
            });
    }, []);

    return (
        <div>
            <h1>Main Page</h1>
            <p>{message}</p>
        </div>
    );
};

export default MainPage;
