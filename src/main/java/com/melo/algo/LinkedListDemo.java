package com.melo.algo;

/**
 * Created by 76009 on 2019/10/24.
 */
public class LinkedListDemo {

    public Node reverse(Node list) {
        Node headNode = null;

        Node currentNode = list;
        Node previousNode = null;

        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = nextNode;
            }

            currentNode.next = previousNode;
            previousNode = currentNode;

            currentNode = nextNode;
        }

        return headNode;
    }

    static class Node {

        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}
