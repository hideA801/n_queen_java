package com.hideakipc.n_queen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Board {
    private static final String LINE_BREAK = "\n";
    private static final String MARK = "●";
    private static final String SPACE = "○";
    private int boardSize;
    private List<Integer> putQueenY = new ArrayList<Integer>();

    Board(int firstY, int size) {
        this.boardSize = size;
        this.putQueenY.add(firstY);
    }

    Board(List<Integer> list, int size) {
        this.boardSize = size;
        this.putQueenY = list;
    }

    void putQueen(int y) {
        putQueenY.add(y);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x : putQueenY) {
            setSpace(sb, x);
            sb.append(MARK);
            int restSpace = this.boardSize - x - 1;
            setSpace(sb, restSpace);
            sb.append(LINE_BREAK);
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

    List<Integer> getPuttableY(int tryX) {
        Set<Integer> notPuttableYs = new HashSet<>();
        for (int x = 0; x < this.putQueenY.size(); x++) {
            Integer y = this.putQueenY.get(x);
            int distance = tryX - x;
            int slantY1 = y + distance;
            int slantY2 = y - distance;
            notPuttableYs.add(y);
            notPuttableYs.add(slantY1);
            notPuttableYs.add(slantY2);
        }
        List<Integer> puttableY = new ArrayList<>();
        for (int i = 0; i < this.boardSize; i++) {
            if (!notPuttableYs.contains(i)) {
                puttableY.add(i);
            }
        }
        return puttableY;
    }

    int size() {
        return this.boardSize;
    }

    Board cooy() {
        List<Integer> list = new ArrayList<>(this.putQueenY);
        return new Board(list, this.boardSize);
    }

    boolean isComplateBoard() {
        return this.putQueenY.size() == this.boardSize;
    }

}
