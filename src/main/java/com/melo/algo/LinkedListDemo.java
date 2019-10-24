package com.melo.algo;

/**
 * Created by 76009 on 2019/10/24.
 */
public class LinkedListDemo {

    public Node reverse(Node list) {
        //it is return result
        Node headNode = null;

        Node currentNode = list;
        Node previousNode = null;

        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                //last node is final first
                headNode = nextNode;
            }

            //store temp pointer value : address  先把当前节点的地址值存起来,当前的终归会作为下一个节点

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
