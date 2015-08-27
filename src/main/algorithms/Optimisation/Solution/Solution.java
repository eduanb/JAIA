package algorithms.Optimisation.Solution;

import algorithms.Optimisation.FitnessFunction;

import java.lang.reflect.Array;
import java.util.Random;

public class Solution<T>
{
	protected T[] variables;
	protected Random random;
	protected FitnessFunction fitnessFunction;
	protected double fitness = -1;

	public int getNumberOfVariables()
	{
		return variables.length;
	}

	public Solution(T[] variables, FitnessFunction fitnessFunction)
	{
		this.variables = variables;
		this.fitnessFunction = fitnessFunction;
		random = new Random();
		fitness = fitnessFunction.evaluate(this);
	}

	public Solution(Class<T> clazz, FitnessFunction fitnessFunction, int numberOfVariables)
	{
		this.variables = (T[]) Array.newInstance(clazz.getComponentType(), numberOfVariables);
		this.fitnessFunction = fitnessFunction;
		random = new Random();
		fitness = fitnessFunction.evaluate(this);
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

	public void swop(Solution<T> other, int point) throws SolutionTypeException
	{
		for (int i = 0; i < point; i++)
		{
			T temp = this.variables[i];
			this.variables[i] = other.variables[i];
			other.variables[i] = temp;
		}

	}

	public double getFitness() throws SolutionEmptyException
	{
		if (variables.length == 0)
			throw new SolutionEmptyException();
		if (fitness == -1)
			fitness = fitnessFunction.evaluate(this);
		return fitness;
	}

	public T getVariable(int position)
	{
		return variables[position];
	}
}
