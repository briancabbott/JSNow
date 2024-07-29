
export interface IQueue<T> {
    enqueue<T>(element: T): void;
    deque<T>(): T;
}

export class Queue<T> implements IQueue<T> {
    enqueue<T>(element: T): void {
        throw new Error("Method not implemented.");
    }
    
    deque<T>(): T {
        throw new Error("Method not implemented.");
    }
}