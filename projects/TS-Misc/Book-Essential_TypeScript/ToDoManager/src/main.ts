
import { ToDoItem } from "./todoItem.js";
import { ToDoCollection } from "./todoCollection.js";
import inquirer from "inquirer";

let todos: ToDoItem[] = [
    new ToDoItem(1, "qwerty"),
    new ToDoItem(2, "asdf"),
    new ToDoItem(3, "zxcv"),
    new ToDoItem(4, "yuiop", true)
];

let collection: ToDoCollection = new ToDoCollection("Brian", todos);

function displayToDoList(): void {
    console.log(`${collection.userName}'s ToDo List (${collection.getItemCounts().incomplete} items to do)`);
    collection.getToDoItems(true).forEach(item => item.printDetails());
}

enum Commands {
    Quit = "Quit"
}

function promptUser(): void {
    console.clear();
    displayToDoList();
    inquirer.prompt({
        type: "list",
        name: "command",
        message: "Choose Option",
        choices: Object.values(Commands)
    }).then(answers => {
        if (answers["command"] !== Commands.Quit) {
            promptUser();
        }
    })
}
promptUser();