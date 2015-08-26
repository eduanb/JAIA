package benchmark;

import java.util.LinkedList;

import pso.Location;
import pso.Particle;

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
