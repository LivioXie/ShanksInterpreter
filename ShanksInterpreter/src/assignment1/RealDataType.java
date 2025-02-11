package assignment1;

public class RealDataType extends InterpreterDataType {
	
	private double store;

	public RealDataType(double store) {
		this.store = store;
	}

	public double getStore() {
		return store;
	}

	public void setStore(double store) {
		this.store = store;
	}
	
	@Override
	public String ToString() {
		return "RealDataType [store=" + store + "]";
	}


	@Override
	public void FromString(String input) {
		store = Double.parseDouble(input);
	}

}
