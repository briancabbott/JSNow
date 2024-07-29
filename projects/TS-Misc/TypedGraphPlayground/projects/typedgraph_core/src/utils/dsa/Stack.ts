
export interface IStack<T> {
    push<T>(t: T): void;
    pop(): T;
    peek() : T;
}

export class Stack<T> implements IStack<T> {
    push<T>(t: T): void {
        throw new Error("Method not implemented.");
    }
    pop(): T {
        throw new Error("Method not implemented.");
    }
    peek(): T {
        throw new Error("Method not implemented.");
    }
    
}