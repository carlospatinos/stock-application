import React from 'react';
import { Stock } from './Stock';
import PropTypes from 'prop-types';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import { StockCard } from './StockCard';

function StockList({ stocks }) {
    return (
        <div className="row">
            {stocks.map((stock) => (
                <Card key={stock.id} style={{ width: '18rem' }}>
                    {/* <Card.Img variant="top" src="/logo192.png" /> */}
                    <Card.Body>
                        <Card.Title>{stock.name}</Card.Title>
                        <Card.Text>
                            {stock.price.toLocaleString()}
                        </Card.Text>
                        <Button variant="primary">Sell</Button>
                    </Card.Body>
                </Card>
            ))}
            {/* {stocks.map((stock) => (
                <div key={stock.id} className="cols-sm">
                    <StockCard stock={stock}></StockCard>
                    <pre></pre>
                </div>
            ))} */}
            {/* <ul className="row">
                {stocks.map((stock) => (
                    <li key={stock.id}>{stock.name}</li>
                ))}
            </ul> */}
        </div>
    );
}
StockList.propTypes = {
    stocks: PropTypes.arrayOf(PropTypes.instanceOf(Stock)).isRequired
}
export default StockList;