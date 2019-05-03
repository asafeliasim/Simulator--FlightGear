package command;

import symbol.SymbolTable;

public class CommandPrint implements Command {

	
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(comString[0]+" "+ symT.getSymbol(comString[0]).getValue());
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (double)comString.length;
	}
}
