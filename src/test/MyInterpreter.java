package test;

import java.util.ArrayList;

import java.util.List;

import interpeter.*;


public class MyInterpreter {

	public static int interpret(String[] lines) {
		String[] path = { "simX", "simY", "simZ" };
		Interpeter in = new Interpeter(path);
		double result = 0;
		int startw = 0;
		int endw = 0;
		int i = 0;
		for (i = 0; i < lines.length; i++) {
			try {
				if (lines[i].contains("while")) {
					startw = i;
					continue;
				}
				if (startw != 0 && endw == 0) {
					if (lines[i].equals("}")) {
						endw = ++i;
						List<String[]> build = new ArrayList<>();
						int j = startw;
						for (; j <= endw; j++) {
							build.add(in.lex.lexer(lines[j], in.par.getSymbol()));
						}
						i = j;
						in.par.parser(build);
						break;
					}
					continue;
				}
				in.par.parser(in.lex.lexer(lines[i], in.par.getSymbol()));
				if (in.par.getSymbol().returnVal != null) {
					result = in.par.getSymbol().returnVal;
					return (int) result;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		try {
			return (int) in.par.parser(in.lex.lexer(lines[i-1], in.par.getSymbol()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
