package assignment1;

public class StringDataType extends InterpreterDataType {
	private String store;

	public StringDataType(String store) {
		this.store = store;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Override
	public String ToString() {
		return store;
	}

	@Override
	public void FromString(String input) {
		store = input;
	}
}
