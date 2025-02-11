package assignment1;

import java.util.List;

public class BuiltInSubstring extends FunctionNode{

	//constructor
	public BuiltInSubstring(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
		String someString = ((StringDataType) list.get(0)).getStore();
		int index = ((IntegerDataType) list.get(1)).getStore();
		int length = ((IntegerDataType) list.get(2)).getStore();
		String resultString = someString.substring(index, index + length);
		((StringDataType) list.get(3)).setStore(resultString);
	}

}
