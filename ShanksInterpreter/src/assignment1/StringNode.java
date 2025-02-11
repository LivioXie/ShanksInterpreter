package assignment1;

public class StringNode extends Node{
	String member;

	//constructor
	public StringNode(String member) {
		this.member = member;
	}

	//get method
	public String getMember() {
		return member;
	}

	//toString method
	@Override
	public String toString() {
		return "StringNode [member=" + member + "]";
	}

	
}
