package algorithms.Optimisation.Solution;

public class Particle extends Solution
{
    protected double[] velocity;
    protected double pbest;
    protected double[] bestPosition;

    public Particle(double[] variables, double[] velocity)
    {
        super(variables);
        this.variables = variables;
    }

    public Particle(int numberOfVariables)
    {
        super(numberOfVariables);
        velocity = new double[numberOfVariables];
        bestPosition = new double[numberOfVariables];
    }

    public double[] getVelocity()
    {

        return velocity;
    }

    public void setVelocity(double[] velocity)
    {
        this.velocity = velocity;
    }

    public double getPbest()
    {
        return pbest;
    }

    public void setPbest(double pbest)
    {
        this.pbest = pbest;
    }

    public double[] getBestPosition()
    {
        return bestPosition;
    }

    public void setBestPosition(double[] bestPosition)
    {
        this.bestPosition = bestPosition;
    }

    public void setVelocity(int pos, Double velocity)
    {
        this.velocity[pos] = velocity;
    }

    @Override
    public String toString()
    {
        if (variables == null || variables.length == 0)
            return "{}";
        StringBuilder result = new StringBuilder().append("Fitness:").append(fitness).append(" {");
        for (int i = 0; i < variables.length; i++) {
            result.append("[");
            result.append(variables[i]);
            result.append(",");
            result.append(velocity[i]);
            result.append("]");
            if (i < variables.length - 1)
                result.append(", ");
        }
        result.append("}");
        return result.toString();
    }

}
