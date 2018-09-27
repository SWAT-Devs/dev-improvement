package dev.improvement

class Util{
	static String prompt(String prompt){
		def cons = System.console()
		if(cons != null){
			return cons.readLine(prompt)
		}
		print prompt
		return new Scanner(System.in).nextLine()
	}

	static String prompt() {
		prompt(">> ")
	}
}
