export class Transaction {
    userName: string = '';
    stockName: string = '';
    price: number = 0;
    amount: number = 0;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.userName) this.userName = initializer.userName;
        if (initializer.stockName) this.stockName = initializer.stockName;
        if (initializer.price) this.price = initializer.price;
        if (initializer.price) this.price = initializer.price;
        if (initializer.amount) this.amount = initializer.amount;
    }
}