import React, { useState, useEffect } from 'react';
import { MOCK_STOCK } from './MockStocks';
import StockList from './StockList';
import { Stock } from './Stock';
import { stockAPI } from './stockAPI';
import Alert from 'react-bootstrap/Alert';


function StockPage() {
    const [stocks, setStocks] = useState<Stock[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | undefined>(undefined);
    const [currentPage, setCurrentPage] = useState(1);

    const handleBuyStock = async (stock: Stock) => {
        console.log('Buying stock: ', stock);
        const data = await stockAPI.post(stock).catch((e) => {
            if (e instanceof Error) {
                setError(e.message);
            }
        });
        console.log(data);
    }

    const handleMoreClick = () => {
        setCurrentPage((currentPage) => currentPage + 1);
    };

    useEffect(() => {
        async function loadProjects() {
            setLoading(true);
            try {
                const data = await stockAPI.get(currentPage);
                console.log(data);
                setError('');
                // setStocks(data);
                if (currentPage === 1) {
                    setStocks(data);
                } else {
                    setStocks((stocks) => [...stocks, ...data]);
                }
            }
            catch (e) {
                if (e instanceof Error) {
                    setError(e.message);
                }
            } finally {
                setLoading(false);
            }
        }
        loadProjects();
    }, [currentPage]);

    return (<>
        <div className="center-page">
            <h1><p className="text-center">Stocks</p></h1>

            {error && (
                <div className="row">
                    <Alert key="danger" variant="danger">
                        {error}
                    </Alert>
                </div>
            )}
            < StockList stocks={stocks} onBuy={handleBuyStock} />

            {!loading && !error && (
                <div className="row">
                    <div className="col-sm-12">
                        <div className="button-group fluid">
                            <button className="button default" onClick={handleMoreClick}>
                                More...
                            </button>
                        </div>
                    </div>
                </div>
            )}
            {loading && (
                <div>
                    <div className="spinner-border" role="status" />
                    <span className="sr-only"> Loading...</span>
                </div>
            )}
        </div>
    </>);
}

export default StockPage;