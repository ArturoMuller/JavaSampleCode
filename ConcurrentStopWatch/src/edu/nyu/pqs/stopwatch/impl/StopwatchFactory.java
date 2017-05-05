package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
import java.lang.IllegalStateException;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects. It maintains
 * references to all created Stopwatch objects and provides a convenient method for getting a list
 * of those objects.
 *
 */
public class StopwatchFactory {
  private static Object lockB = new Object();
  private static HashMap<String, Stopwatch> stopWatches = new HashMap<String, Stopwatch>();

  /**
   * Creates and returns a new StopWatch object
   * 
   * @param id The identifier of the new object
   * @return The new StopWatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or already taken.
   */
  public static StopWatch getStopwatch(final String id) {
    synchronized (lockB) {
      if (stopWatches.containsKey(id)) {
        throw new IllegalArgumentException("id already exists");
      }
      if (id == null) {
        throw new NullPointerException("id cannot be null");
      }
      if (id.equals("")) {
        throw new IllegalArgumentException("id cannot be empty");
      }
      StopWatch current = new StopWatch(id);
      stopWatches.put(id, current);
      return current;
    }
  }

  /**
   * Returns a list of all created stopwatches
   * 
   * @return a List of all created StopWatch objects. Returns an empty list if no StopWatches have
   *         been created.
   */
  public static List<Stopwatch> getStopwatches() {
    synchronized (lockB) {
      List<Stopwatch> copystopWatches = new ArrayList<Stopwatch>(stopWatches.values());
      return copystopWatches;
    }
  }



  /**
   * The StopWatch is a thread-safe static inner class for a StopWatch object. It maintains provides
   * all the methods a normal StopWath has and a method for getting a list of all the laps.
   *
   */
  public static class StopWatch implements Stopwatch {
    private final String ID;
    private long startTime = 0;
    private long stopTime = 0;
    private long lastTime = 0;
    private boolean running = false;
    private ArrayList<Long> laps = new ArrayList<Long>();
    private Object lockA;

    public StopWatch(String id) {
      this.ID = id;
      lockA = new Object();
    }

    @Override
    public String getId() {
      return ID;
    }


    @Override
    public void start() {
      synchronized (lockA) {
        if (running == false) {
          if (stopTime == 0) {
            startTime = System.currentTimeMillis();
            running = true;
          } else {
            startTime += System.currentTimeMillis() - stopTime;
            laps.remove(laps.size() - 1);
            running = true;
          }
        } else {
          throw new IllegalStateException("stop watch is already running");
        }
      }
    }

    @Override
    public void lap() {
      synchronized (lockA) {
        if (running == true && laps.size() == 0) {
          lastTime = System.currentTimeMillis();
          long timeElapsed = lastTime - startTime;
          laps.add(new Long(timeElapsed));
        } else if (running == true && laps.size() > 0) {
          long tempLastTime = System.currentTimeMillis();
          long timeElapsed = (tempLastTime - lastTime);
          lastTime = tempLastTime;
          laps.add(new Long(timeElapsed));
        } else {
          throw new IllegalStateException("stop watch is not running");
        }
      }
    }

    @Override
    public void stop() {
      synchronized (lockA) {

        if (running == true) {
          stopTime = System.currentTimeMillis();
          long timeElapsed = (stopTime - startTime);
          laps.add(new Long(timeElapsed));
          running = false;
        } else {
          throw new IllegalStateException("stop watch is not running");
        }
      }
    }



    @Override
    public void reset() {
      synchronized (lockA) {
        startTime = 0;
        stopTime = 0;
        lastTime = 0;
        running = false;
        laps = new ArrayList<Long>();
      }
    }

    @Override
    public List<Long> getLapTimes() {
      synchronized (lockA) {
        ArrayList<Long> copylaps = new ArrayList<Long>(laps);
        return copylaps;
      }
    }

    @Override
    public boolean equals(Object comparingWatch) {
      if (comparingWatch instanceof StopWatch) {
        if (this.ID.equals(((StopWatch) comparingWatch).getId())) {
          return true;
        } else {
          return false;
        }
      } else {
        throw new IllegalArgumentException("must be a StopWatch object");
      }
    }

    @Override
    public int hashCode() {
      return this.ID.hashCode();
    }

    /**
     * Returns a brief description of this StopWatch. The exact details of the representation are
     * unspecified and subject to change, but the following may be regarded as typical:
     * 
     * "[id:  meet_18, [elapsed time | stop time]:  8923, total laps(including stop):  10}"
     */
    @Override
    public String toString() {
      synchronized (lockA) {
        if (running == false) {
          return "id:  " + getId() + ", stop time:  " + ((stopTime - startTime)) + ", total laps:  "
              + laps.size();
        } else {
          return "id:  " + getId() + ", elapsed time:  " + (System.currentTimeMillis() - startTime)
              + ", total laps:  " + laps.size();
        }
      }
    }

  }
}
