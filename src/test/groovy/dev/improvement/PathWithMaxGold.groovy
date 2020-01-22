package dev.improvement

import spock.lang.*

/**
* https://leetcode.com/problems/path-with-maximum-gold/
*/
class PathWithMaxGold extends Specification {
    @Unroll
    def 'test 1'(){
        when:
            def arr = 
                [
                    [0,6,0],
                    [5,8,7],
                    [0,9,0]
                ]
        then:
            getMaxGold(arr) == 24
    }

    @Unroll
    def 'test 2'(){
        when:
            def arr = 
                [
                    [1,0,7],
                    [2,0,6],
                    [3,4,5],
                    [0,3,0],
                    [9,0,20]
                ]
        then:
            getMaxGold(arr) == 28
    }

    @Unroll
    def 'test 3'(){
        when:
            def arr = 
                [
                    [8,1,0,18],
                    [0,1,2,0],
                    [0,0,0,0],
                    [0,3,3,1],
                    [5,4,0,0]
                ]
        then:
            getMaxGold(arr) == 18
    }

    public static int getMaxGold(List<Integer[]> arr){
        // step 1: solve
        int maxVal = 0;
        for(int r = 0; r < arr.size(); r++){
            Integer[] row = arr[r]
            for(int c = 0; c < row.size(); c++){
                if(arr[r][c] != 0)
                    maxVal = Math.max(maxVal, findBestPath(arr, r, c, new ArrayList<Cell>(), 0))
            }
        }
        return maxVal;
    }


    private static int findBestPath(List<Integer[]> arr, int r, int c, List<Cell> visited, int value){
        if(!canGo(arr, r, c, visited))
            return value;
            
        visited.add(new Cell(r, c))
        int val = value + arr[r][c]

        return Math.max(findBestPath(arr, r+ 1, c, visited, val), 
            Math.max(findBestPath(arr, r - 1, c, visited, val),
            Math.max(findBestPath(arr, r, c+1, visited, val), 
                findBestPath(arr, r, c-1, visited, val))));
    }

    private static boolean canGo(List<Integer[]> arr, int r, int c, List<Cell> visited){
        return r >= 0 && r < arr.size() && c >= 0 && c<arr[r].size() && 
            !visited.contains(new Cell(r, c)) && arr[r][c] != 0
    }

    static class Cell{
        int r;
        int c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        boolean equals(Object other){
            Cell o = (Cell) other
            return this.r == o.r && this.c == o.c;
        }

        @Override
        public String toString() {
            return r + ", " + c
        }
    }
}