package symbol;


public class SimPath {
	private String pathName;
	private double value;
	
	public SimPath(String path) {
		this.pathName = path;
		this.value = 0;
	}

	public String getPathName() {
		return pathName;
	}
	
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
	
}
