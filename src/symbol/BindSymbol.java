package symbol;


public class BindSymbol implements Symbol {
	private SimPath symP; 
	private String varPath;
	private SymbolTable symbolTable;
	
	@SuppressWarnings("serial")
	public static class ValNotinitialize extends Exception{

		public ValNotinitialize(String str) {
			super(str);
			// TODO Auto-generated constructor stub
		}
		
	}
	public BindSymbol(SymbolTable symT,String path) {
		this.symbolTable = symT;
		this.varPath = path;
		if(!symT.ifSpathExist(path)) {
			SimPath simp = new SimPath(path);
			symT.putSimPath(simp);
			this.symP = simp;
		}
		else
			symP = symT.getSimPath(path);
	}
	@Override
	public void setValue(double val) {
		if(symP == null)
			try {
				throw new Exception(new ValNotinitialize("Error! this SimPath isn't Exist"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		symP.setValue(val);
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return symP.getValue();
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return this.varPath;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPath(String str) {
		// TODO Auto-generated method stub
		
	}
	
}
