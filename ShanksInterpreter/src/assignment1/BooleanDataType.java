package assignment1;

public class BooleanDataType extends InterpreterDataType{
	private boolean store;

	public BooleanDataType(boolean store) {
		this.store = store;
	}

	public boolean isStore() {
		return store;
	}

	@Override
	public String ToString() {
		return Boolean.toString(store);
	}
	
	@Override
	public void FromString(String input) {
	    store = Boolean.parseBoolean(input);
	}
}
