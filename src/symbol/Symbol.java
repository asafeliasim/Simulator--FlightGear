package symbol;

public interface Symbol  {
	public void setValue(double val);
	public double getValue();
	public boolean isInitialized();
	public String getPath();
	public String getName();
	public void setPath(String str);
}
