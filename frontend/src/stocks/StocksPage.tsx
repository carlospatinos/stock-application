import React, { useState, useEffect } from 'react';
import { MOCK_STOCK } from './MockStocks';
import StockList from './StockList';
import { Stock } from './Stock';
import { stockAPI } from './stockAPI';

function StockPage() {
    const [stocks, setStocks] = useState<Stock[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | undefined>(undefined);

    const saveProject = (stock: Stock) => {
        console.log('Buying stock: ', stock);
    }

    useEffect(() => {
        async function loadProjects() {
            setLoading(true);
            try {
                const data = await stockAPI.get(1);
                console.log(data);
                setError('');
                setStocks(data);
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
    }, []);

    return (<>
        <div className="center-page">
            <h1><p className="text-center">Stocks</p></h1>
            {loading ? (
                <div>
                    <div className="spinner-border" role="status" />
                    <span className="sr-only"> Loading...</span>
                </div>
            ) :
                <StockList stocks={MOCK_STOCK} onSave={saveProject} />
            }
        </div>
    </>);
}

export default StockPage;