import { Graph } from "./graph.js";

// ISelector

// IVisitor


// Things that just generally traverse our Graphs... coule be Visitors, etc.s
export interface ITraversor {
    traverse(graph: Graph): void;
}