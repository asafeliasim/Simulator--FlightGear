package expression;

public class Plus extends BinaryExpression {
	

	public Plus(MathExpression left, MathExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	public double calculate() {
		// TODO Auto-generated method stub
		return left.calculate()+ right.calculate();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Plus))
			return false;
		Plus other = (Plus)obj;
		
		return left.equals(other.left)&& right.equals(other.right);
	}
}
