package dev.improvement

import spock.lang.*

/**
* https://coderbyte.com/editor/Closest%20Enemy%20II:Java
*/
class ClosestEnemy extends Specification {
    @Unroll
    def 'test 1'(){
        when:
            def arr = 
                [
                    [0,0,0],
                    [1,0,0],
                    [2,0,0]
                ]
        then:
            closestEnemy(arr) == 1
    }

    @Unroll
    def 'test 2'(){
        when:
            def arr = 
                [
                    [0,0,0,0],
                    [2,0,1,0],
                    [0,0,0,0],
                    [2,0,0,2]
                ]
        then:
            closestEnemy(arr) == 2
    }

    @Unroll
    def 'test 3'(){
        when:
            def arr = 
                [
                    [0,0,0,0],
                    [1,0,0,0],
                    [0,0,0,2],
                    [0,0,0,2]
                ]
        then:
            closestEnemy(arr) == 2
    }

    public static int closestEnemy(List<Integer[]> arr){
        Point iPoint;
        List<Point> targets = new ArrayList<>()
        for(int i = 0; i < arr.size(); i++){
            for(int j = 0; j < arr[i].size(); j++){
                int val = arr[i][j]
                if(val == 1)
                    iPoint = new Point(i, j)
                else if (val == 2)
                    targets.add(new Point(i, j))
            }
        }

        for(Point t : targets){
            int distance = t.getDistanceBetween(iPoint)
        }

        return -1
    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getDistanceBetween(Point other){
            int xDistance = Math.abs(x - other.x)
            int yDistance = Math.abs(y - other.y)
            return xDistance + yDistance
        }
    }
}