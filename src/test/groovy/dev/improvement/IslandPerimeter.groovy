package dev.improvement

import spock.lang.*

/**
* https://leetcode.com/problems/island-perimeter/
*/
class IslandPerimeter extends Specification {
    @Unroll
    def 'test 1'(){
        when:
            def arr = 
                [
                    [0,1,0,0],
                    [1,1,1,0],
                    [0,1,0,0],
                    [1,1,0,0]
                ]
        then:
            islandPerimeter(arr) == 16
    }

    @Unroll
    def 'test 2'(){
        when:
            def arr = 
                [
                    [1,0,0,0],
                    [1,1,1,1],
                    [0,1,0,1],
                    [0,1,0,1]
                ]
        then:
            islandPerimeter(arr) == 20
    }

    @Unroll
    def 'test 3'(){
        when:
            def arr = 
                [
                    [0,0,0],
                    [0,1,0],
                    [0,0,0]
                ]
        then:
            islandPerimeter(arr) == 4
    }

     @Unroll
    def 'test 4'(){
        when:
            def arr = 
                [
                    [0,0,0,0],
                    [0,1,1,1],
                    [0,1,1,1],
                    [0,1,1,1]
                ]
        then:
            islandPerimeter(arr) == 12
    }

    public static int islandPerimeter(List<Integer[]> island) {
        int parimeterCount = 0;
        for (int i = 0; i < island.size(); i++) {
            for (int j = 0; j < island[i].size(); j++) {
                if (island[i][j] == 1) {
                    //check north
                    if (i == 0  || island[i-1][j] == 0) {
                        parimeterCount++;
                    }
                    //check south
                    if (i == island.size() - 1 || island[i+1][j] == 0) {
                        parimeterCount++;
                    }
                    //check east
                    if (j == island[i].size() - 1 || island[i][j+1] == 0) {
                        parimeterCount++;
                    }
                    //check west
                    if (j == 0 || island[i][j-1] == 0) {
                        parimeterCount++;
                    }
                }
            }
            
        }

        return parimeterCount;
    }
}