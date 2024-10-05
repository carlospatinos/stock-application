import React from 'react';
import PropTypes from 'prop-types';
import { Stock } from './Stock';
import StockCard from './StockCard';

function StockList({ stocks }) {
    return (
        <div className="row">
            {
                stocks.map((stock) => (
                    <StockCard stock={stock}></StockCard>
                ))
            }
        </div>
    );
}
StockList.propTypes = {
    stocks: PropTypes.arrayOf(PropTypes.instanceOf(Stock)).isRequired
}
export default StockList;