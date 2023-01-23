package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int inCapacity = 0;
    int outCapacity = 0;

    public T poll() {
        if (outCapacity == 0 && inCapacity == 0) {
            throw new NoSuchElementException();
        }
        if (outCapacity == 0) {
            while (inCapacity > 0) {
                out.push(in.pop());
                inCapacity--;
                outCapacity++;
            }
        }
        outCapacity--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCapacity++;
    }
}
