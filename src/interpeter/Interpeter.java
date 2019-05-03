package interpeter;

public class Interpeter {
	public Parser par;
	public Lexer lex;
	
	public Interpeter(String[] path) {
		par = new Parser(path);
		lex = new Lexer();
	}
}
