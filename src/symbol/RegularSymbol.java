package symbol;
// x = 5;
public class RegularSymbol implements Symbol {

	private String name;
	private double value;
	private String path;
	
	public RegularSymbol(String n) {
		this.setName(n);
		value = 0;
	}
	public RegularSymbol(String n, double num) {
		this.setName(n);
		this.value = num;
	}
	@Override
	public void setValue(double val) {
		this.value = val;
	}	
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	@Override
	public boolean isInitialized() {
		
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return this.path;
	}
	@Override
	public void setPath(String s) {
		this.path = s;
	}

}
