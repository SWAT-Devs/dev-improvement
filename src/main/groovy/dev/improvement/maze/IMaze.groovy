package dev.improvement.maze

interface IMaze {
	IPlayer start()
	void display(Appendable out)
	void display(Appendable out, IPlayer player)
}
