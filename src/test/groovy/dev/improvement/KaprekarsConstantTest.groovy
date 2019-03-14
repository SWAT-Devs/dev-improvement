package dev.improvement

import spock.lang.*

// The magic of 6174
class KaprekarsConstantTest extends Specification {
    def '3087 -> 2 iterations'(){
        when: def input = 3087
        then: KaprekarsConstant(input) == 2 && KaprekarsConstant2(input) == 2
    }

    def '2111 -> 5 iterations'(){
        when: def input = 2111
        then: KaprekarsConstant(input) == 5 && KaprekarsConstant2(input) == 5
    }

    def '9831 -> 7 iterations'(){
        when: def input = 9831
        then: KaprekarsConstant(input) == 7 && KaprekarsConstant2(input) == 7
    }


    def int KaprekarsConstant(int num){
       return calculate(num);
    }

    def int calculate(int num){
        if(num == 6174)
            return 0;
        String x = String.valueOf(num);
        if(x.length() == 1) x = x + "000";
        else if(x.length() == 2) x = x + "00";
        else if(x.length() == 3) x = x + "0";
        char[] chars = x.toCharArray();
        Arrays.sort(chars);
        String small = new String(chars);
        String large = small.reverse();
        int s = Integer.parseInt(small);
        int l = Integer.parseInt(large);
        int result = l - s;
        return calculate(result) + 1;
    }

    // solution without converting to String
    public static int KaprekarsConstant2(int num) {
		if (num == 6174)
			return 0;

		List<Integer> digits = getDigits(num);
		while (digits.size() < 4) {
			digits.add(0);
		}

		if (digits.size() > 4 || new HashSet<Integer>(digits).size() == 1)
			throw new IllegalArgumentException("Must be 4 digits or less and contain 2 distinct numbers");
		
		Collections.sort(digits);
		int smallNum = makeNumber(digits);
		Collections.sort(digits, Collections.reverseOrder());
		int largeNum = makeNumber(digits);

		int newNum = largeNum - smallNum;
		return 1 + KaprekarsConstant2(newNum);
	}

	private static int makeNumber(List<Integer> digits) {
		return (digits[0] * 1000) + (digits[1] * 100) + (digits[2] * 10) + digits[3]
	}

	private static List<Integer> getDigits(int num) {
		List<Integer> digits = new ArrayList<>();

		while (num > 0) {
			digits.add(num % 10);
			num = num / 10;
		}
		return digits;
	}
}