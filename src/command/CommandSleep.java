package command;

import java.util.concurrent.TimeUnit;

import symbol.SymbolTable;

public class CommandSleep implements Command {
	private long parmater;
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		this.parmater = Long.parseLong(comString[1]);
		try {
			TimeUnit.MILLISECONDS.sleep(parmater);
			System.out.println("The program is going to sleep for: " + parmater + "ms");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (double)comString.length;
	}

}
