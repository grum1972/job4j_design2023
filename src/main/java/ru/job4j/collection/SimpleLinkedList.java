package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value, null);

        if (head == null) {
            head = node;
        } else {
            Node<E> pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = node;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> pointer = head;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private Node<E> pointer = head;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer != null;
            }

            @Override
            public E next() {
                E elem;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                elem = pointer.item;
                pointer = pointer.next;
                return elem;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
