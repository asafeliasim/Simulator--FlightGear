package expression;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//import interpeter.symbol;
import symbol.SymbolTable;


public class ShuntingYard {
	public Queue<String> queue;
	public Stack<String> stack;
	public Stack<MathExpression> stackExp;
	public SymbolTable symbol;
	public String[] comString;

	public ShuntingYard(String[] inCome, SymbolTable symT) {
		queue = new LinkedList<String>();
		stack = new Stack<>();
		stackExp = new Stack<>();
		comString = inCome;
		symbol = symT;
	}
	public double calc() {
		for(String s: this.comString) {
			if(isDouble(s))
				queue.add(s);
			else
				switch(s) {
				case "return":
					break;
				case "*":
				case "/":
				case "(":
					stack.push(s);
					break;
				case "+":
				case "-":
					while(!stack.isEmpty() && (!stack.peek().equals("(")))
						queue.add(stack.pop());
					stack.push(s);
					break;
				case ")":
					while(!stack.peek().equals("(")) 
						queue.add(stack.pop());
					stack.pop();
					break;
				default:	
						queue.add(Double.toString(symbolvalue(s)));
						break;
			
			
		}
	}
		while(!stack.isEmpty()) {
			queue.add(stack.pop());
		}
		for(String str: queue) {
			if(isDouble(str)) 
				stackExp.add(new NumberExpression(Double.parseDouble(str)));
			else
			{
				MathExpression right= stackExp.pop();
				MathExpression left = stackExp.pop();
				switch(str) {
				case "/":
					stackExp.push(new Div(left,right));
					break;
				case "*":
					stackExp.push(new Mul(left,right));
					break;
				case "+":
					stackExp.push(new Plus(left,right));
					break;
				case "-":
					stackExp.push(new Minus(left,right));
					break;
				}
			}
				
		}
		return Math.floor((stackExp.pop().calculate()*1000))/1000 ;
}
	public double symbolvalue(String inCome) 
	{
		try {
			return this.symbol.getSymbol(inCome).getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return this.symbol.valTable.get(inCome);
		return 0;
	}
	
	private static boolean isDouble(String exp) {
		try {
			Double.parseDouble(exp);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
}

