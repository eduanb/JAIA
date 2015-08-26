package pso;

public class Velocity
{
	// store the Velocity in an array to accommodate multi-dimensional problem space
	private double[] vel;

	public Velocity(double[] vel)
	{
		super();
		this.vel = vel;
	}

	public double[] getPos()
	{
		return vel;
	}

	public void setPos(double[] vel)
	{
		this.vel = vel;
	}

}
