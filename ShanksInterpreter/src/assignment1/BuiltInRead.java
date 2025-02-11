package assignment1;

import java.util.List;
import java.util.Scanner;

public class BuiltInRead extends FunctionNode{
	
	//constructor
	public BuiltInRead(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
		Scanner s = new Scanner(System.in);
		String a = s.nextLine();
		String[] input = a.split(" ");
		for (int i = 0; i < list.size(); i++) {
	        list.get(i).FromString(input[i]);
	    }
		s.close();
	}
}
