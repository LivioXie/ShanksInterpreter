package assignment1;

public class VariableReferenceNode extends StatementNode {
	
	private String name;
	private Node arrayIndex;
	
	//constructor
	public VariableReferenceNode(String name, Node arrayIndex) {
		this.name = name;
		this.arrayIndex = arrayIndex;
	}

	//get name
	public String getName() {
		return name;
	}

	//get ArrayIndext
	public Node getArrayIndex() {
		return arrayIndex;
	}

	//toString method
	@Override
	public String toString() {
		return "VariableReferenceNode [name=" + name + ", arrayIndex=" + arrayIndex + "]";
	}
}
