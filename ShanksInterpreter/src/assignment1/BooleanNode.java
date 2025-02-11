package assignment1;

public class BooleanNode extends Node{
	boolean member;
	//constructor
	public BooleanNode(boolean member) {
		this.member = member;
	}
	//get method
	public boolean getMember() {
		return member;
	}
	//toString
	@Override
	public String toString() {
		return "BooleanNode [member=" + member + "]";
	}


	
}
