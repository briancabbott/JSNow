import { ToDoItem } from "./todoItem.js";
import { ToDoCollection } from "./todoCollection.js";
import inquirer from "inquirer";
let todos = [
    new ToDoItem(1, "qwerty"),
    new ToDoItem(2, "asdf"),
    new ToDoItem(3, "zxcv"),
    new ToDoItem(4, "yuiop", true)
];
let collection = new ToDoCollection("Brian", todos);
function displayToDoList() {
    console.log(`${collection.userName}'s ToDo List (${collection.getItemCounts().incomplete} items to do)`);
    collection.getToDoItems(true).forEach(item => item.printDetails());
}
var Commands;
(function (Commands) {
    Commands["Quit"] = "Quit";
})(Commands || (Commands = {}));
function promptUser() {
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
    });
}
promptUser();
