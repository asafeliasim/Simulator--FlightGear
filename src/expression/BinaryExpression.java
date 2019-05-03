package expression;

public abstract class BinaryExpression implements MathExpression {

	protected MathExpression left;
	protected MathExpression right;
	
	//public BinaryExpression() {}
	public BinaryExpression(MathExpression left, MathExpression right) {
		this.left = left;
		this.right = right;
	}


}
