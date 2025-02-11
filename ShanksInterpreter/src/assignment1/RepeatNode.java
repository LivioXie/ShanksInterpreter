package assignment1;

import java.util.List;

public class RepeatNode extends StatementNode {
	
	private BooleanCompareNode Condition;	
	private List<StatementNode> statement;
	
	//constructor
	public RepeatNode(BooleanCompareNode condition, List<StatementNode> statement) {
		Condition = condition;
		this.statement = statement;
	}
	
	//get condition
	public BooleanCompareNode getCondition() {
		return Condition;
	}
	
	//get statement
	public List<StatementNode> getStatement() {
		return statement;
	}

	//toString method
	@Override
	public String toString() {
		return "RepeatNode [Condition=" + Condition + ", statement=" + statement + "]";
	}
	
}
