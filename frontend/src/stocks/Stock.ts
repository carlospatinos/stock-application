export class Stock {
    id: number | undefined;
    name: string = '';
    description: string = '';
    imageUrl: string = '';
    price: number = 0;
    availableUnits: number | undefined;
    isActive: boolean = false;
    amount: number = 0;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.id) this.id = initializer.id;
        if (initializer.name) this.name = initializer.name;
        if (initializer.description) this.description = initializer.description;
        if (initializer.imageUrl) this.imageUrl = initializer.imageUrl;
        if (initializer.price) this.price = initializer.price;
        if (initializer.availableUnits) this.availableUnits = initializer.availableUnits;
        if (initializer.isActive) this.isActive = initializer.isActive;
        if (initializer.amount) this.amount = initializer.amount;
    }
}