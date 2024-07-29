import { Graph } from "./graph.js";


// Things that just generally traverse our Graphs... coule be Visitors, etc.s
export interface ITraversor {
    traverse(graph: Graph): void;
}