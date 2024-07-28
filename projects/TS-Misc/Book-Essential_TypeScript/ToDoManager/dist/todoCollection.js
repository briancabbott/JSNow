import { ToDoItem } from "./todoItem.js";
export class ToDoCollection {
    userName;
    nextId = 1;
    itemMap = new Map();
    constructor(userName, todoItems = []) {
        this.userName = userName;
        if (todoItems !== undefined && todoItems.length > 0) {
            todoItems.forEach(item => this.itemMap.set(item.id, item));
        }
    }
    addToDo(task) {
        while (this.getToDoById(this.nextId)) {
            this.nextId++;
        }
        this.itemMap.set(this.nextId, new ToDoItem(this.nextId, task));
        return this.nextId;
    }
    getToDoById(id) {
        return this.itemMap.get(id);
    }
    getToDoItems(includeComplete) {
        return [...this.itemMap.values()].filter(item => includeComplete || !item.complete);
    }
    markComplete(id, complete) {
        const todoItem = this.getToDoById(id);
        if (todoItem) {
            todoItem.complete = complete;
        }
    }
    removeComplete() {
        this.itemMap.forEach(item => {
            if (item.complete) {
                this.itemMap.delete(item.id);
            }
        });
    }
    getItemCounts() {
        return {
            total: this.itemMap.size,
            incomplete: this.getToDoItems(false).length
        };
    }
}
