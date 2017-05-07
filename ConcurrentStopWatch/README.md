CONCURRENT STOPWATCH
===

This project is a thread-safe StopWatch object that can be used for timing laps.  The stopwatch objects are created in the StopwatchFactory.  Different threads can share a single stopwatch object and safely call any of the stopwatch methods.

**Package edu.nyu.pqs.stopwatch.impl** contains the StopWatch and a StopWatch factory 

**Package edu.nyu.pqs.stopwatch.api** contains an interface that is implemented by the StopWatch

**Package edu.nyu.pqs.stopwatch.demo** contains a simple test class



Example
---
The StopWatch Object supports the typical operations of a StopWatch:

`Stopwatch stopwatch = StopWatchFactory.getStopwatch(
"ID");` creates a new StopWatch Object with the id: 'ID'.

**The methods that the StopWatch has are:**
`start()` to begin the timer

`lap()`   to store the time elapsed since the last time lap() was called or since start() was called if this is the first lap

`stop()` stops the stopwatch (and records one final lap)

`reset()` resets the stopwatch

`getLapTimes()` returns a list of lap times (in milliseconds).  This method can be called at any time


Running the example Test
---
when inside src folder
 
compile: `javac edu/nyu/pqs/stopwatch/demo/SlowThinker.java`

run: `java edu.nyu.pqs.stopwatch.demo.SlowThinker`

