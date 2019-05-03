package expression;

public class Div extends BinaryExpression{

	public Div(MathExpression left, MathExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	public double calculate() {
		// TODO Auto-generated method stub
		return left.calculate() / right.calculate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Div))
			return false;
		Div other = (Div)obj;
		
		return left.equals(other.left)&& right.equals(other.right);
	}
	
	
}
