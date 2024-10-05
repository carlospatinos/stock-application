import { Stock } from './Stocks';

export const MOCK_STOCK = [
    new Stock({
        name: 'SSTK',
        price: 10,
        availableUnits: 20
    }),
    new Stock({
        name: 'COCA',
        price: 12.5,
        availableUnits: 30
    })
];