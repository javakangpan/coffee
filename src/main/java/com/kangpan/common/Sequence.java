package com.kangpan.common;

public class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence (int size) {
        items = new Object[size];
    }
    public void add (Object obj) {
        if (next < items.length) {
            items[next++] = obj;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }

    private class SequenceSelector implements Selector {
        private int i = 0;
        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }

        @Override
        public Object current() {
            return items[i];
        }
    }
}
