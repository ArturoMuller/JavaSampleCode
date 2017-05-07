FILTER ITERATOR
===


This project is an undirected GRAPH with generic node values and Integer edge weights.
The Graph class returns an iterator that can be iterated by using DFS and BFS algorithms
The Filter Iterator works the same way as the mentioned one exept that it filters out any 
predicates used it's constructor


**Package graph** contains the graph and all its classes 

**Package graphTest** contains a test for all public clsess

Example
---
A good Example on how to use the Graph can be found in various classes of the **graphTest** Package 
**Example of using BFS and DFS Iterators of the GraphPackage's Graph Class:**
```
Node<String> r, s, t;
Graph<String> p;
s = new Node<String>("s");
r = new Node<String>("r");
t = new Node<String>("t");
//graph must be created with a node
p = new Graph<String>(s);
//add any other remaining nodes to graph
p.addNode(t);
p.addNode(r);
//add edges and their weights
p.addEdge(s, r, 20);
p.addEdge(s, t, 15);
//Initialize and use BFS Iterator as needed
Iterator<String> itrBFS = p.BFSIterator();
//or Initialize and use DFS Iterator as needed
Iterator<String> itrDFS = p.DFSIterator();
// use Iterator as needed

**Example of using the FilterIterator class from the GraphPackage's FilterIterator Class::**
Node<String> r, s, t;
Graph<String> p;
p = new Graph<String>(s);
s = new Node<String>("sarasota");
r = new Node<String>("raleigh");
t = new Node<String>("toledo");
p.addEdge(s, r, 20);
p.addEdge(s, t, 15);

//predicates creation for nodes of cities whose names are shorter than 8 characters 
Predicate<String> lessThanEight = new Predicate<String>() {
@Override
public boolean accept(String city) {
return city.length() < 8;
}
};

//predicates creation for nodes of cities whose names contain the character e 
Predicate<String> containsE = new Predicate<String>() {
@Override
public boolean accept(String city) {
return city.contains("e");
}
};

//Create an iterator that connects these two predicates with an and statement
Iterator<String> twoAndPred = new FilterIterator<String>(p.BFSIterator(),
new PredicateAnd<String>(containsE, lessThanEight));
// use Iterator as needed



