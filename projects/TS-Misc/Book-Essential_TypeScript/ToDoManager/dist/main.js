import { ToDoItem } from "./todoItem.js";
import { ToDoCollection } from "./todoCollection.js";
let todos = [
    new ToDoItem(1, "qwerty"),
    new ToDoItem(2, "asdf"),
    new ToDoItem(3, "zxcv"),
    new ToDoItem(4, "yuiop", true)
];
let collection = new ToDoCollection("Brian", todos);
console.clear();
console.log(`${collection.userName}'s ToDo List`);
let newId = collection.addToDo("Read Graph Theory Stuff");
let todoItem = collection.getToDoById(newId);
todoItem.printDetails();
