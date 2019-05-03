package interpeter;
import java.util.Scanner;
import java.util.regex.Pattern;

import symbol.SymbolTable;

public class Lexer {
	String[] str;
	//private static final String lexerRejex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|-?\\d+\\.?\\d*|-?\\d*\\.?\\d+|\\\".*\\\"|==|!=|<|>|<=|>=|\\+|-|\\*|\\/|&&|\\|\\||!|=|\\(|\\)|\\{|\\}|\\w+)";
	Regex reg;
	@SuppressWarnings("serial")
	public static class StringNotFound extends Exception{

		public StringNotFound(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
	}
	@SuppressWarnings("unused")
	public String[] lexer(String line, SymbolTable symbol) throws Exception {
		if(symbol.lex == null)
			symbol.lex = this;
		Scanner s = new Scanner(line);
		if(s == null) 
			throw new StringNotFound("Error!! String do not Exist");
		String comString = s.nextLine();
		s.close();
		return str = comString.split(" ");
	}
	
}
