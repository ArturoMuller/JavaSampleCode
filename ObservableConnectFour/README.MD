ObservableConnectFour
===
This project is a connect four API

Package **connectfour** contains the entire API

Package **connectfourTest** contains unit tests on the games logic

Package **connectfourview** runs a version of the game that I made with SWING

Example
---
to run a connectfour game from the connectfour factory
```
import connectfour.*;

public class ConnectFourApp {
public static void main(String[] args) {
ConnectFourFactory.getConnectFourSwing();
}
}
``````

Running the version of the created game
---
inside src folder 
copmile `javac connectfourview/ConnectFourApp.java`
run `java connectfourview/ConnectFourApp`
