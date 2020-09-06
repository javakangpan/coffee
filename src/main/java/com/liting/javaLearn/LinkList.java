package com.liting.javaLearn;

/**
 * 内部链式存储机制
 * @param <T>
 */
public class LinkList<T> {
    // 内部类可以访问其外部内参数
    private class Node {
        T item;
        Node next;
        Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
        Node() {
            this.item = null;
            this.next = null;
        }
        boolean isEnd() {
            return null == item && null == next;
        }
    }

    private Node head = new Node();
    public void push(T item) {
        head = new Node(item, head);
    }

    public T pop() {
        T result = head.item;
        if(!head.isEnd()) {
            head = head.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkList<String> linkList = new LinkList<>();
        linkList.push("hello");
        linkList.push("liTing");
        String s;
        while ((s = linkList.pop()) != null) {
            System.out.println(s);
        }
    }
}
