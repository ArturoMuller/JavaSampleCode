FILTER ITERATOR AND GRAPH 
===

This project is a Library for an undirected GRAPH with generic node values.
The Graph class has methods `BFSIterator()` and `DFSIterator()` that return an iterator that iterates through the graph from the source using the Breadth first search algorithm for BFS and the Depth first search algorithm for DFS.

The Filter Iterator works the same way as the GRAPH but has the option to filter out any node values based on user rules.


**Package graph** contains the graph and all its classes 

**Package graphTest** contains JUNIT tests for the graph API

Example
---
 

**Example for using the BFS and DFS Iterators of the Graph Package's Graph Class:**
```
Node<String> r, s, t;
Graph<String> p;
s = new Node<String>("s");
r = new Node<String>("r");
t = new Node<String>("t");
p = new Graph<String>(s);
p.addNode(t);
p.addNode(r);
p.addEdge(s, r, 20);
p.addEdge(s, t, 15);
Iterator<String> itrBFS = p.BFSIterator();
Iterator<String> itrDFS = p.DFSIterator();
`````

**Example for creating a simple graph and using rules to initialize the FilterIterator class:**
```
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
``````

For further examples on how to use the Graph please review the Package **graphTest**  in the testsrc directory
