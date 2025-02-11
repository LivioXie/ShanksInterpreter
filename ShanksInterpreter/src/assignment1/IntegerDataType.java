package assignment1;

public class IntegerDataType extends InterpreterDataType{
	
	private int store;

	public IntegerDataType(int store) {
		this.store = store;
	}

	public int getStore() {
		return store;
	}

	public void setStore(int store) {
		this.store = store;
	}

	@Override
	public String ToString() {
		return Integer.toString(store);
	}

	@Override
	public void FromString(String input) {
		store = Integer.parseInt(input);
	}
}
