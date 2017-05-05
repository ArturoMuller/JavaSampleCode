package Chapter11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeSortedFiles {
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
		PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, new Comparator<ArrayEntry>() {
			@Override
			public int compare(ArrayEntry o1, ArrayEntry o2) {
				return Integer.compare(o1.value, o2.value);// here it returns
															// positive if first
															// is less than the
															// second so
															// priority queue
															// puts the positive
															// at the top
			}
		});

		List<Integer> heads = new ArrayList<>(sortedArrays.size());

		// puts each sortedArrays' first element in minHeap;

		for (int i = 0; i < sortedArrays.size(); ++i) {
			if (sortedArrays.get(i).size() > 0) {
				minHeap.add(new ArrayEntry(sortedArrays.get(i).get(0), i));
				heads.add(1);
			} else {
				heads.add(0);
			}
		}

		List<Integer> result = new ArrayList<>();
		ArrayEntry headEntry;
		while ((headEntry = minHeap.poll()) != null) {
			result.add(headEntry.value);
			List<Integer> smallestArray = sortedArrays.get(headEntry.arrayId);
			int smallestArrayHead = heads.get(headEntry.arrayId);
			if (smallestArrayHead < smallestArray.size()) {
				// Add the next entry of the smallesArray into minHeap.
				minHeap.add(new ArrayEntry(smallestArray.get(smallestArrayHead), headEntry.arrayId));
				heads.set(headEntry.arrayId, heads.get(headEntry.arrayId) + 1);
			}
		}
		return result;
	}

}
