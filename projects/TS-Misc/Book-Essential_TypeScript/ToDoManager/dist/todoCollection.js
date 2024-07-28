"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ToDoCollection = void 0;
const todoItem_1 = require("./todoItem");
class ToDoCollection {
    userName;
    todoItems;
    nextId = 1;
    constructor(userName, todoItems = []) {
        this.userName = userName;
        this.todoItems = todoItems;
    }
    addToDo(task) {
        while (this.getToDoById(this.nextId)) {
            this.nextId++;
        }
        this.todoItems.push(new todoItem_1.ToDoItem(this.nextId, task));
        return this.nextId;
    }
    getToDoById(id) {
        return this.todoItems.find(item => item.id === id);
    }
    markComplete(id, complete) {
        const todoItem = this.getToDoById(id);
        if (todoItem) {
            todoItem.complete = complete;
        }
    }
}
exports.ToDoCollection = ToDoCollection;
