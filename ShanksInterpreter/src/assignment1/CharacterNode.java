package assignment1;

public class CharacterNode extends Node{
	char member;

	//constructor
	public CharacterNode(char member) {
		this.member = member;
	}
	//get method
	public char getMember() {
		return member;
	}
	
	//toString method
	@Override
	public String toString() {
		return "CharacterNode [member=" + member + "]";
	}

}
