package FullSearch;

import java.io.IOException;
import java.util.HashMap;

class Tree {

	public int x;
	public Tree l;
	public Tree r;

	public Tree(int x) {
		this.x = x;
	}
}

public class OddOccurrencesInArray {

	static public void main(String[] args) throws IOException {

//		Tree root = new Tree(1);
//		root.l = new Tree(2);
//		root.r = new Tree(3);
//		root.l.l = new Tree(4);
//		root.l.r = new Tree(5);
//		root.r.l = new Tree(6);
//		root.r.r = new Tree(3);
//		root.r.l.r = new Tree(8);
//		root.r.r.r = new Tree(9);
//		root.r.r.r.r = new Tree(10);
//		root.r.r.r.r.r = new Tree(11);
		
		
		Tree root = new Tree(4);
		root.l = new Tree(5);
		root.l.l = new Tree(4);
		root.l.l.l = new Tree(5);
		root.r = new Tree(6);
		root.r.l = new Tree(1);
		root.r.r = new Tree(6);
		
		System.out.println(solution(root));
	}

	static public int solution(Tree t) {
		if (t == null) {
			return 0;
		}
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		return largestUinquePath(t, hash);
	}

	static public int largestUinquePath(Tree t, HashMap<Integer, Integer> m) {
		if (t == null) {
			return m.size();
		}

		if (m.containsKey(t.x)) {
			m.put(t.x, m.get(t.x) + 1);
		} else {
			m.put(t.x, 1);
		}

		int max_path = Math.max(largestUinquePath(t.l, m), largestUinquePath(t.r, m));

		if (m.containsKey(t.x)) {
			m.put(t.x, m.get(t.x) - 1);
		}

		if (m.get(t.x) == 0) {
			m.remove(t.x);
		}
		return max_path;
	}

}
