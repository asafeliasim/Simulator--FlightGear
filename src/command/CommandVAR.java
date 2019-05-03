package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import symbol.BindSymbol;
import symbol.RegularSymbol;
import symbol.SimPath;
import symbol.Symbol;
import symbol.SymbolTable;


public class CommandVAR implements Command{
	
	@Override
	public double docommand(String[] comString, SymbolTable symT) throws Exception {
		// if "var breaks"; 
		if(comString.length == 2 && !comString[1].contains("=")) 
		{
			symT.putSymbol(comString[1]
					, new Symbol() {
						@SuppressWarnings("serial") class uninitializedSymbolException extends Exception{

							public uninitializedSymbolException(String str) {
								super(str);
								// TODO Auto-generated constructor stub
							}
							
						}
						@Override
						public void setValue(double val) {}
						@Override
						public void setPath(String str) {}
						@Override
						public boolean isInitialized() {return false;}
						@Override
						public double getValue() {
							try {
								throw new Exception(new uninitializedSymbolException("Error!! "
										+ "uninitialized Symbol!! cannot get value!!!"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return 0;
						}
						@Override
						public String getPath() {
							try {
								throw new Exception(new uninitializedSymbolException("Error!! "
										+ "uninitialized Symbol!! cannot get path!!!"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							// TODO Auto-generated method stub
							return null;
						}
						@Override
						public String getName() {
							
							try {
								throw new Exception(new uninitializedSymbolException("Error!! "
										+ "uninitialized Symbol!! cannot get Name!!!"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return null;
						}
					});
			
			
			return (double)comString.length;
		}
		// if "var breaks=3"
		// " "+char --> change to string
		// var head=1
		else if(comString.length == 2 && comString[1].contains("=")) {
			String []keyAndVal = comString[1].split("=");
			// keyAndVal = {breaks , 3}
			RegularSymbol sym = new RegularSymbol(keyAndVal[0],Double.parseDouble(keyAndVal[1]));
			symT.putSymbol(sym.getName(), sym);
			//symT.valTable.put(keyAndVal[0], Double.parseDouble(keyAndVal[1]));
			//System.out.println();
			return (double)comString.length;
		}
		// or (0)var (1)breaks (2)= (3)bind (4)//engine.engine.123.break//;
		else if (comString[3].equals("bind")) {
			BindSymbol bSmy = new BindSymbol(symT, comString[4]);
			symT.putSymbol(comString[1], bSmy);
			
			return (double)comString.length;
			}
		
		else 
		{
			Command c = new CommandAssignment();
			List<String> current = new ArrayList<>(Arrays.asList(comString));
			if(current.contains("var"))
				current.remove("var");
			String[] newStr = current.toArray(new String[0]);
		
			c.docommand(newStr, symT);
			return (double)comString.length;
		}
		
	}
	
}
