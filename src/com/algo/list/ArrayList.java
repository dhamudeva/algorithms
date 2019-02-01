package com.algo.list;

public class ArrayList {

  private int size;
  private Node headOfList;
  private Node tailOfList;
  
  public ArrayList() {
    size = 0;
    headOfList = null;
    tailOfList = null;
  }

  public void add(int dataInput) {
    addAtEnd(dataInput);
  }
  
  public int size() {
    return size;
  }
  
  private void addAtEnd(int dataInput) {
    
    Node newNode = new Node(dataInput, null);
    
    if (headOfList == null) {
      headOfList = newNode;
      tailOfList = newNode;
    } else {
      tailOfList.next = newNode;
      tailOfList = newNode;
    }
    size++;
  }

  /**
   * Reverse the given list
   * 
   * Take the given list and build the reversed list from the end. 
   * That means the reversed list's head will start with null. 
   * 
   * Detach a node from the list and make its next to point to null
   * This becomes the first node in the reversed list.
   *
   * While detaching a node from old list, preserve the next node so that
   * we can parse the old list till the end.
   * 
   */
  
  public void reverse() {
    
    Node nodeToBeDetached = headOfList;
    Node headOfReversedList = null;
    
    while (nodeToBeDetached != null) {
      Node nextNodeToBeDetached = nodeToBeDetached.next;
      nodeToBeDetached.next = headOfReversedList;
      headOfReversedList = nodeToBeDetached;
      nodeToBeDetached = nextNodeToBeDetached;
    } 
    // Rearrange the head and tail of the new list
    tailOfList = headOfList;
    headOfList = headOfReversedList;
  }
  
  public void print() {
    Node nodeToPrint = headOfList;
    
    while (nodeToPrint != null) {
      System.out.print(nodeToPrint.data + "->");
      nodeToPrint =  nodeToPrint.next;
    }
    System.out.println("null");
  }
  

  /**
   *  Since I am doing it in java, I cannot really compare the pointer values
   *  so going with data of the node. If data is equal, 
   *  I will assume the node is equal.
   *  
   * @return intersection data
   */
  public int findIntersection(ArrayList list) {
    
    // Reverse the list
    this.reverse();
    list.reverse();
    
    Node node1 = headOfList;
    Node node2 = list.headOfList;
    Node intersectingNode = null;
    
    while (node1 != null && node2 != null
        && node1.data == node2.data) {
      intersectingNode = node1;
      node1 = node1.next;
      node2 = node2.next;
    }
    
    return intersectingNode != null ? intersectingNode.data : -1;
  }
  
  
  private class Node {
    private int data;
    private Node next;
    
    Node(int dataInput, Node nextInput) {
      data = dataInput;
      next = nextInput;
    }
  }
}
