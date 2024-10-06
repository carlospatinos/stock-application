import React, { useState, useEffect } from 'react';
// import { MOCK_STOCK } from './MockStocks';
import StockList from './StockList';
import { Stock } from './Stock';
import { Transaction } from '../transactions/Transaction';
import { stockAPI } from './stockAPI';
import { transactionAPI } from '../transactions/transactionAPI';
import { subscriptionAPI } from '../notifications/notificationsAPI';
import NotificationMessage from "../notifications/NotificationMessage";
import { NotificationSubscription } from "../notifications/NotificationSubscription";

import Alert from 'react-bootstrap/Alert';
import { getToken, onMessage } from "firebase/messaging";
import { messaging } from "../firebase/firebaseConfig";
import { toast } from "react-toastify";

function StockPage() {
    const { REACT_APP_VAPID_KEY } = process.env;

    const [stocks, setStocks] = useState<Stock[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | undefined>(undefined);
    const [currentPage, setCurrentPage] = useState(1);

    const handleBuyStock = async (stock: Stock) => {
        console.log('Buying stock: ', stock);
        const transaction = new Transaction({ userName: 'carlos', stockName: stock.name, price: stock.price, amount: stock.amount });
        // const data = await stockAPI.post(stock).catch((e) => {
        //     if (e instanceof Error) {
        //         setError(e.message);
        //     }
        // });
        const data = await transactionAPI.post(transaction).catch((e) => {
            if (e instanceof Error) {
                setError(e.message);
            }
        });
        console.log(data);
    }

    const handleMoreClick = () => {
        setCurrentPage((currentPage) => currentPage + 1);
    };


    onMessage(messaging, payload => {
        console.log("incoming msg");
        console.log(payload);
        toast(<NotificationMessage notification={payload.notification} />);
    });

    useEffect(() => {
        async function requestPermission() {
            //requesting permission using Notification API
            const permission = await Notification.requestPermission();

            if (permission === "granted") {
                const token = await getToken(messaging, {
                    vapidKey: REACT_APP_VAPID_KEY,
                });

                //We can send token to server 
                console.log("Token generated : ", token);

                try {
                    const notificationSubscription = new NotificationSubscription({ user: 'carlos', token: token });
                    console.log(notificationSubscription);
                    const tokenCreation = await subscriptionAPI.post(notificationSubscription);
                    console.log(tokenCreation);
                } catch (e) {
                    if (e instanceof Error) {
                        console.log(e);
                    }
                }

            } else if (permission === "denied") {
                //notifications are blocked
                alert("You denied for the notification");
            }
        };
        async function loadProjects() {
            setLoading(true);
            try {
                const data = await stockAPI.get(currentPage);
                console.log(data);
                setError('');
                // setStocks(data);
                if (currentPage === 1) {
                    setStocks(data);
                } else {
                    setStocks((stocks) => [...stocks, ...data]);
                }
            }
            catch (e) {
                if (e instanceof Error) {
                    setError(e.message);
                }
            } finally {
                setLoading(false);
            }
        }
        loadProjects();
        requestPermission();
    }, [currentPage, REACT_APP_VAPID_KEY]);

    return (<>
        <div className="center-page">
            <h1><p className="text-center">Stocks</p></h1>

            {error && (
                <div className="row">
                    <Alert key="danger" variant="danger">
                        {error}
                    </Alert>
                </div>
            )}
            < StockList stocks={stocks} onBuy={handleBuyStock} />

            {!loading && !error && (
                <div className="row">
                    <div className="col-sm-12">
                        <div className="button-group fluid">
                            <button className="button default" onClick={handleMoreClick}>
                                More...
                            </button>
                        </div>
                    </div>
                </div>
            )}
            {loading && (
                <div>
                    <div className="spinner-border" role="status" />
                    <span className="sr-only"> Loading...</span>
                </div>
            )}
        </div>
    </>);
}

export default StockPage;