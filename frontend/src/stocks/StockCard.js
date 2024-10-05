import PropTypes from 'prop-types';
import React from 'react';
import { Stock } from './Stock';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


function StockCard(prop) {
    const { stock } = prop;
    const handleSellClick = (stockName) => {
        console.log('Selling ' + stockName);
    }
    return (
        <Card style={{ width: '18rem' }}>
            <Card.Body>
                <Card.Title>{stock.name}</Card.Title>
                <Card.Text>
                    {stock.price.toLocaleString()}
                </Card.Text>
                <Button variant="primary" onClick={() => { handleSellClick(stock.name) }}>Sell</Button>
            </Card.Body>
        </Card>
    );
}
StockCard.propTypes = {
    stock: PropTypes.instanceOf(Stock).isRequired,
};
export default StockCard;