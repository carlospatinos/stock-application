export class Stock {
    id: number | undefined;
    name: string = '';
    price: number | undefined;
    availableUnits: number | undefined;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.id) this.id = initializer.id;
        if (initializer.name) this.name = initializer.name;
        if (initializer.price) this.price = initializer.price;
        if (initializer.availableUnits) this.availableUnits = initializer.availableUnits;
    }
}