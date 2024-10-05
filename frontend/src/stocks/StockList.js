import React from 'react';
import { Stock } from './Stocks';

function StockList({ stocks }) {
    return (<pre>{JSON.stringify(stocks, null, ' ')}</pre>);
}

export default StockList;