package algorithms.Optimisation.PopulationBased.PSO;

/**
 * Created by Eduan on 2015-08-29.
 */
public class MomentumGbestPSO extends GbestPSO {

    public MomentumGbestPSO(double C1, double C2, double momentumMin, double momentumMax, int iterationMax)
    {
        super(C1,C2);
        this.momentumMin = momentumMin;
        this.momentumMax = momentumMax;
        this.iterationMax = iterationMax;
    }
    double momentumMin,momentumMax,iterationMax;
    @Override
    protected double getMomentum(int iteration)
    {
        return  (momentumMax - (((double) iteration) / iterationMax) * (momentumMax - momentumMin));
    }
}
