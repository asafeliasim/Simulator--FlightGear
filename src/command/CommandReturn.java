package command;

import expression.ShuntingYard;
import symbol.SymbolTable;

public class CommandReturn implements Command {

	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		//return breaks;
		//return 5;
		if(comString.length == 2 && symT.itExist(comString[1])) {
			
			symT.returnVal = symT.getSymbol(comString[1]).getValue(); 
					//symT.valTable.get(comString[1]); 
			return symT.returnVal;
		}
		// return x = y * z
		else
		{
			ShuntingYard shun = new ShuntingYard(comString, symT);
			double result = shun.calc();
			symT.returnVal =  result;
			return result;
		}
		
	}

}
