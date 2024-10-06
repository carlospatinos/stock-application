import React, { useState } from 'react';
import { Stock } from './Stock';
import StockCard from './StockCard';
import StockForm from './StockForm';
import CardGroup from 'react-bootstrap/CardGroup';
import Container from 'react-bootstrap/Container';


interface StockListProps {
    stocks: Stock[];
    onBuy: (stock: Stock) => void
}

function StockList({ stocks, onBuy }: StockListProps) {
    const [stockBeingSold, setStockBeingSold] = useState({});
    const handleBuy = (stockToSell: Stock) => {
        setStockBeingSold(stockToSell);
    }
    const handleCancel = () => {
        setStockBeingSold({});
    }
    const items = stocks.map(stock => (
        <div key={stock.id} className="cols-sm">
            {stock === stockBeingSold ? (
                <StockForm onCancel={handleCancel} onBuy={onBuy} stock={stock} />
            ) : (
                <StockCard stock={stock} onBuy={handleBuy} />
            )}
        </div>
    ));
    return (<Container>
        <CardGroup>{items}
        </CardGroup>
    </Container>);
}

export default StockList;