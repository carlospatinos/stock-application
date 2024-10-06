import React, { useEffect, useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { getToken, onMessage } from "firebase/messaging";
import { messaging } from "./firebase/firebaseConfig";
import { toast, ToastContainer } from "react-toastify";
import NotificationMessage from "./notifications/NotificationMessage";
import "react-toastify/dist/ReactToastify.css";
import StockPage from './stocks/StocksPage';
import UserLogin from './user/UserLogin';

function App() {
  const { REACT_APP_VAPID_KEY } = process.env;
  const [activeUser, setActiveUser] = useState('');
  async function requestPermission() {
    //requesting permission using Notification API
    const permission = await Notification.requestPermission();

    if (permission === "granted") {
      const token = await getToken(messaging, {
        vapidKey: REACT_APP_VAPID_KEY,
      });

      //We can send token to server 
      console.log("Token generated : ", token);
    } else if (permission === "denied") {
      //notifications are blocked
      alert("You denied for the notification");
    }
  }

  useEffect(() => {
    requestPermission();
  }, []);

  onMessage(messaging, payload => {
    console.log("incoming msg");
    console.log(payload);
    toast(<NotificationMessage notification={payload.notification} />);
  });

  const handleLogin = (username: string) => {
    setActiveUser(username);
    console.log(username);
  }
  return (
    <div className="App">
      {activeUser ? <StockPage /> : <UserLogin onUserLogin={handleLogin} />}
      <ToastContainer />
    </div>
  );
}

export default App;
