export class Notification {
    title: string = '';
    description: string = '';
    imageUrl: string = '';
    isActive: boolean = false;

    constructor(initializer?: any) {
        if (!initializer) return;
        if (initializer.title) this.title = initializer.title;
        if (initializer.description) this.description = initializer.description;
        if (initializer.imageUrl) this.imageUrl = initializer.imageUrl;
        if (initializer.isActive) this.isActive = initializer.isActive;
    }
}