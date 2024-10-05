import { Stock } from './Stock';
import PropTypes from 'prop-types';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


function StockCard(props) {
    const { stock } = props;
    return (
        <Card style={{ width: '18rem' }}>
            {/* <Card.Img variant="top" src="/logo192.png" /> */}
            <Card.Body>
                <Card.Title>{stock.name}</Card.Title>
                <Card.Text>
                    {stock.price.toLocaleString()}
                </Card.Text>
                <Button variant="primary">Sell</Button>
            </Card.Body>
        </Card>
    );
}

StockCard.propTypes = {
    project: PropTypes.instanceOf(Stock).isRequired,
};

export default StockCard;