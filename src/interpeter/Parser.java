package interpeter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import command.Command;
import command.CommandAssignment;
import command.CommandHandler;
import command.CommandReturn;
import command.serverCommand.CommandConnect;
import loopcommand.BlockCommand;
import loopcommand.WhileCondition;
import symbol.SimPath;
import symbol.SymbolTable;

//import java.util.HashMap;

public class Parser 
{
	CommandHandler ch;
	private SymbolTable symbol;
	HashMap<String, Command> commandMap;

	public Parser(String[] path) {
		this.ch = new CommandHandler();
		this.setSymbol(new SymbolTable());
		this.commandMap = new HashMap<>();
		for (String str : path) {
			getSymbol().putSimPath(new SimPath(str));
		}
		if(this.symbol.par == null)
			this.symbol.par = this;
		
	}
	public int parser(String[] comString) throws Exception {
		Command c;
		c = ch.commandMap.get(comString[0]);
		if (c == null) 
			c = new CommandAssignment();
		
		Regex reg= new Regex(Arrays.toString(comString));
		String[] string2Array = reg.returnStringArray(new StringBuilder());
		return  (int) c.docommand(string2Array, this.getSymbol());
	}
	public static String returnString(String[] comString) {
		String str = "";
		for(int i = 0; i<comString.length;i++) {
			str+= comString[i]+" ";
		}
		//var break=34+23+11;
		return str;
	}
	public SymbolTable getSymbol() {
		return symbol;
	}
	public void setSymbol(SymbolTable symbol) {
		this.symbol = symbol;
	}
	public Command whileCommands(String[] comString, BlockCommand bc) {
		Command c = new CommandAssignment(comString);
		
		return new CommandAssignment();
	}
	@SuppressWarnings("unlikely-arg-type")
	public int parser(List<String[]> comString) {
		Command c;
		if(comString.get(0)[0].equals("while")) {
			c = new WhileCondition(comString);
		}
		else {
			c = ch.commandMap.get(comString.get(0));
			if(c == null)
				c= new CommandAssignment();
		}
		double result = 0;
		try {
			Regex reg= new Regex(Arrays.toString(comString.get(0)));
			String[] string2Array = reg.returnStringArray(new StringBuilder());
			return  (int) c.docommand(string2Array, this.getSymbol());
			//result = c.docommand(comString.get(0), this.getSymbol());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)result;
		/*
		try {
			double result = c.docommand(comString.get(comString.size()-1), this.symbol);
			return (int)result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//return -1;
	}
	public double retVal() {
		return this.symbol.returnVal;
	}
}
