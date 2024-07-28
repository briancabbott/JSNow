"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const todoItem_1 = require("./todoItem");
const todoCollection_1 = require("./todoCollection");
let todos = [
    new todoItem_1.ToDoItem(1, "qwerty"),
    new todoItem_1.ToDoItem(2, "asdf"),
    new todoItem_1.ToDoItem(3, "zxcv"),
    new todoItem_1.ToDoItem(4, "yuiop", true)
];
let collection = new todoCollection_1.ToDoCollection("Brian", todos);
console.clear();
console.log(`${collection.userName}'s ToDo List`);
let newId = collection.addToDo("Read Graph Theory Stuff");
let todoItem = collection.getToDoById(newId);
todoItem.printDetails();
