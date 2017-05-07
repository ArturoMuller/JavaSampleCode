ObservableConnectFour
===
This project is a connect four game implemented using SWING

Package **connectfour** contains the connect four game and everything need

Package **connectfourTest** contains unit tests for the games logic

Package **connectfourview** uses the connectfour package and its classes to loead a version of the game

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

Running the connectfourview package's connectFourApp
---
inside src folder 

compile `javac connectfourview/ConnectFourApp.java`

run `java connectfourview/ConnectFourApp`
