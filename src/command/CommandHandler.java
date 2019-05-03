package command;

import java.util.HashMap;

import command.serverCommand.CommandConnect;
import command.serverCommand.CommandDisconnect;
import command.serverCommand.CommandOpenServer;
import loopcommand.WhileCondition;


public class CommandHandler {
	public HashMap<String, Command> commandMap;
	
	public CommandHandler() {
		commandMap = new HashMap<>();
		commandMap.put("var", new CommandVAR());
		commandMap.put("print", new CommandPrint());
		commandMap.put("sleep", new CommandSleep());
		commandMap.put("openDataServer", new CommandOpenServer());
		commandMap.put("connect", new CommandConnect());
		//commandMap.put("while", new WhileCondition(null));
		commandMap.put("return", new CommandReturn());
		commandMap.put("disconnect", new CommandDisconnect());
	}

}
