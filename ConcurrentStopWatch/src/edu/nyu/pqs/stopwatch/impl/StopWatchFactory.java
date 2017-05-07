package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import stopwatchApi.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for StopWatch objects. It maintains
 * references to all created StopWatch objects and provides a convenient method for getting a list
 * of those objects.
 *
 */
public class StopWatchFactory {
  private static Set<Stopwatch> stopWatches = new HashSet<Stopwatch>();

  /**
   * Creates and returns a new StopWatch object
   * 
   * @param id The identifier of the new object
   * @return The new StopWatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or already taken.
   */
  public static StopWatch getStopwatch(final String id) {
      if (stopWatches.contains(id)) {
        throw new IllegalArgumentException("id already exists");
      }
      if (id == null) {
        throw new IllegalArgumentException("id cannot be null");
      }
      if (id.equals("")) {
        throw new IllegalArgumentException("id cannot be empty");
      }
      StopWatch current = new StopWatch(id);
      stopWatches.add(current);
      return current;

  }

  /**
   * Returns a list of all created StopWatches
   * 
   * @return a List of all created StopWatch objects. Returns an empty list if no StopWatches have
   *         been created.
   */
  public static List<Stopwatch> getStopwatches() {
      return new ArrayList<Stopwatch>(stopWatches);
  }

}
