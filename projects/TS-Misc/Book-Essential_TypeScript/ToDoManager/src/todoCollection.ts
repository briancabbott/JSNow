
import { ToDoItem } from "./todoItem";

export class ToDoCollection {
    private nextId: number = 1;

    constructor(public userName: string, public todoItems: ToDoItem[] = []) {}

    addToDo(task: string): number {
        while (this.getToDoById(this.nextId)) {
            this.nextId++;
        }
        this.todoItems.push(new ToDoItem(this.nextId, task));
        return this.nextId;
    }

    getToDoById(id: number): ToDoItem {
        return this.todoItems.find(item => item.id === id);
    }

    markComplete(id: number, complete: boolean) {
        const todoItem = this.getToDoById(id);
        if (todoItem) {
            todoItem.complete = complete;
        }
    }
}