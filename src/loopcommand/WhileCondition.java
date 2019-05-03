package loopcommand;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import command.Command;
import interpeter.Lexer;
import interpeter.Parser;
import interpeter.Regex;
import symbol.SymbolTable;



public class WhileCondition implements Command {

	private BlockCommand bc;
	private String syml; // x
	private String operator; // <
	private String val; // 5
	private SymbolTable symbolT;
	private String[] build;
	private List<String[]> loopString;
	
	
	public WhileCondition(List<String[]> comString) {
	// TODO:: INIT THE VALIBAL.
		syml = comString.get(0)[1];
		operator = comString.get(0)[2];
		val = comString.get(0)[3];
		comString.remove(0);
		loopString = comString;
		bc = new BlockCommand();
	}
	@Override
	public double docommand(String[] comString, SymbolTable symT)throws Exception {
		this.symbolT = symT;
		
		while(true) 
		{
			if(!(this.ifWTrue(syml, operator, val, symT)))
				break;
			else {
			for(int i = 0; i < loopString.size() - 1 ; i++) {
					String[] line = loopString.get(i);
					Regex reg= new Regex(Arrays.toString(line));
					String[] string2Array = reg.returnStringArray(new StringBuilder());
					symT.par.parser(string2Array);
					//symT.par.parser(line);
				}
			}
			
		}	
		return comString.length;
	}
	public String getSym() {
		return this.syml;
	}
	public String getOperator() {
		return this.operator;
	}
	public String getVal() {
		return this.val;
	}

	public boolean ifWTrue(String val, String operator, String con, SymbolTable symT) {
		double value = 0;
		try {
			value = symT.getSymbol(val).getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch (operator) {
		case ">":
			return value > Double.parseDouble(con);
		case "<":
			return value < Double.parseDouble(con);
		case "!=":
			return value != Double.parseDouble(con);
		case "=":
			return value == Double.parseDouble(con);
		case "<=":
			return (value <= Double.parseDouble(con));
		case ">=":
			return value >= Double.parseDouble(con);
		default:
			return false;
		}

	}
	public String[] newStringLoop(String[] old, SymbolTable symT) {
		List<String> build = new ArrayList<>(Arrays.asList(old));
		build.remove(0);
		build.remove(0);
		build.remove(0);
		build.remove(0);
		build.remove(0);
		String[] result = build.toArray(new String[0]);
		return result;
	}
}
