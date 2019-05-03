package expression;

public class Mul extends BinaryExpression {

	public Mul(MathExpression left, MathExpression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}

	public double calculate() {
		// TODO Auto-generated method stub
		return (left.calculate() * right.calculate());
	}
}
