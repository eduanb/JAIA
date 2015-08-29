package algorithms.Optimisation.Solution;

import algorithms.Optimisation.OptimisationProblem.OptimisationProblem;

import java.util.Random;

public class Solution
{
	protected Double[] variables;
	protected Random random;
	protected double fitness = -1;
	protected boolean isFitenssSet = false;

	public Double[] getVariables() {
		return variables;
	}

	public void setVariable(int pos, Double value)
	{
		variables[pos] = value;
	}

	public int getNumberOfVariables()
	{
		return variables.length;
	}

	public Solution(Double[] variables)
	{
		this.variables = variables;
		random = new Random();
	}

	public Solution(int numberOfVariables)
	{
		this.variables = new Double[numberOfVariables];
		random = new Random();
	}

	@Override
	public String toString()
	{
		if (variables == null || variables.length == 0)
			return "{}";
		StringBuilder result = new StringBuilder().append("Fitness:").append(fitness).append(" {");
		for (int i = 0; i < variables.length; i++)
		{
			result.append(variables[i]);
			if(i < variables.length - 1) result.append(", ");
		}
		result.append("}");
		return result.toString();
	}

	public void swop(Solution other, int point) throws SolutionTypeException
	{
		for (int i = 0; i < point; i++)
		{
			Double temp = this.variables[i];
			this.variables[i] = other.variables[i];
			other.variables[i] = temp;
		}

	}

	public double getFitness() throws SolutionException
	{
		if (variables.length == 0)
			throw new SolutionEmptyException();
		if (!isFitenssSet)
			throw  new SolutionFitnessNotSetException();
		return fitness;
	}

	public double calculateFitness(OptimisationProblem ff)
	{
		this.fitness = ff.evaluate(this);
		this.isFitenssSet = true;
		return this.fitness;
	}

	public Double getVariable(int position)
	{
		return variables[position];
	}
}
