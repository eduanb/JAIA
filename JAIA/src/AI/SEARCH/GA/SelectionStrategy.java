package AI.SEARCH.GA;

public interface SelectionStrategy<T>
{
	public Solution<T>[] select(Solution<T>[] solutionArray);
}
