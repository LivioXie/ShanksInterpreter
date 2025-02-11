package assignment1;

import java.util.List;

public class WhileNode extends StatementNode{

	private BooleanCompareNode Condition;	
	private List<StatementNode> statement;
	
	//constructor
	public WhileNode(BooleanCompareNode condition, List<StatementNode> statement) {
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
		return "WhileNode [Condition=" + Condition + ", statement=" + statement + "]";
	}
	
}
