package assignment1;

public class RealNode extends Node {
	//float member
	private float member;

	//constructor
	public RealNode(float member) {
		this.member = member;
	}

	//get method
	public float getMember() {
		return member;
	}

	//toString method
	@Override
	public String toString() {
		return Float.toString(member);
	}
}
