import React from 'react';
import { Stock } from './Stocks';
import PropTypes from 'prop-types';

function StockList({ stocks }) {
    console.log(stocks);
    return (
        <ul className="row">
            {stocks.map((stock) => (
                <li key={stock.name}>{stock.name}</li>
            ))}
        </ul>
    );
}
StockList.propTypes = {
    stocks: PropTypes.arrayOf(PropTypes.instanceOf(Stock)).isRequired
}
export default StockList;