ObservableConnectFour
===
This project is a connect four game implemented using java SWING

Package **connectfour** contains the connect four game and everything needed

Package **connectfourTest** contains junit tests for the game logic

Package **connectfourView** uses the connectfour package and its classes to load a version of the game

Example code to run the game from any class
---
Using the connectfour factory
```
import connectfour.*;

public class ConnectFourApp {
public static void main(String[] args) {
ConnectFourFactory.getConnectFourSwing();
}
}
``````

Running the connectfourView packages using connectFourApp
---
inside src folder 

compile `javac connectfourView/ConnectFourApp.java`

run `java connectfourView/ConnectFourApp`
