package interpeter;

//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {
	Pattern p;
	Matcher  m;
	String[] answer = null;
	public Regex(String comString) {
		this.p = Pattern.compile( "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|-?\\d+\\.?\\d*|-?\\d*\\.?\\d+|\\\".*\\\"|==|!=|<|>|<=|>=|\\+|-|\\*|\\/|&&|\\|\\||!|=|\\(|\\)|\\{|\\}|\\w+)");
		this.m = p.matcher(comString); 
		
	}
	   
	    //
	//var =y+4 ----> 
	public String[] returnStringArray(StringBuilder str) {
		while(m.find()) {
			str.append(m.group() + " ");
		}
		// System.out.println(Arrays.toString());
		return this.answer = str.toString().split(" "); 
		////S+[,]
	}
}
