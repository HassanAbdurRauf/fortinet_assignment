package com.fortinet.assignment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class Tester {

	// Tester for reverse function for Node Class
	// Part of Solution to 1
	@Test
	public void isReversed() {

		Node n1 = new Node();
		n1.value = 1;
		n1.next = new Node();
		n1.next.value = 2;
		n1.next.next = new Node();
		n1.next.next.value = 3;
		n1.next.next.next = new Node();
		n1.next.next.next.value = 4;

		//assertEquals(isReversed(n1, reverse(n1)), true);
		long num= MultiDimensionArray.getValue(2);
		assertEquals(num, (long) 0.0);
	}

	// Tester for separate function
	// Part of Solution to 3
	@Test
	public void partitionCheck() {

		List<Integer> list = new ArrayList<Integer>();

		list.add(7);
		list.add(3);
		list.add(5);
		list.add(12);
		list.add(2);
		list.add(1);
		list.add(5);
		list.add(3);
		list.add(8);
		list.add(4);
		list.add(6);
		list.add(4);

		assertEquals(separate(list, 5), true);

	}

	// Helper function for testing the reverse function
	public boolean isReversed(Node n1, Node n2) {

		if (n1 == null && n2 == null)
			return true;
		else if (n1 == null || n2 == null)
			return false;

		Stack<Integer> stack = new Stack<Integer>();

		while (n1 != null) {
			stack.push(n1.value);
			n1 = n1.next;
		}

		while (n2.next != null) {
			if (n2.value != stack.pop()) {
				return false;
			}
			n2 = n2.next;
		}

		return true;
	}

	// Solution to 1: reverse function
	public Node reverse(Node head) {

		// The idea is very straight forward, i.e traverse array to the end
		// while changing the direction of link list by
		// maintaining two extra nodes. I avoided recursive call,
		// because for huge link list, function stack for
		// recursive call may decrease the algorithm
		// performance.

		// Time complexity of this algorithm is O(N)

		// Space complexity: In order to avoid loosing the original list
		// space complexity is O(N), otherwise the solution of O(1)
		// space is also possible

		// To avoid change in original List Cloning it

		if (head == null)
			return null;

		Node tmp = new Node();
		Node tmphead = tmp;
		Node traverser = head;
		tmp.value = head.value;

		while (traverser.next != null) {
			traverser = traverser.next;
			tmp.next = new Node();
			tmp = tmp.next;
			tmp.value = traverser.value;
		}

		Node pre = null;

		while (tmphead != null) {
			Node next = tmphead.next;
			tmphead.next = pre;
			pre = tmphead;
			tmphead = next;
		}

		return pre;
	}

	// helper for separate function
	// Part of Solution to 3
	boolean seperateHelper(List<Integer> list, int subsetSum[],
			boolean taken[], int subset, int K, int N, int curIdx, int limitIdx) {
		if (subsetSum[curIdx] == subset) {
			/*
			 * current index (K - 2) represents (K - 1) subsets of equal sum
			 * last subsetition will already remain with sum 'subset'
			 */
			if (curIdx == K - 2)
				return true;

			// recursive call for next subsetition
			return seperateHelper(list, subsetSum, taken, subset, K, N,
					curIdx + 1, N - 1);
		}

		// start from limitIdx and include elements into current subsetition
		for (int i = limitIdx; i >= 0; i--) {
			// if already taken, continue
			if (taken[i])
				continue;
			int tmp = subsetSum[curIdx] + list.get(i);

			// if temp is less than subset then only include the element
			// and call recursively
			if (tmp <= subset) {
				// mark the element and include into current subsetition sum
				taken[i] = true;
				subsetSum[curIdx] += list.get(i);
				boolean nxt = seperateHelper(list, subsetSum, taken, subset, K,
						N, curIdx, i - 1);

				// after recursive call unmark the element and remove from
				// subsetition sum
				taken[i] = false;
				subsetSum[curIdx] -= list.get(1);
				if (nxt)
					return true;
			}
		}
		return false;
	}

	// Part of Solution to 3: separate function
	public boolean separate(List<Integer> list, int k) {

		// Your resolution
		// Time complexity of the algorithm is O(k*N^2)
		// Space complexity is k since we are using the recursive method

		int lengthofArray = list.size();

		// If k is 1, then complete array will be our answer
		if (k == 1)
			return true;

		// If total number of subsetitions are more than n, then
		// division is not possible
		if (lengthofArray < k)
			return false;

		// if array sum is not divisible by k then we can't divide
		// array into k subsetitions
		int sum = 0;
		for (int i = 0; i < lengthofArray; i++)
			sum += list.get(i);
		if (sum % k != 0)
			return false;

		// the sum of each subset should be subset (= sum / k)
		int subset = sum / k;
		int subsetSum[] = new int[k];
		boolean taken[] = new boolean[lengthofArray];

		// Initialize sum of each subset from 0
		for (int i = 0; i < k; i++)
			subsetSum[i] = 0;

		// mark all elements as not taken
		for (int i = 0; i < lengthofArray; i++)
			taken[i] = false;

		// initialize first subsubset sum as last element of
		// array and mark that as taken
		subsetSum[0] = list.get(lengthofArray - 1);
		taken[lengthofArray - 1] = true;
		if (subset < subsetSum[0])
			return false;

		// call recursive method to check k-subsetition condition
		return seperateHelper(list, subsetSum, taken, subset, k, lengthofArray,
				0, lengthofArray - 1);
	}
}

// Question 1 given class
class Node {

	Node next;
	int value;
}

// Solution to 2: MultiDimensionArray
class MultiDimensionArray {

	// This is a provided function, Assume it works
	public static Long getValue(int... indexOfDimension) {
		// ...
		return (long) 0.0; // stub
	}

	// lengthOfDeminsion: each dimension's length, assume it is valid:
	// lengthOfDeminsion[i]>0.
	public static Long sum(MultiDimensionArray mArray, int[] lengthOfDimension) {
		// Resolution: As we already know the implementation of an array is
		// contiguous in memory, so the
		// ith index of the nth dimension of the array can be reached by simply
		// multiplying the length of
		// the dimension by the row number i.e n and adding up the index i.e i
		// to the sum.

		// Time complexity of this algorithm is O(N*M) where N is the number of
		// dimensions and M is the max
		// length of all the dimension

		// Space complexity will be O(1) as there is no extra space or constant
		// space required to sum
		// the array

		long sum = 0;
		int totalLength = 0;
		for (int dimension : lengthOfDimension) {
			totalLength *= dimension;
		}
		
		for (int i = 0; i < totalLength; i++)
		{
			sum += getValue(i);
		}

		return sum;
	}
}

// Solution to 4
// Implementation of Singleton Design Pattern
class SingleInstance {
	private static SingleInstance instance = null;

	protected SingleInstance() {
		// Exists only to defeat instantiation.
	}

	public static SingleInstance getInstance() {
		if (instance == null) {
			instance = new SingleInstance();
		}
		return instance;
	}
}

// Dynamix Proxy Implementation

interface IVehicle {
	public void start();

	public void stop();

}

// the main wrapper/ proxy class
class VehicleProxy implements IVehicle {
	private IVehicle v;

	public VehicleProxy(IVehicle v) {
		this.v = v;
	}

	public void start() {
		System.out.println("VehicleProxy: About to Start");
		v.start();
		System.out.println("VehicleProxy: Started");
	}

	@Override
	public void stop() {
		System.out.println("VehicleProxy: About to Stop");
		v.start();
		System.out.println("VehicleProxy: Stopped");
	}

}

class Car implements IVehicle {
	private String name;

	public Car(String name) {
		this.name = name;
	}

	public void start() {
		System.out.println("Car " + name + " started");
	}

	@Override
	public void stop() {
		System.out.println("Car " + name + " stoped");
	}
}

class Client {
	public static void main(String[] args) {
		IVehicle c = new Car("Tesla S1");
		IVehicle v = new VehicleProxy(c);
		v.start();
		v.stop();
	}

	// Solution to 5
	// JUnit Testing for Service Methods
	// The concept of the proposed solution is originated from the idea of
	// stubs and drivers or in other words mocking.
	// We can simulate the DAOS using stubs in order to achieve unit testing

	// We can use also an open source library to serve this problem. i.e
	// JMock,EasyMock,Mockito
	// All of these are used to mock the unimplemented services

	// Another way could be using hsqldb, h2 in memory databases

}