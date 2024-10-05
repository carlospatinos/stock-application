import React from "react";
import { MOCK_STOCK } from './MockStocks';

function StockPage() {
    return (<>
        <h1>Stocks</h1>
        <pre>{JSON.stringify(MOCK_STOCK, null, ' ')}</pre>
    </>);
}

export default StockPage;