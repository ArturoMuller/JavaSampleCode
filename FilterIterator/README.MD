FILTER ITERATOR and GRAPH 
===


This project is a Library for an undirected GRAPH with generic node values.
The Graph class returns an iterator that can be iterated by using DFS and BFS algorithms
The Filter Iterator works the same way as the GRAPH but has the option to filter out any node values based on user rules.


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
p = new Graph<String>(s);
p.addNode(t);
p.addNode(r);
p.addEdge(s, r, 20);
p.addEdge(s, t, 15);
Iterator<String> itrBFS = p.BFSIterator();
Iterator<String> itrDFS = p.DFSIterator();
`````

**Example of using the FilterIterator class from the GraphPackage's FilterIterator Class::**
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


