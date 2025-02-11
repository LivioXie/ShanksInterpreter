package assignment1;

public class AssignmentNode extends StatementNode {
	private VariableReferenceNode target;
	private Node value;
	
	//constructor
	public AssignmentNode(VariableReferenceNode target, Node value) {
		this.target = target;
		this.value = value;
	}

	//get target
	public VariableReferenceNode getTarget() {
		return target;
	}

	//get value
	public Node getValue() {
		return value;
	}
	
	//toString method
	@Override
	public String toString() {
		return "AssignmentNode [target=" + target + ", value=" + value + "]";
	}
}
