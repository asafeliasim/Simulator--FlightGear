package symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import command.serverCommand.CommandConnect;
import command.serverCommand.CommandOpenServer;
import interpeter.Lexer;
import interpeter.Parser;

public class SymbolTable {
	private HashMap<String, Symbol> symbolMap;
	public CommandConnect client = null;
	private List<SimPath> spathList; 
	public CommandOpenServer server = null;
	public Lexer lex = null ;
	public Parser par = null ;
	public Double returnVal = null;
	@SuppressWarnings("serial" )
	private static class SymbolNotExist extends Exception{

		public SymbolNotExist(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	public SymbolTable() {
		symbolMap = new HashMap<>();
		spathList = new ArrayList<>();
		
	}
	
	public void putSymbol(String s, Symbol sym) {
		this.symbolMap.put(s, sym);
	}
	public Symbol getSymbol(String s) throws SymbolNotExist {
		if(!symbolMap.containsKey(s))
			throw new SymbolNotExist("Error! Symbol not Exist" + s);
		return symbolMap.get(s);
	}
	
	public boolean itExist(String s) {
		 return this.symbolMap.containsKey(s);
	}
	public boolean ifSpathExist(String path) {
		for (SimPath simPath : spathList) {
			if(simPath.getPathName().equals(path))
				return true;
		}
		return false;
	}
	public void putSimPath(SimPath smp) {
		this.spathList.add(smp);
	}
	public SimPath getSimPath(String path) {
		for (SimPath simPath : spathList) {
			if(simPath.getPathName().equals(path))
				return simPath;
		}
		return null;
	}
	public boolean symbolContain(String str) {
		return this.symbolMap.containsKey(str);
	}
	public void removeSymbol(String str) {
		this.symbolMap.remove(str);
	}
	public int numOfPaths() {
		return this.spathList.size();
	}
	public void setValueToAll(String[] vals) {
		for(int i = 0; i < spathList.size() ; i++) {
			if(!(Double.parseDouble(vals[i]) == 0.0 && spathList.get(i).getValue() != 0.0))
				spathList.get(i).setValue(Double.parseDouble(vals[i]));
		}
	}
}
