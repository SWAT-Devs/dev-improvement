package dev.improvement

class Main {
	private static Map<String, Class> apps = [
		"calc" : EnumCalc
	]
	public static void main(String[] args) {
		println "Please enter:"
		apps.each { println "\t$it.key : $it.value" }
		def name = Util.prompt()
		def clazz = apps[name]
		if(clazz == null){
			System.err.println "$name not found"
			return;
		}
		def app = clazz.newInstance()
		if(app instanceof Script){
			app.run();
		}
		else {
			System.err.println "$app can not be handled"
		}
	}
}
