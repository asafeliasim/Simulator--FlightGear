package command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import expression.ShuntingYard;
import symbol.BindSymbol;
import symbol.RegularSymbol;
import symbol.SymbolTable;
public class CommandAssignment implements Command {
	
	public String[] params;
	
	public CommandAssignment (){}
	public CommandAssignment (String[] p){
		this.params = p;
	}
	@SuppressWarnings("serial")
	public static class PathNotExist extends Exception{

		public PathNotExist(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	} 
	@SuppressWarnings("serial")
	public static class ShuntingYardisNull extends Exception{

		public ShuntingYardisNull(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	@SuppressWarnings("unused")
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		// breaks = 2 ;
		// put the variable and the value in the symbol table
		if(comString[0].equals("}"))
			return 1;
		if(comString.length > 2 && ifcalcExpression(comString))
		{
			
			if(!symT.itExist(comString[0])) 
			{
				String[] s = this.newString(comString, symT);
				ShuntingYard Sh = new ShuntingYard(s,symT);
				RegularSymbol sNew = new RegularSymbol(comString[0]);
				sNew.setValue(Sh.calc());
				symT.putSymbol(sNew.getName(), sNew);
				Double size = (double) comString.length;
				return size;
			}
			else if(comString.length == 3 && symT.symbolContain(comString[0]) &&
					!symT.getSymbol(comString[0]).isInitialized()) {
					symT.removeSymbol(comString[0]);
					RegularSymbol sym =  new RegularSymbol(comString[0]);
					symT.putSymbol(comString[0],sym);
					sym.setValue(Double.parseDouble(comString[2]));
					return (double)comString.length;
			}
			else if(comString.length == 3 && symT.symbolContain(comString[0]) &&
					symT.getSymbol(comString[0]).isInitialized()){
						symT.getSymbol(comString[0]).setValue(Double.parseDouble(comString[2]));
						symT.client.sendValue(symT.getSymbol(comString[0]).getPath(),Double.parseDouble(comString[2]));
					}
			else if(comString.length >= 3)
			{
				// var x= bind ndsf;
				if(comString[3].equals("bind") && !symT.getSymbol(comString[0]).isInitialized()) {
				symT.removeSymbol(comString[1]);
				BindSymbol smb = new BindSymbol(symT,comString[4]);
				symT.putSymbol(comString[1],smb);
				return (double)comString.length;
				}
				
			else if (comString[2].equals("bind")) {
				if(symT.symbolContain(comString[0]) && !symT.getSymbol(comString[0]).isInitialized()) 
					symT.removeSymbol(comString[0]);	
				symT.putSymbol(comString[0], new BindSymbol(symT,comString[3]));
				return (double)comString.length;
				}
			else
			{	if(comString[1].equals("=")  && symT.itExist(comString[0])) {
				String answer = comString[0];
				String [] newStr = this.newStringWithNoSymbol(comString, symT);
				ShuntingYard shunt = new ShuntingYard(newStr, symT);
				if(shunt == null)
					throw new ShuntingYardisNull("Error! NullPointer");
				symT.getSymbol(answer).setValue(shunt.calc());
				//symT.client.sendValue(answer, symT.getSymbol(answer).getValue());
				return(double) comString.length;
				}
			else {
				ShuntingYard shunt = new ShuntingYard(comString, symT);
				if(shunt == null)
					throw new ShuntingYardisNull("Error! NullPointer");
				RegularSymbol symb = new RegularSymbol(comString[0], shunt.calc());
				symT.putSymbol(symb.getName(), symb);
				return (double) comString.length;
				}
			 }
		     }
		}
		else 
		{	
			ShuntingYard shunt = new ShuntingYard(comString, symT);
			if(shunt == null)
				throw new ShuntingYardisNull("Error! NullPointer");
			RegularSymbol symb = new RegularSymbol(comString[0], shunt.calc());
			symT.putSymbol(symb.getName(), symb);
			return (double)comString.length;
		}
		
		return (double)comString.length;
		
	}
	@SuppressWarnings("unlikely-arg-type")
	private static boolean ifcalcExpression(String[] income) {
		for (String string : income) {
			if(income.equals("bind"))
				return false;
		}
		return true;
	}
	public String[] newString(String[] old,SymbolTable symT) {
		List<String> build= new ArrayList<>(Arrays.asList(old));
		build.remove(0);
		build.remove(0);
		RegularSymbol sym = new RegularSymbol(old[0]);
		symT.putSymbol(old[0],sym);
	
		String[] result = build.toArray(new String[0]);
		return result;
	}
	public String[] newStringWithNoSymbol(String[] old, SymbolTable symT) {
		List<String> build = new ArrayList<>(Arrays.asList(old));
		build.remove(0);
		build.remove(0);
		String[] result = build.toArray(new String[0]);
		return result;
	}
}
