package command.serverCommand;

import command.Command;
import symbol.SymbolTable;

public class CommandDisconnect implements Command {

	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		symT.client.sendCommand("bye");
		if(symT.client != null) 
			symT.client.close();
		if(symT.server != null) {
			symT.server.stopServer();
			symT.server.close();
		}
		return (double)comString.length;
	}

}
