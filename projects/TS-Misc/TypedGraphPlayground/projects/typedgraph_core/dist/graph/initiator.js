// Initiator -- Could also be called Generator
// Creation helper of Various Graphs, Graph Types
var GraphType;
(function (GraphType) {
    GraphType[GraphType["SimpleBase"] = 0] = "SimpleBase";
    GraphType[GraphType["Acyclic"] = 1] = "Acyclic";
    GraphType[GraphType["DirectedAcyclic"] = 2] = "DirectedAcyclic"; // Directed Acyclic Graph
})(GraphType || (GraphType = {}));
export {};
