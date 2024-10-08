
import { Stock } from './Stock';
// TODO move to a config file
const baseUrl = 'http://localhost:8080/api';
// const baseUrl = 'http://localhost:4000'
const url = `${baseUrl}/stocks`;

function translateStatusToErrorMessage(status: number) {
    switch (status) {
        case 401:
            return 'Please login again.';
        case 403:
            return 'You do not have permission to view the stock(s).';
        default:
            return 'There was an error retrieving the stock(s). Please try again.';
    }
}

function checkStatus(response: any) {
    if (response.ok) {
        return response;
    } else {
        const httpErrorInfo = {
            status: response.status,
            statusText: response.statusText,
            url: response.url,
        };
        console.log(`log server http error: ${JSON.stringify(httpErrorInfo)}`);

        let errorMessage = translateStatusToErrorMessage(httpErrorInfo.status);
        throw new Error(errorMessage);
    }
}

function parseJSON(response: Response) {
    return response.json();
}

// eslint-disable-next-line
function delay(ms: number) {
    return function (x: any): Promise<any> {
        return new Promise((resolve) => setTimeout(() => resolve(x), ms));
    };
}

function convertToStockModels(data: any[]): Stock[] {
    console.log(data)
    let stocks: Stock[] = data.map(convertToStockModel);
    return stocks;
}

function convertToStockModel(item: any): Stock {
    return new Stock(item);
}

const stockAPI = {
    get(page = 1, limit = 20) {
        return fetch(`${url}?_page=${page}&_limit=${limit}&_sort=name`)
            .then(delay(100))
            .then(checkStatus)
            .then(parseJSON)
            .then(convertToStockModels)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error retrieving the stocks. Please try again.'
                );
            });
    },
    post(stock: Stock) {
        return fetch(`${url}`, {
            method: 'POST', body: JSON.stringify(stock), headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(delay(100))
            .then(checkStatus)
            .then(parseJSON)
            // .then(convertToStockModels)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error retrieving the stocks. Please try again.'
                );
            });
    },
    put(stock: Stock) {
        return fetch(`${url}/${stock.id}`, {
            method: 'PUT',
            body: JSON.stringify(stock),
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(checkStatus)
            .then(parseJSON)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error updating the project. Please try again.'
                );
            });
    },
};

export { stockAPI };