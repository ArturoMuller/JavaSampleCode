package edu.nyu.pqs.stopwatch.impl;

import java.lang.IllegalStateException;
import java.util.ArrayList;
import java.util.List;

import stopwatchApi.Stopwatch;

/**
 * The StopWatch is a thread-safe inner class for a StopWatch object. It
 * maintains all the methods a normal StopWath has and a method for getting a
 * list of all the laps.
 */
class StopWatch implements Stopwatch {
	private final String ID;
	private long startTime = 0;
	private long stopTime = 0;
	private long lapTime = 0;
	private boolean running = false;
	private ArrayList<Long> laps = new ArrayList<Long>();
	private Object lock;

	public StopWatch(String id) {
		this.ID = id;
		lock = new Object();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void start() {
		synchronized (lock) {
			if (!running) {
				if (stopTime == 0) {
					startTime = System.currentTimeMillis();
					running = true;
				} else {
					startTime += System.currentTimeMillis() - lapTime;
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
		synchronized (lock) {
			if (running && laps.size() == 0) {
				lapTime = System.currentTimeMillis();
				long timeElapsed = lapTime - startTime;
				laps.add(new Long(timeElapsed));
			} else if (running && laps.size() > 0) {
				long temp_lapTime = System.currentTimeMillis();
				long timeElapsed = temp_lapTime - lapTime;
				lapTime = temp_lapTime;
				laps.add(new Long(timeElapsed));
			} else {
				throw new IllegalStateException("stop watch is not running");
			}
		}
	}

	@Override
	public void stop() {
		synchronized (lock) {
			if (running && laps.size() == 0) {
				lapTime = System.currentTimeMillis();
				stopTime = lapTime;
				laps.add(new Long(lapTime - startTime));
				running = false;
			} else if (running && laps.size() > 0) {
				long temp_lapTime = System.currentTimeMillis();
				long timeElapsed = temp_lapTime - lapTime;
				lapTime = temp_lapTime;
				stopTime = temp_lapTime;
				laps.add(new Long(timeElapsed));
			} else {
				throw new IllegalStateException("stop watch is not running");
			}
		}
	}

	@Override
	public void reset() {
		synchronized (lock) {
			startTime = 0;
			stopTime = 0;
			lapTime = 0;
			running = false;
			laps = new ArrayList<Long>();
		}
	}

	@Override
	public List<Long> getLapTimes() {
		synchronized (lock) {
			ArrayList<Long> copylaps = new ArrayList<Long>(laps);
			return copylaps;
		}
	}

	@Override
	public boolean equals(Object comparingWatch) {
		if (comparingWatch instanceof StopWatch) {
			if (ID.equals(((Stopwatch) comparingWatch).getId())) {
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
		return ID.hashCode();
	}

	/**
	 * Returns a brief description of this StopWatch. The exact details of the
	 * representation are unspecified and subject to change, but the following
	 * may be regarded as typical:
	 * 
	 * "[id: meet_18, [elapsed time | stop time]: 8923, total laps(including
	 * stop): 10}"
	 */
	@Override
	public String toString() {
		synchronized (lock) {
			if (!running) {
				return "id:  " + getId() + ", stop time:  " + ((stopTime - startTime)) + ", total laps:  "
						+ laps.size();
			} else {
				return "id:  " + getId() + ", elapsed time:  " + (System.currentTimeMillis() - startTime)
						+ ", total laps:  " + laps.size();
			}
		}
	}

}
