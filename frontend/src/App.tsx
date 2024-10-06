import React, { useEffect, useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';


import { ToastContainer } from "react-toastify";

import "react-toastify/dist/ReactToastify.css";
import StockPage from './stocks/StocksPage';
import UserLogin from './user/UserLogin';

function App() {

  const [activeUser, setActiveUser] = useState('');
  useEffect(() => {
  }, []);

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
