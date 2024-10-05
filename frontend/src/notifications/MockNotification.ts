import { Notification } from './Notification';

export const MOCK_PROJECTS = [
    new Notification({
        title: 'Stock transaction successful',
        description:
            'Now you are the owner of 10 NASDAQ stocks',
        imageUrl: '/notification.png',
        isActive: true,
    }),
];