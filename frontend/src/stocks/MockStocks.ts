import { Stock } from './Stock';

export const MOCK_STOCK = [
    new Stock({
        id: 1,
        name: 'SSTK',
        price: 10,
        availableUnits: 20
    }),
    new Stock({
        id: 2,
        name: 'COCA',
        price: 12.5,
        availableUnits: 30
    }),
    new Stock({
        id: 3,
        name: 'ERIC',
        price: 12.5,
        availableUnits: 30
    }),
    new Stock({
        id: 4,
        name: 'OKTA',
        price: 12.5,
        availableUnits: 30
    }),
    new Stock({
        id: 5,
        name: 'ALPHA',
        price: 12.5,
        availableUnits: 30
    })
];