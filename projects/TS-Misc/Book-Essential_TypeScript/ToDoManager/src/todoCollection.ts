
import { ToDoItem } from "./todoItem.js";

type ItemCounts = {
    total: number,
    incomplete: number
}

export class ToDoCollection {
    private nextId: number = 1;
    private itemMap: Map<Number, ToDoItem> = new Map<Number, ToDoItem>();

    constructor(public userName: string, todoItems: ToDoItem[] = []) {
        if (todoItems !== undefined && todoItems.length > 0) {
            todoItems.forEach(item => this.itemMap.set(item.id, item));
        }
    }

    addToDo(task: string): number {
        while (this.getToDoById(this.nextId)) {
            this.nextId++;
        }
        this.itemMap.set(this.nextId, new ToDoItem(this.nextId, task));
        return this.nextId;
    }

    getToDoById(id: number): ToDoItem {
        return this.itemMap.get(id);
    }

    getToDoItems(includeComplete: boolean) {
        return [...this.itemMap.values()].filter(item => includeComplete || !item.complete);
    }

    markComplete(id: number, complete: boolean) {
        const todoItem = this.getToDoById(id);
        if (todoItem) {
            todoItem.complete = complete;
        }
    }

    removeComplete() {
        this.itemMap.forEach(item => { 
            if (item.complete) { 
                this.itemMap.delete(item.id)
            } 
        });
    }

    getItemCounts(): ItemCounts {
        return {
            total: this.itemMap.size,
            incomplete: this.getToDoItems(false).length
        };
    }
}