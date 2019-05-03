package command;

import symbol.SymbolTable;

public interface Command {
	
	public double docommand(String[] comString, SymbolTable symT) throws Exception;

}
