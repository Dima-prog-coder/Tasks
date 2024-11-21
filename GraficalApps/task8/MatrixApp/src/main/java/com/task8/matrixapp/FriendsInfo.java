package com.task8.matrixapp;

import java.util.ArrayList;

class FriendsInfo {
    public int cnt;
    public ArrayList<int[]> lstIndexes;

    public FriendsInfo(int[] firstEl, int startCnt) {
        this.cnt = startCnt;
        this.lstIndexes = new ArrayList<>();
        this.lstIndexes.add(firstEl);
    }
}
