export class Stock {
    name: string = '';
    price: number | undefined;
    availableUnits: number | undefined;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.name) this.name = initializer.title;
        if (initializer.price) this.price = initializer.price;
        if (initializer.availableUnits) this.availableUnits = initializer.availableUnits;
    }
}