package com.backbase.homework.kalah.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Board {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private int[][] pits = new int[6][2];
    private int eastKalah;
    private int westKalah;

    public Board() {
        //initialize 6 stones first
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 2; y++) {
                pits[x][y] = 6;
            }
        }
    }

    public MoveResponse move(Move move)  {

        int x = move.getStartPitIndexX(), y = move.getStartPitIndexY();

        int stoneCount = pits[move.getStartPitIndexX()][move.getStartPitIndexY()];

        resetPitStoneCount(move.getStartPitIndexX(), move.getStartPitIndexY());

        for (int i=0; i<stoneCount-1; i++) {
            if(x == 5 && y == 1 ) { // if kalah turn is on the right border
                eastKalah = move.getPlayerOrder() == 2?eastKalah +1:eastKalah; // add one stone to kalah
                y = 0; //reSet cursor on y line
                x = 6;
            } else {
                if(x == 0 && y == 0) { // if kalah turn is on the left border
                    westKalah = move.getPlayerOrder() == 1? westKalah + 1: westKalah; // add one stone to kalah
                    y = 1; //reset cursor on y line
                    x = -1;
                } else {
                    x = y == 1? x+1: x-1; // if cursor is on upper side, decrease, else increase
                    pits[x][y] ++;
                }
            }
        }

        //Last round play
        MoveResponse moveResponse;

        if(x == 5 && y == 1 && move.getPlayerOrder() == 2) { // if kalah turn is on the right border
            eastKalah ++; // add one stone to kalah
            moveResponse = new MoveResponse(0,  0, true, false);
        } else {
            if(x == 0 && y == 0 && move.getPlayerOrder() == 1) { // if kalah turn is on the left border
                westKalah ++; // add one stone to kalah
                moveResponse = new MoveResponse(0,  0, false, true);
            } else {
                x = y == 1? x+1: x-1; // if cursor is on upper side, decrease, else increase
                pits[x][y] ++;
                moveResponse = new MoveResponse(x,  y, false, false);
            }
        }

        print();
        System.out.println("------------------------------------ eastkalah:"+this.eastKalah + ", westkalah:" + westKalah);

        return moveResponse;
    }

    public int getPitStoneCount(int x, int y) {
        return this.pits[x][y];
    }

    public void resetPitStoneCount(int x, int y) {
        this.pits[x][y] = 0;
    }

    public void print() {
        for (int t= 0; t < 2; t++) {

            for (int i = 0; i < 6; i++) {
                System.out.print(this.pits[i][t] + ",");
            }
            System.out.println("");
        }
    }

}
