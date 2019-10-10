package dev.improvement

import spock.lang.*

/**
* https://leetcode.com/problems/as-far-from-land-as-possible/
*/
class AsFarFromLandAsPossible extends Specification {
    @Unroll
    def 'test 1'(){
        when:
            def arr = 
                [
                    [1,0,1],
                    [0,0,0],
                    [1,0,1]
                ]
        then:
            maxDistance(arr) == 2
    }

    @Unroll
    def 'test 2'(){
        when:
            def arr = 
                [
                    [1,0,0],
                    [0,0,0],
                    [0,0,0]
                ]
        then:
            maxDistance(arr) == 4
    }

    @Unroll
    def 'test 3'(){
        when:
            def arr = 
                [
                    [1,0,0,0],
                    [0,0,1,0],
                    [0,0,0,0],
                    [0,0,0,1]
                ]
        then:
            maxDistance(arr) == 3
    }

    public static int maxDistance(List<Integer[]> grid) {
        def max = -1
        List<Point> land = new ArrayList<>()
        List<Point> water = new ArrayList<>()
        for(int i = 0; i < grid.size(); i++){
            Integer[] row = grid.get(i)
            for(int j = 0; j < row.size(); j++){
                int val = row[j]
                Point p = new Point(i, j)
                if(val == 1)
                    land.add(p)
                else
                    water.add(p)
            }
        }

        for(Point w : water){
            List<Integer> listOfDistances = new ArrayList<>()
            for(Point l : land){
                listOfDistances.add(w.getDistance(l))
            }
            int minDistance = Collections.min(listOfDistances)
            max = Math.max(max, minDistance)
        }

        return max
    }

    public static class Point {
        public int x
        public int y

        public Point(int x, int y){
            this.x = x
            this.y = y
        }

        public int getDistance(Point p){
            return Math.abs(x - p.x) + Math.abs(y - p.y)
        }
    }
}