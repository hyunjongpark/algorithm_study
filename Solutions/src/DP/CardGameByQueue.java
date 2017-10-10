package DP;
/*
 * https://www.acmicpc.net/problem/10835
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CardGame {
	static boolean debug = true;
	static int depth = 0;
	static int[] left;
	static int[] right;

	static int[][] dpt;

	static int maxValue;

	static public class LR {
		int l;
		int r;

		LR(int a, int b) {
			l = a;
			r = b;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("cardgame"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		depth = Integer.parseInt(br.readLine());

		left = new int[depth];
		right = new int[depth];
		dpt = new int[depth + 1][depth + 1];
		maxValue = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < depth; i++) {
			left[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < depth; i++) {
			right[i] = Integer.parseInt(st.nextToken());
		}

		Queue<LR> queue = new LinkedList<>();
		queue.offer(new LR(0, 0));
		updateTable(queue);

		System.out.print(maxValue);
	}

	public static void updateTable(Queue<LR> queue) {

		while (!queue.isEmpty()) {
			LR lr = queue.poll();

			int leftIndex = lr.l;
			int rightIndex = lr.r;

			int v = dpt[leftIndex][rightIndex];

			// case1 LR
			if (leftIndex + 1 < depth && rightIndex + 1 < depth) {
				LR addlr = new LR(leftIndex + 1, rightIndex + 1);
				if (!isContain(queue, addlr)) {
					queue.offer(addlr);
				}
				if (dpt[leftIndex + 1][rightIndex + 1] < v) {
					dpt[leftIndex + 1][rightIndex + 1] = v;
					printDBTable(queue);
				}
			}

			// case1 L
			if (leftIndex + 1 < depth && rightIndex < depth) {
				LR addlr = new LR(leftIndex + 1, rightIndex);
				if (!isContain(queue, addlr)) {
					queue.offer(addlr);
				}

				if (dpt[leftIndex + 1][rightIndex] < v) {
					dpt[leftIndex + 1][rightIndex] = v;
					printDBTable(queue);
				}
			}

			// case1 R
			if (leftIndex < depth && rightIndex < depth) {
				LR addlr = new LR(leftIndex, rightIndex + 1);
				if (!isContain(queue, addlr)) {
					queue.offer(addlr);
				}

				if (left[leftIndex] > right[rightIndex] && dpt[leftIndex][rightIndex + 1] < v + right[rightIndex]) {
					dpt[leftIndex][rightIndex + 1] = v + right[rightIndex];
				}
				
				if (maxValue < dpt[leftIndex][rightIndex + 1]) {
					maxValue = dpt[leftIndex][rightIndex + 1];
				}
				printDBTable(queue);
			}

			printDBTable(queue);
		}

	}

	public static boolean isContain(Queue<LR> queue, LR lr) {
		for (LR anObject : queue) {
			if (anObject.l == lr.l && anObject.r == lr.r) {
				return true;
			}
		}
		return false;
	}

	public static void printDBTable(Queue<LR> queue) {
		if (!debug) {
			return;
		}
		for (LR anObject : queue) {
			System.out.print(anObject.l + ":" + anObject.r + ", ");
		}
		System.out.println();

		for (int i = 0; i < depth; i++) {
			for (int j = 1; j <= depth; j++) {
				System.out.print(dpt[i][j] + "	");
			}
			System.out.println();
		}
		System.out.println();
	}

}
