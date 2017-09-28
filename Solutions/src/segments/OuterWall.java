package segments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//public class Main {
public class OuterWall {

	public static class Node {
		int x = 0;
		int y = 0;
		int radius = 0;
		boolean isVisit = false;

		Node parent = null;
		ArrayList<Node> child = new ArrayList<Node>();

		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.radius = d;
		}

		public boolean encloses(Node wall) {
			return (radius > wall.radius) && (sqrdist(wall) < sqr(radius - wall.radius));
		}

		private int sqrdist(Node wall) {
			return sqr(y - wall.y) + sqr(x - wall.x);
		}

		private int sqr(int x) {
			return x * x;
		}
	}

	public static class SortingDistane implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			if (o1.radius > o2.radius) {
				return -1;
			} else if (o1.radius < o2.radius) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	static ArrayList<Node> leafNode = new ArrayList<Node>();
	static int maxHeight = 0;
	static Node maxNode = null;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("outerwall"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < TC; i++) {
			int wallCount = Integer.parseInt(br.readLine().trim());
			leafNode = new ArrayList<Node>();
			List<Node> sortingNode = new ArrayList<Node>();
			for (int j = 0; j < wallCount; j++) {
				String[] t_input = br.readLine().trim().split(" ");
				int x = Integer.parseInt(t_input[0]);
				int y = Integer.parseInt(t_input[1]);
				int d = Integer.parseInt(t_input[2]);
				sortingNode.add(new Node(x, y, d));
			}
			Collections.sort(sortingNode, new SortingDistane());
			for (int n = 1; n < sortingNode.size(); n++) {
				addNode(sortingNode.get(0), sortingNode.get(n));
			}
			if (wallCount == 1) {
				System.out.println(0);
			} else {
				maxHeight = 0;
				int h = getBFSMaxDepth(sortingNode.get(0), 0);
				maxHeight = 0;
				h = getBFSMaxDepth(maxNode, 0);
				System.out.println(h);
			}
		}
	}

	public static boolean addNode(Node parent, Node child) {
		if (parent.encloses(child)) {
			if (parent.child.size() == 0) {
				child.parent = parent;
				parent.child.add(child);
				return true;
			} else {
				for (int i = 0; i < parent.child.size(); i++) {
					if (addNode(parent.child.get(i), child)) {
						return true;
					}
				}
			}
			child.parent = parent;
			parent.child.add(child);
			return true;
		}
		return false;
	}
		

	public static int getBFSMaxDepth(Node node, int depth) {
		node.isVisit = true;
		int h = 0;
		for (Node n : node.child) {
			if (n.isVisit == true) {
				continue;
			}
			depth++;
			h = Math.max(h, 1 + getBFSMaxDepth(n, depth));
			depth--;
		}
		if (node.parent != null && node.parent.isVisit == false) {
			depth++;
			h = Math.max(h, 1 + getBFSMaxDepth(node.parent, depth));
			depth--;
		}
		if (maxHeight < depth) {
			maxHeight = depth;
			maxNode = node;
		}
		node.isVisit = false;
		return h;
	}
}