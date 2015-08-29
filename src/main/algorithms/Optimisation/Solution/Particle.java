package algorithms.Optimisation.Solution;

public class Particle extends Solution
{
	protected Double[] velocity;
	protected double pbest;
	protected Double[] bestPosition;

	public void setVelocity(Double[] velocity) {
		this.velocity = velocity;
	}

	public void setPbest(double pbest) {
		this.pbest = pbest;
	}

	public void setBestPosition(Double[] bestPosition) {
		this.bestPosition = bestPosition;
	}

	public Double[] getVelocity() {

		return velocity;
	}

	public double getPbest() {
		return pbest;
	}

	public Double[] getBestPosition() {
		return bestPosition;
	}

	public void setVelocity(int pos, Double velocity)
	{
		this.velocity[pos] = velocity;
	}

	public Particle(Double[] variables,Double velocities)
	{
		super(variables);
		this.variables = variables;
	}

	public Particle(int numberOfVariables)
	{
		super(numberOfVariables);
		velocity = new Double[numberOfVariables];
	}

	@Override
	public String toString()
	{
		if (variables == null || variables.length == 0)
			return "{}";
		StringBuilder result = new StringBuilder().append("Fitness:").append(fitness).append(" {");
		for (int i = 0; i < variables.length; i++)
		{
			result.append("[");
			result.append(variables[i]);
			result.append(",");
			result.append(velocity[i]);
			result.append("]");
			if(i < variables.length - 1) result.append(", ");
		}
		result.append("}");
		return result.toString();
	}

}
