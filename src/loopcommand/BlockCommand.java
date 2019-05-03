package loopcommand;
import java.util.ArrayList;
import java.util.List;

import command.Command;
import command.CommandAssignment;
import command.CommandPrint;
import command.CommandSleep;
import command.CommandVAR;

import symbol.SymbolTable;

public class BlockCommand implements Command {

	List<String> commandList;
	WhileCondition wc;
	private SymbolTable symTable;
	private boolean continueLoop = true;
	
	public BlockCommand() {}
	public BlockCommand(WhileCondition whilec, SymbolTable symT) {
		this.wc = whilec;
		this.symTable = symT;
		commandList = new ArrayList<>();
	}
	@SuppressWarnings("serial")
	public static class CommandNotExist extends Exception{

		public CommandNotExist(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	@Override
	public double docommand(String[] comString, SymbolTable symT)
			throws Exception {
		while(continueLoop) {
			
			if (!(wc.ifWTrue(wc.getSym(), wc.getOperator(), wc.getVal(), symT)))
				continueLoop = false;
		Command c;
		switch(comString[0]) {
		 case "print":
		 {
			 c= new CommandPrint();
			 c.docommand(comString, null);
			 return (double)comString.length;
		 }
		 case "var":
		 {
			 c= new CommandVAR();
			 c.docommand(comString, null);
			 return (double)comString.length;
		 }
		 case "sleep":
		 {
			 c= new CommandSleep();
			 c.docommand(comString, null);
			 return (double)comString.length;
		 }
		 default:
		 {
			 c= new CommandAssignment();
			 c.docommand(comString, null);
			 return (double)comString.length;
		 }
			
		}
		
		}
		return (double)comString.length;
	}
	
	public void putCommand(String str) {
		this.commandList.add(str);
	}
}
