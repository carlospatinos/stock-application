
import { NotificationSubscription } from './NotificationSubscription';
// TODO move to a config file
const baseUrl = 'http://localhost:8082/api';
// const baseUrl = 'http://localhost:4000'
const url = `${baseUrl}/subscription`;

function translateStatusToErrorMessage(status: number) {
    switch (status) {
        case 401:
            return 'Please login again.';
        case 403:
            return 'You do not have permission to view the subscription(s).';
        default:
            return 'There was an error retrieving the subscription(s). Please try again.';
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

function convertToNotificationModels(data: any[]): NotificationSubscription[] {
    let subscriptions: NotificationSubscription[] = data.map(convertToNotificationModel);
    return subscriptions;
}

function convertToNotificationModel(item: any): NotificationSubscription {
    return new NotificationSubscription(item);
}

const subscriptionAPI = {
    post(subscription: NotificationSubscription) {
        return fetch(`${url}`, {
            method: 'POST', body: JSON.stringify(subscription), headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(delay(100))
            .then(checkStatus)
            // .then(parseJSON)
            // .then(convertToNotificationModels)
            .catch((error: TypeError) => {
                console.log('log client error ' + error);
                throw new Error(
                    'There was an error posting the subscriptions. Please try again.'
                );
            });
    }
};

export { subscriptionAPI };