package expression;

public class NumberExpression implements MathExpression {

	private double value;
	
	public NumberExpression(double val) {
		this.value = val;
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public double calculate() {
		return this.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof NumberExpression))
			return false;
		NumberExpression other = (NumberExpression)obj;
		
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

}
