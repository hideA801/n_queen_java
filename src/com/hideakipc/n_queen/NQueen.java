package com.hideakipc.n_queen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueen {
    private static final int ARG_IS_DISP_BOARDS = 1;
    private static final int ARG_QUEEN_SIZE = 0;

    class Conf {
        boolean isDispBoard = false;
        int boardSize = 0;

        Conf(int size, boolean isDisp) {
            this.boardSize = size;
            this.isDispBoard = isDisp;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new NQueen().exe(args);
        long stop = System.currentTimeMillis();
        System.out.println("実行にかかった時間は " + (stop - start) + " ミリ秒です。");
    }

    private void exe(String[] args) {
        Conf conf = parse(args);
        Set<Board> results = getNQueensBoards(conf.boardSize);
        dumpResult(results, conf.isDispBoard);
    }

    private Conf parse(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("引数が不正です");
        } else if (args.length < 2) {
            return new Conf(Integer.parseInt(args[ARG_QUEEN_SIZE]), false);
        }
        return new Conf(Integer.parseInt(args[ARG_QUEEN_SIZE]), Boolean.valueOf(args[ARG_IS_DISP_BOARDS]));
    }

    private void dumpResult(Set<Board> results, boolean isDispBoard) {
        if (isDispBoard) {
            dispQueenPlacement(results);
        }
        System.out.println("total: " + results.size());
    }

    private void dispQueenPlacement(Set<Board> results) {
        for (Board borad : results) {
            System.out.println(borad);
            System.out.println();
        }
    }

    private Set<Board> getNQueensBoards(int size) {
        Set<Board> results = new HashSet<>();
        for (int firstQueenY = 0; firstQueenY < size; firstQueenY++) {
            Board board = new Board(firstQueenY, size);
            Set<Board> borads = getComplateBoards(1, board);
            if (!borads.isEmpty()) {
                results.addAll(borads);
            }
        }
        return results;
    }

    private Set<Board> getComplateBoards(int tryX, Board board) {
        Set<Board> complateBoards = new HashSet<>();
        if (board.isComplateBoard()) {
            complateBoards.add(board);
            return complateBoards;
        }
        List<Integer> puttableY = board.getPuttableY(tryX);
        for (Integer y : puttableY) {
            Board newBoard = board.cooy();
            newBoard.putQueen(y);
            Set<Board> boards = getComplateBoards(tryX + 1, newBoard);
            if (!boards.isEmpty()) {
                complateBoards.addAll(boards);
            }
        }
        return complateBoards;
    }

}
