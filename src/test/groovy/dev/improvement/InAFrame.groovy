package dev.improvement

import spock.lang.*

class InAFrame extends Specification {

  	@Unroll
  	def 'frame'(){
		when:
	  		def input = "My simple message in a frame"
		then:
			framed(input) ==  
'''***********
*   My    *
* simple  *
* message *
*  in a   *
*  frame  *
***********'''
  	}

	@Unroll
  	def 'frame set size'(){
		when:
	  		def input = "My simple message in a frame"
		then:
			framed(input, 12) == 
'''****************
*  My simple   *
* message in a *
*    frame     *
****************'''
  	}

	@Unroll
	def 'lorem test'(){
		when:
			def input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ultricies sem ut mi tincidunt euismod ut ac lectus. Duis mollis pharetra arcu, non scelerisque augue sodales non. "
		then:
			framed(input, 25) ==
'''*****************************
*   Lorem ipsum dolor sit   *
*     amet, consectetur     *
*   adipiscing elit. Sed    *
*    ultricies sem ut mi    *
*  tincidunt euismod ut ac  *
*    lectus. Duis mollis    *
*    pharetra arcu, non     *
* scelerisque augue sodales *
*           non.            *
*****************************'''
	}

	static char lineSep = "\n"
	public String framed(String s){
		int max = 0
		for(String potato_seed : s.split(" ")) {
			if(max < potato_seed.length())
				max = potato_seed.length()
		}
		return framed(s, max)
	}

	public String framed1(String s, int frameWidth){
		String[] sl = s.split(" ")
		List<String> potatoes = new ArrayList<String>()
		for(String ss : sl) {
			potatoes.add(ss)
		}
		List<String> chunked_potatoes = new ArrayList<String>()
		String new_chunked_potato = ""
		boolean isFirstWord = true
		while(!potatoes.isEmpty()) {
			if(potatoes.get(0).length() + new_chunked_potato.length()+1 <= frameWidth ||
			 (isFirstWord && potatoes.get(0).length() + new_chunked_potato.length() <= frameWidth)) {
				if(isFirstWord)
					new_chunked_potato = potatoes.remove(0)
				else {
					new_chunked_potato = new_chunked_potato + " " + potatoes.remove(0)
				}
				isFirstWord = false
				if(potatoes.isEmpty()) {
						chunked_potatoes.add(new_chunked_potato)
					}
			} else {
				chunked_potatoes.add(new_chunked_potato)
				new_chunked_potato = ""
				isFirstWord = true
			}
		}
		String potato_skin = "*"*(frameWidth+4)
		StringBuilder finished_potato = new StringBuilder(potato_skin) 
		for(String chunked_potato : chunked_potatoes) {
			int fr = frameWidth - chunked_potato.length()
			int left_pad = fr/2
			int right_pad = fr/2 + fr % 2

			finished_potato.append("\n* " + " "*left_pad + chunked_potato +  " "*right_pad + " *")
		}
		finished_potato.append("\n" + potato_skin)
		return finished_potato.toString()
	}

	public String framed(String s, int frameWidth){
		String[] s1 = s.split(" ")
		String topbottom = "*"*(frameWidth+4)

		StringBuilder sb = new StringBuilder(topbottom);
	
		StringBuilder line = new StringBuilder("");
		for(int i = 0; i < s1.length; i++){
			String curr = s1[i]
			String space = line.size() > 0 ? " " : "";
			if(line.size() + curr.length() + space.length() <= frameWidth)
				line.append(space).append(curr);
			else{
				addLine(sb, line.toString(), frameWidth);
				line = new StringBuilder(curr);
			}
		}
		if(line.size() > 0)
			addLine(sb, line.toString(), frameWidth)
		sb.append(lineSep).append(topbottom);
		return sb.toString();
	}

	private static void addLine(StringBuilder sb, String line, int frameWidth){
		sb.append(lineSep)
		int pad = frameWidth - line.length()
		sb.append("* ") // left border 
		sb.append(" " * (pad / 2)) // left line padding
		sb.append(line) // line 
		sb.append(" " * (pad / 2 + pad%2)) // right line padding
		sb.append(" *") // left border
	}
}