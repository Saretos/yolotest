package asteroids.ai.behavior;

public interface Behavior {
	public void enter();
	public void process(double delta);
	public void exit();
}
