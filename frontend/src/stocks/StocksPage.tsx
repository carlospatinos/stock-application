import React from "react";
import { MOCK_STOCK } from './MockStocks';
import StockList from './StockList';

function StockPage() {
    return (<>
        <h1>Stocks</h1>
        <StockList stocks={MOCK_STOCK} />
    </>);
}

export default StockPage;