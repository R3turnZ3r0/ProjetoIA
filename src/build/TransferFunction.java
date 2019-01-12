package build;

public class TransferFunction
{

	public double evalute(double value)
	{
		return 1 / (1 + Math.pow(Math.E, - value));
	}

	public double evaluteDerivate(double value)
	{
		return (value - Math.pow(value, 2));
	}
}
