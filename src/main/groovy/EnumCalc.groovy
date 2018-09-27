// Simple calculator
// Inputs: Number or an operation
// If a number add to stack
// If an operation pop last two numbers off of stack as operands and push result to stack
interface Operator {
	double apply(double op1, double op2);
}

enum BasicMath implements Operator {
	ADD('+'){ double apply(double op1, double op2){ return op1 + op2}},
		SUB('-'){ double apply(double op1, double op2){ return op1 - op2}},
		MULT('*'){ double apply(double op1, double op2){ return op1 * op2}},
		DIV('/'){ double apply(double op1, double op2){ return op2 / op1}};
	final String symbol
	BasicMath(String symbol){
		this.symbol = symbol
	}
	abstract double apply(double op1, double op2)
}

class Exponent implements Operator {
	double apply(double op1, double op2){
		op2 ** op1
	}
}

class OpCommand implements Command{
	Operator op;
	LinkedList<Double> stack;

	OpCommand(Operator op, LinkedList<Double> stack){
		this.op = op;
		this.stack = stack;
	}
	
	void call(){
		def res = op.apply(stack.removeLast(), stack.removeLast());
		stack << res
		println res
	}
}

def stack = new LinkedList<Double>()
def cons = System.console()

interface Command {
	void call()
}

boolean end = false

def commands = new TreeMap<String, Command>(String.CASE_INSENSITIVE_ORDER)
commands["exit"] = {end = true}
commands["list"] = {stack.each {println it}}
commands["**"] = new OpCommand(new Exponent(), stack)
commands["exp"] = commands["**"]
commands['%'] = new OpCommand({o1, o2 -> o2%o1}, stack)

BasicMath.values().each {
	commands[it.name().toLowerCase()] = new OpCommand(it, stack)
	commands[it.symbol] = new OpCommand(it, stack)
}


while(!end) {
	def line = dev.improvement.Util.prompt()
	if(line == null || line.isEmpty()){
		continue
	}
	line = line.trim().toLowerCase()

	if(commands.containsKey(line)){
		commands[line].call()
	}
	else{
		def n = Double.parseDouble(line)
		stack << n
	}
}

