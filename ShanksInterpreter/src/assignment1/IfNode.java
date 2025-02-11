package assignment1;

import java.util.List;

public class IfNode extends StatementNode{
	
	private BooleanCompareNode Condition;	
	private List<StatementNode> statement;
	private IfNode nextIf;
	
	//constructor
	public IfNode(BooleanCompareNode condition, List<StatementNode> statement, IfNode nextIf) {
		Condition = condition;
		this.statement = statement;
		this.nextIf = nextIf;
	}

	//get condition
	public BooleanCompareNode getCondition() {
		return Condition;
	}

	//get statement
	public List<StatementNode> getStatement() {
		return statement;
	}

	//get nextIf
	public IfNode getNextIf() {
		return nextIf;
	}

	//toString method
	@Override
	public String toString() {
		return "IfNode [Condition=" + Condition + ", statement=" + statement + ", nextIf=" + nextIf + "]";
	}

}
