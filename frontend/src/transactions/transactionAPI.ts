
// TOO get a transactionObject
import { Transaction } from './Transaction';
// TODO move to a config file
const baseUrl = 'http://localhost:8080/api';
// const baseUrl = 'http://localhost:4000'
const url = `${baseUrl}/transactions`;

function translateStatusToErrorMessage(status: number) {
    switch (status) {
        case 401:
            return 'Please login again.';
        case 403:
            return 'You do not have permission to view the transaction(s).';
        default:
            return 'There was an error retrieving the transaction(s). Please try again.';
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

function convertToTransactionModels(data: any[]): Transaction[] {
    console.log(data)
    let transactions: Transaction[] = data.map(convertToTransactionModel);
    return transactions;
}

function convertToTransactionModel(item: any): Transaction {
    return new Transaction(item);
}

const transactionAPI = {
    get(page = 1, limit = 20) {
        return fetch(`${url}?_page=${page}&_limit=${limit}&_sort=name`)
            .then(delay(100))
            .then(checkStatus)
            .then(parseJSON)
            .then(convertToTransactionModels)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error retrieving the transactions. Please try again.'
                );
            });
    },
    post(transaction: Transaction) {
        return fetch(`${url}`, {
            method: 'POST', body: JSON.stringify(transaction), headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(delay(100))
            .then(checkStatus)
            .then(parseJSON)
            // .then(convertToTransactionModels)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error retrieving the transactions. Please try again.'
                );
            });
    },
};

export { transactionAPI };