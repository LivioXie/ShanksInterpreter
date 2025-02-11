package assignment1;

public class CharacterDataType extends InterpreterDataType{
	
	private char store;

	public CharacterDataType(char store) {
		this.store = store;
	}

	public char getStore() {
		return store;
	}

	@Override
	public String ToString() {
		return Character.toString(store);
	}
	
	@Override
	public void FromString(String input) {
		store = input.charAt(1);
	}
	
}
