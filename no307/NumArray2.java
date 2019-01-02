package com.daniel.leetcode.solutions.no307;

class NumArray2 {

    private interface Merger<E> {
        E merge(E a, E b);
    }

    private class SegmentTree<E> {
    /*
        Private members:
        1. low level array that represents all elements.
        2. segment tree.
     */

        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        /*
            Constructor, using an array to initialize
         */
        public SegmentTree(E[] arr, Merger<E> merger) {
            this.merger = merger;
            data = (E[])new Object[arr.length];
            for(int i = 0; i < arr.length; i ++)
                data[i] = arr[i];
            tree = (E[])new Object[arr.length * 4];
            // Build tree.
            buildSegmentTree(0, 0, data.length - 1);
        }

        private void buildSegmentTree(int treeIndex, int l, int r) {
            if(l == r) {
                tree[treeIndex] = data[l];
                return;
            }

            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);

            int mid = l + (r - l) / 2;
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }

        // query value of secion [queryL, queryR] .
        public E query(int queryL, int queryR) {
            if(queryL < 0 || queryL >= data.length ||
                    queryR < 0 || queryR >= data.length || queryL > queryR)
                throw new IllegalArgumentException("Index is illegal.");
            return query(0, 0, data.length - 1, queryL, queryR);
        }
        // In the range [l ... r] of segment tree, query [queryL, queryR].
        // First Three params stands for node in segment tree.
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            if(l == queryL && r == queryR)
                return tree[treeIndex];
            int mid = l + ( r - l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if(queryL >= mid + 1)
                return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            else if(queryR <= mid)
                return query(leftTreeIndex, l, mid, queryL, queryR);
            E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
            E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1 , queryR);
            return merger.merge(leftResult, rightResult);
        }

        public void set(int index, E e) {
            if(index < 0 || index >= data.length)
                throw new IllegalArgumentException("Index is illegal");
            data[index] = e;
            set(0, 0, data.length - 1, index, e);
        }

        private void set(int treeIndex, int l, int r, int index, E e) {
            if(l == r){
                tree[treeIndex] = e;
                return;
            }

            int mid = l + (r -l) / 2;
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            if(index >= mid + 1)
                set(rightTreeIndex, mid + 1, r, index, e);
            else
                set(leftTreeIndex, l, mid, index, e);
            tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        }


    /*
        get size of array
     */

        public int getSize() {
            return data.length;
        }

    /*
        get element by index.
     */

        public E get(int index){
            if(index < 0 || index >= data.length)
                throw new IllegalArgumentException("Invalid index");
            return data[index];
        }

        /*
            Helpers: get left child's index.
         */
        private int leftChild(int index) {
            return 2 * index + 1;
        }

    /*
        Helpers: get right child's index.
     */

        private int rightChild(int index) {
            return 2 * index + 2;
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');
            for (int i = 0; i < tree.length; i ++) {
                if(tree[i] != null)
                    res.append(tree[i]);
                else
                    res.append("null");

                res.append("->");
            }
            return res.toString();
        }
    }


    private SegmentTree<Integer> segmentTree;

    public NumArray2(int[] nums) {
        if(nums.length != 0) {
            Integer[] data = new Integer[nums.length];
            for(int i = 0; i < nums.length; i ++)
                data[i] = nums[i];
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }

    }

    public void update(int i, int val) {
        if(segmentTree == null)
            throw new IllegalArgumentException("Error");
        segmentTree.set(i, val);

    }

    public int sumRange(int i, int j) {

    }
}

