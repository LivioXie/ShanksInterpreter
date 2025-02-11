package assignment1;

import java.util.List;

public class BuiltInRight extends FunctionNode{

	//constructor
	public BuiltInRight(String name, List<VariableNode> parameters, List<VariableNode> constants,
			List<VariableNode> variables, List<StatementNode> statements) {
		super(name, parameters, constants, variables, statements);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean isVariadic() {
        return false;
    }
	 //execute method
	public void execute(List<InterpreterDataType> list) {
		String input = ((StringDataType) list.get(0)).getStore();
		int length = ((IntegerDataType) list.get(1)).getStore();
		int someString = input.length() - length;
		String resultString = input.substring(someString);
		((StringDataType) list.get(2)).setStore(resultString);
	}

}
