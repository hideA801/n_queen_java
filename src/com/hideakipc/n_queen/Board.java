package com.hideakipc.n_queen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Board {
    private static final String MARK = "●";
    private static final String SPACE = "○";
    private int max;
    private List<Integer> result = new ArrayList<Integer>();

    Board(int firstY, int size) {
        this.max = size;
        this.result.add(firstY);
    }

    Board(List<Integer> list, int size) {
        this.max = size;
        this.result = list;
    }

    void putQueen(int y) {
        result.add(y);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x : result) {
            setSpace(sb, x);
            sb.append(MARK);
            int restSpace = max - x - 1;
            setSpace(sb, restSpace);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void setSpace(StringBuilder sb, int count) {
        int i = 0;
        while (i < count) {
            sb.append(SPACE);
            i++;
        }
    }

    Set<Integer> getNotPutY(int tryX) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x = 0; x < this.result.size(); x++) {
            Integer y = this.result.get(x);
            set.add(y);
            int distance = tryX - x;
            int slantY1 = y + distance;
            int slantY2 = y - distance;
            set.add(slantY1);
            set.add(slantY2);
        }
        return set;
    }

    int size() {
        return this.max;
    }

    int putQueenSize() {
        return this.result.size();
    }

    Board cooy() {
        List<Integer> list = new ArrayList<>(this.result);
        return new Board(list, this.max);
    }

}
