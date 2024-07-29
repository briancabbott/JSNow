
import { ToDoItem } from "./todoItem.js";
import { ToDoCollection } from "./todoCollection.js";
import { LowSync } from "lowdb";
import { JSONFileSync } from "lowdb/node";

type SchemaType = {
    tasks: {
        id: number;
        task: string;
        complete: boolean;
    }[]
}

export class JsonToDoCollection extends ToDoCollection {
    private database: LowSync<SchemaType>;

    constructor(public userName: string, todoItems: ToDoItem[] = []) {
        super(userName, []);

        this.database = new LowSync(new JSONFileSync("todos_database.json"));
        this.database.read();

        if (this.database.data === null) {
            this.database.data = {tasks : todoItems};
            this.database.write();
            todoItems.forEach(item => this.itemMap.set(item.id, item));
        } else {
            this.database.data.tasks.forEach(item => this.itemMap.set(item.id, new ToDoItem(item.id, item.task, item.complete)));
        }
    }

    addToDo(task: string): number {
        let result = super.addToDo(task);
        this.storeTasks();
        return result;
    }

    markComplete(id: number, complete: boolean): void {
        super.markComplete(id, complete);
        this.storeTasks();
    }

    removeComplete(): void {
        super.removeComplete();
        this.storeTasks();
    }

    private storeTasks() {
        this.database.data.tasks = [...this.itemMap.values()];
        this.database.write();
    }
}