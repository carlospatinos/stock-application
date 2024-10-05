export class Notification {
    title: string = '';
    description: string = '';
    image: string = '/notification.png';
    isActive: boolean = false;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.title) this.title = initializer.title;
        if (initializer.description) this.description = initializer.description;
        if (initializer.image) this.image = initializer.image;
        if (initializer.isActive) this.isActive = initializer.isActive;
    }
}