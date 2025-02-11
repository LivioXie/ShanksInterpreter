package assignment1;

public class IntegerNode extends Node {
	
	//integer member
	private int member;

	//constructor
	public IntegerNode(int member) {
		this.member = member;
	}

	//get method
	public int getMember() {
		return member;
	}

	//toString method
	@Override
	public String toString() {
		return Integer.toString(member);
	}

}
