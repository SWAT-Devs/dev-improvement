package dev.improvement

import org.junit.*
import groovy.transform.*

@CompileStatic
class EtchASketchTest {
	EtchASketch eas = new EtchASketch()

	@Test
	void test_print() {
		eas.screen = [[true, false], [false, true]]
		eas.print()
		def ls = System.lineSeparator()
		def sb = new StringBuilder()
		eas.print(sb)
		Assert.assertEquals("2 x 2", "X $ls X".toString(), sb.toString())
	}

	@Test
	void test_run() {
		assertRunEquals(">", [[true, true]])
		assertRunEquals("v", [[true],
							  [true]])
		assertRunEquals(">v", [[true,  true],
							   [false, true]])
		assertRunEquals(">v<", [[true, true],
								[true, true]])
		assertRunEquals(">>vv<<^^", [[true, true,  true],
									 [true, false, true],
									 [true, true,  true]])
	}

	private void assertRunEquals(String instructions, List<List<Boolean>> expected){
		eas.run(instructions)
		Assert.assertEquals("Run '$instructions'", expected, eas.screen)
	}
}

