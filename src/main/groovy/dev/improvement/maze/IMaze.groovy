package dev.improvement.maze

interface IMaze {
	IPlayer start()
	void display(PrintStream out)
	void display(PrintStream out, IPlayer player)
}
