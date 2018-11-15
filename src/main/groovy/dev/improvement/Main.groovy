package dev.improvement

class Main {
	private static Map<String, Class> apps = [
				"calc" : EnumCalc,
				"squares" : dev.improvement.maze.Squares
	]
		public static void main(String[] args) {
				String name;
				if(args.length == 0){
						println "Please enter:"
						apps.each { println "\t$it.key : $it.value" }
						name = Util.prompt()
				}
				else {
						name = args[0]
				}
		def clazz = apps[name]
		if(clazz == null){
			System.err.println "$name not found"
			return;
		}
		def app = clazz.newInstance()
		if(app instanceof Script || app instanceof Runnable){
			app.run();
		}
		else if(app instanceof ICommand) {
			app.run(args.length > 1 ? args[(1..-1)] : [])
		}
		else {
			System.err.println "$app can not be handled"
		}
	}
}
