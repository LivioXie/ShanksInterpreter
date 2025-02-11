package assignment1;

import java.util.List;

public class ForNode extends StatementNode {
	
	private Node from;
	private Node to;
	private List<StatementNode> statement;
	
	//constructor
	public ForNode(Node from, Node to, List<StatementNode> statement) {
		this.from = from;
		this.to = to;
		this.statement = statement;
	}

	//get from 
	public Node getFrom() {
		return from;
	}

	//get to
	public Node getTo() {
		return to;
	}

	//get statement
	public List<StatementNode> getStatement() {
		return statement;
	}

	//toString method
	@Override
	public String toString() {
		return "ForNode [from=" + from + ", to=" + to + ", statement=" + statement + "]";
	}
	

}
