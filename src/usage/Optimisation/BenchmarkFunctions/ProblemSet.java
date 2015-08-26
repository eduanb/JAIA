package Optimisation.BenchmarkFunctions;

import algorithms.Optimisation.PopulationBased.PSO.Location;
import algorithms.Optimisation.PopulationBased.PSO.Particle;

import java.util.LinkedList;

public abstract interface ProblemSet
{
	public double evaluate(Location location);

	public double getErrTolarance();

	public LinkedList<Particle> initializeSwarm();

    public Particle resetParticle(Particle p);
	
	public int getProblemDimention();
	
	public double getTargetValue();
	
	public double[] getClampMaxValues();
	
	public double[] getClampMinValues();
	
	public String getName();
}
