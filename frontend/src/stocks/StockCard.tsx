import React from 'react';
import { Stock } from './Stock';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Figure from 'react-bootstrap/Figure';


interface StockCardProps {
    stock: Stock;
    onBuy: (stock: Stock) => void
}

function StockCard(props: StockCardProps) {
    const { stock, onBuy } = props;
    const handleSellClick = (stockToSell: Stock) => {
        onBuy(stockToSell);
    }
    return (
        <Card style={{
            width: '18rem'
        }} bg={'secondary'} border="primary" >
            <Card.Header>{stock.name}</Card.Header>
            {/* <CardImg></CardImg> */}
            <Card.Body>
                <Figure>
                    <Figure.Image
                        width={80}
                        height={80}
                        alt="Icon"
                        src={stock.imageUrl}
                    />
                    <Figure.Caption>
                        {stock.description}
                    </Figure.Caption>
                </Figure>
                <Card.Title>Price: {stock.price.toLocaleString()}
                </Card.Title>
                {/* <Card.Text style={{ fontSize: '18px' }}>
                    {stock.description}.
                </Card.Text> */}
                <Card.Footer className="text-muted"><Button variant="primary" onClick={() => {
                    handleSellClick(stock);
                }}>Buy</Button></Card.Footer>
            </Card.Body>
        </Card >
    );
}

export default StockCard;