import React, { SyntheticEvent, useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

import { Stock } from './Stock';

interface StockFormProps {
    stock: Stock;
    onCancel: () => void;
    onBuy: (stockToSell: Stock) => void;
}

function StockForm({ stock: initialStock, onBuy, onCancel }: StockFormProps) {
    const [stock, setStock] = useState(initialStock);
    const handleSubmit = (event: SyntheticEvent) => {
        event.preventDefault();
        onBuy(stock);
    }

    const handleChange = (event: any) => {
        const { type, name, value } = event.target;
        // if input type is checkbox use checked
        // otherwise it's type is text, number etc. so use value
        let updatedValue = value;

        //if input type is number convert the updatedValue string to a +number
        if (type === 'number') {
            updatedValue = Number(updatedValue);
        }
        const change = {
            [name]: updatedValue,
        };


        let updatedStock: Stock;
        // need to do functional update b/c
        // the new stock state is based on the previous stock state
        // so we can keep the stock properties that aren't being edited +like stock.id
        // the spread operator (...) is used to
        // spread the previous stock properties and the new change
        setStock((s) => {
            updatedStock = new Stock({ ...s, ...change });
            console.log(change);
            console.log(updatedStock.amount);
            return updatedStock;
        });
    };
    return (
        <Form onSubmit={handleSubmit}>
            <Card style={{ width: '18rem' }} border="secondary" >
                <Card.Header>{stock.name}</Card.Header>
                <Card.Body>
                    <Form.Text>Great opportunity</Form.Text> <br />
                    <Form.Text>Price: {stock.price}</Form.Text><br />
                    <Form.Text>Available: {stock.availableUnits}</Form.Text><br /><br />
                    <Form.Group className="mb-3" controlId="formAmount">
                        <Form.Label>Amount</Form.Label>
                        <Form.Control type="number" name="amount" placeholder="Enter amount" value={stock.amount} onChange={handleChange} />
                    </Form.Group>
                    <Form.Group className="mb-3" >
                        <Button variant="primary" type="submit">
                            Buy
                        </Button>
                        <span />
                        <Button variant="secondary" onClick={onCancel}>
                            Cancel
                        </Button>
                    </Form.Group>

                </Card.Body>
            </Card >
        </Form>
    );
}

export default StockForm;