package dev.improvement

import spock.lang.*

class TreasureHunt extends Specification {

	@Unroll
	def 'test'(){
		when:
			def arr = [
				[34, 21, 32, 41, 25],
				[14, 42, 43, 14, 31],
				[54, 45, 52, 42, 23],
				[33, 15, 51, 31, 35],
				[21, 52, 33, 13, 23],
			]
		then:
			hunt(arr) == [34, 42, 15, 25, 31, 54, 13, 32, 45, 35, 23, 43, 51, 21, 14, 41, 33, 52]
	}

	public static List<Integer> hunt(List<Integer[]> input) {
		Stack<Integer> list = new Stack<Integer>()
		int start = input[0][0]

		while(list.isEmpty() || start != list.peek()) {
			list.push(start)
			int[]nextPositions = getColumns(start)
			start = input[nextPositions[0]][nextPositions[1]]
		}

		return new ArrayList<Integer>(list)
	}

	public static int[] getColumns(int place) {
		int[] f = new int[2]
		f[0] = (place / 10) - 1
		f[1] = (place % 10) - 1
		return f
	}
}