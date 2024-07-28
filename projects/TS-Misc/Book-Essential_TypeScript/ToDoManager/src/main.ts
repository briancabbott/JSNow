
import { ToDoItem } from "./todoItem";
import { ToDoCollection } from "./todoCollection";

let todos: ToDoItem[] = [
    new ToDoItem(1, "qwerty"),
    new ToDoItem(2, "asdf"),
    new ToDoItem(3, "zxcv"),
    new ToDoItem(4, "yuiop", true)
];

let collection: ToDoCollection = new ToDoCollection("Brian", todos);

console.clear();
console.log(`${collection.userName}'s ToDo List`);

let newId: number = collection.addToDo("Read Graph Theory Stuff");
let todoItem: ToDoItem = collection.getToDoById(newId);
todoItem.printDetails();