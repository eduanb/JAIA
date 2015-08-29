package algorithms.Optimisation.Solution;

import algorithms.Optimisation.FitnessFunction;

import java.lang.reflect.Array;
import java.util.Random;

public class Solution<T>
{
	protected T[] variables;
	protected Random random;
	protected double fitness = -1;
	protected boolean isFitenssSet = false;

	public T[] getVariables() {
		return variables;
	}

	public void setVariable(int pos, T value)
	{
		variables[pos] = value;
	}

	public int getNumberOfVariables()
	{
		return variables.length;
	}

	public Solution(T[] variables)
	{
		this.variables = variables;
		random = new Random();
	}

	public Solution(Class<T> clazz, int numberOfVariables)
	{
		this.variables = (T[]) Array.newInstance(clazz.getComponentType(), numberOfVariables);
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

	public void swop(Solution<T> other, int point) throws SolutionTypeException
	{
		for (int i = 0; i < point; i++)
		{
			T temp = this.variables[i];
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

	public double calculateFitness(FitnessFunction ff)
	{
		this.fitness = ff.evaluate(this);
		this.isFitenssSet = true;
		return this.fitness;
	}

	public T getVariable(int position)
	{
		return variables[position];
	}
}
