export class NotificationSubscription {
    user: string = '';
    token: string = '';

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.user) this.user = initializer.user;
        if (initializer.token) this.token = initializer.token;
    }
}