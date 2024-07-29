
import { ToDoItem } from "./todoItem.js";
import { ToDoCollection } from "./todoCollection.js";
import inquirer from "inquirer";
import { JsonToDoCollection } from "./jsonTodoCollection.js";

let todos: ToDoItem[] = [
    new ToDoItem(1, "qwerty"),
    new ToDoItem(2, "asdf"),
    new ToDoItem(3, "zxcv"),
    new ToDoItem(4, "yuiop", true)
];

let collection: ToDoCollection = new JsonToDoCollection("Brian", todos);
let showCompleted = true;
enum Commands {
    Add = "Add New Task",
    Complete = "Complete Task",
    Toggle = "Show/Hide Completed",
    Purge = "Remove Commpleted Tasks",
    Quit = "Quit"
}

function displayToDoList(): void {
    console.log(`${collection.userName}'s ToDo List (${collection.getItemCounts().incomplete} items to do)`);
    collection.getToDoItems(showCompleted).forEach(item => item.printDetails());
}

function promptAdd(): void {
    console.clear();
    inquirer.prompt(
        {
            type: "input",
            name: "add",
            message: "Enter Task:"
        }
    ).then(
        answers => {
            if (answers["add"] !== "") {
                collection.addToDo(answers["add"]);
            }
            promptUser();
        }
    );
}

function promptComplete(): void {
    console.clear();
    inquirer.prompt(
        {
            type: "checkbox",
            name: "complete",
            message: "Mark Tasks Complete",
            choices: collection.getToDoItems(false).map(item => ({name: item.task, value: item.id, checked: item.complete}))
        }).then(answers => {
            let completedTasks = answers["complete"] as number[];
            collection.getToDoItems(true).forEach(item => collection.markComplete(item.id, completedTasks.find(id => id === item.id) != undefined));
            promptUser();
        });
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
        switch (answers["command"]) {
            case Commands.Toggle:
                showCompleted = !showCompleted;
                promptUser();
                break;
            case Commands.Add:
                promptAdd();
                break;
            case Commands.Complete:
                if (collection.getItemCounts().incomplete > 0) {
                    promptComplete();
                } else {
                    promptUser();
                }
                break;
            case Commands.Purge:
                collection.removeComplete();
                promptUser();
                break;
        }
    })
}
promptUser();