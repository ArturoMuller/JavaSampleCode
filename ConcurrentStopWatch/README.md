CONCURRENT STOPWATCH
===

This project A thread-safe StopWatch object that can be used for timing laps.  The stopwatch
objects are created in the StopwatchFactory.  Different threads can
share a single stopwatch object and safely call any of the stopwatch methods.

**Package impl** contains the StopWatch and a StopWatch factory 

**Package api** contains an interface that is implemented by the StopWatch

**Package demo** contains a simple test class



Example
---
The StopWatch Object supports the typical operations of a StopWatch:

`Stopwatch stopwatch = StopWatchFactory.getStopwatch(
"ID " + Thread.currentThread().getId());` creates a new StopWatch Object with its ID
**The methods that the StopWatch has are:**
`start()` begins the timer
`lap()`   Stores the time elapsed since the last time lap() was called or since start() was called if 
this is the first lap
`stop()` Stops the stopwatch (and records one final lap)
`reset()` Resets the stopwatch
and `getLapTimes()` Returns a list of lap times (in milliseconds).  This method can be called at any time.


