
export interface IStack<T> {
    push<T>(t: T): void;
    pop(): T;
    peek() : T;
}

export class Stack<T> implements IStack<T> {
    
}