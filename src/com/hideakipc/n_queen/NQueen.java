package com.hideakipc.n_queen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NQueen {
    public static void main(String[] args) {
        new NQueen().exe(args);
    }

    private void exe(String[] args) {
        boolean isDispBoard = false;
        long start = System.currentTimeMillis();
        int size = parse(args);
        List<Board> results = CheckQueens(size);
        dumpResult(results, start, isDispBoard);
    }

    private int parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("引数が不正です。引数には数値を指定してください");
        }
        return Integer.parseInt(args[0]);
    }

    private void dumpResult(List<Board> results, long start, boolean isDispBoard) {
        if(isDispBoard){
            dispQueenPlacement(results);
        }

        System.out.println("total: " + results.size());
        long stop = System.currentTimeMillis();
        System.out.println("実行にかかった時間は " + (stop - start) + " ミリ秒です。");
    }

    private void dispQueenPlacement(List<Board> results) {
        for (Board borad : results) {
            System.out.println(borad);
            System.out.println();
        }
    }

    private List<Board> CheckQueens(int size) {
        List<Board> results = new ArrayList<>();
        for (int firstQueenY = 0; firstQueenY < size; firstQueenY++) {
            List<Board> borads = getResult(firstQueenY, size);
            if (!borads.isEmpty()) {
                results.addAll(borads);
            }
        }
        return results;
    }

    private List<Board> getResult(int firstY, int size) {
        Board board = new Board(firstY, size);
        return getComplateBorads(1, board);
    }

    private List<Board> getComplateBorads(int tryX, Board board) {
        List<Integer> puttableY = getPuttableY(tryX, board);
        int size = puttableY.size();
        List<Board> list = new ArrayList<>();
        if (size == 0 && board.size() == board.putQueenSize()) {
            list.add(board);
            return list;
        }
        for (Integer y : puttableY) {
            Board newBoard = board.cooy();
            newBoard.putQueen(y);
            List<Board> boards = getComplateBorads(tryX + 1, newBoard);
            if(!boards.isEmpty()){
              list.addAll(boards);
            }
        }
        return list;
    }

    //TODO ここ早くできるかも
    private List<Integer> getPuttableY(int tryX, Board board) {
        Set<Integer> notPuts = board.getNotPutY(tryX);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            if (!notPuts.contains(i)) {
                list.add(i);
            }
        }
        return list;
    }

}
