package assignment1;

import java.util.List;

public class BuiltInWrite extends FunctionNode{

	//constructor
	public BuiltInWrite(String name, List<VariableNode> parameters, List<VariableNode> constants,
			List<VariableNode> variables, List<StatementNode> statements) {
		super(name, parameters, constants, variables, statements);
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean isVariadic() {
        return true;
    }
	 //execute method
	public void execute(List<InterpreterDataType> list) {
		StringBuilder acc = new StringBuilder();
		for(InterpreterDataType x : list) {
			acc.append(x.ToString());
			acc.append(" ");
		}
		System.out.println(acc.toString());
	}
}
