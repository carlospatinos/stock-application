import React, { SyntheticEvent } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

import { Stock } from './Stock';

interface StockFormProps {
    onCancel: () => void
    onSave: (stockToSell: Stock) => void
}

function StockForm({ onSave, onCancel }: StockFormProps) {
    const handleSubmit = (event: SyntheticEvent) => {
        event.preventDefault();
        onSave(new Stock({ name: 'Updated Stock' }));
    }
    return (
        <Form onSubmit={handleSubmit}>
            <Card style={{ width: '18rem' }} border="secondary" >
                <Card.Header>Great opportunity</Card.Header>
                <Card.Body>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Amount</Form.Label>
                        <Form.Control type="number" placeholder="Enter amount" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Button variant="primary" type="submit">
                            Save
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