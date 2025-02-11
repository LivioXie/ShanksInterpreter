package assignment1;

import java.util.List;

public class ArrayDataType<T> extends InterpreterDataType {

	private List<T> store;

	public ArrayDataType(List<T> store) {
		this.store = store;
	}

	public List<T> getStore() {
		return store;
	}

	@Override
	public String ToString() {
		return "ArrayDataType [value=" + store + "]";
	}

	@Override
	public void FromString(String input) {
		store.add((T) input);
	}
}
