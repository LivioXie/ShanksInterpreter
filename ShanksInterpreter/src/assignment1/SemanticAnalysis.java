package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SemanticAnalysis {

    private List<FunctionCallNode> functionCall = new ArrayList<>();
    
    Interpreter map = new Interpreter();
    HashMap<String, InterpreterDataType> mymap = map.getIDT();


    // method to perform semantic analysis on assignment statements
    public void checkAssignments(ProgramNode programNode) throws SyntaxErrorException {
        // iterate through each function in the program
        for (FunctionNode functionNode : programNode.getFunction().values()) {
            // perform semantic analysis on assignment statements in the function
            semanticAnalysis(functionNode);
        }
    }

    private void semanticAnalysis(Node node) throws SyntaxErrorException {
        if (node instanceof AssignmentNode) {
            // get the target and value nodes of the assignment
            VariableReferenceNode target = ((AssignmentNode) node).getTarget();
            Node value = ((AssignmentNode) node).getValue();
            // get the variable type of the target
            InterpreterDataType targetType = getVariableType(target);
            // recursively analyze the value node
            semanticAnalysis(value);
            // get the variable type of the value
            InterpreterDataType valueType = getVariableType(value);
            // compare the variable types of the target and value
            if (targetType != valueType) {
                // throw an exception with information for debugging
                throw new SyntaxErrorException("Type violation");
            }
        } else if (node instanceof MathOpNode) {
            // recursively analyze left and right expressions of MathOpNode
            semanticAnalysis(((MathOpNode) node).getLeft());
            semanticAnalysis(((MathOpNode) node).getRight());
        } else if (node instanceof FunctionCallNode) {
            String functionName = ((FunctionCallNode) node).getName();
            boolean functionExists = false;
            int expectedParamCount = 0;
            for (FunctionCallNode function : functionCall) {
                if (function.getName().equals(functionName)) {
                    functionExists = true;
                    expectedParamCount = function.getParameter().size();
                    break;
                }
            }
            if (!functionExists) {
                throw new SyntaxErrorException("Function not found: " + functionName);
            }
            int actualParamCount = ((FunctionCallNode) node).getParameter().size();
            if (expectedParamCount != actualParamCount) {
                throw new SyntaxErrorException("Incorrect number of parameters in function call");
            }
            // recursively analyze arguments
            for (Node argumentExpression : ((FunctionCallNode) node).getParameter()) {
                semanticAnalysis(argumentExpression);
            }
        } else if (node instanceof IfNode) {
            // recursively analyze the condition and statement list of the if node
            semanticAnalysis(((IfNode) node).getCondition());
            for (StatementNode statement : ((IfNode) node).getStatement()) {
                semanticAnalysis(statement);
            }
        }
    }

    // method to get the variable type of an expression
    private InterpreterDataType getVariableType(Node expression) throws SyntaxErrorException {
        if (expression instanceof VariableNode) {
            // get the variable name
            String variableName = ((VariableNode) expression).getName();
            // check if the variable is declared and get its type from the mymap map
            if (!mymap.containsKey(variableName)) {
                throw new SyntaxErrorException("Variable is not declared.");
            }
            return mymap.get(variableName);
        } else if(expression instanceof IntegerNode) {
			int value = ((IntegerNode) expression).getMember();
		    return new IntegerDataType(value);
		    //handle RealNode
		}else if(expression instanceof RealNode) {
			float value = ((RealNode) expression).getMember();
	        return new RealDataType(value);
	        //handle String Node
		}else if (expression instanceof StringNode) {
	        String value = ((StringNode) expression).getMember();
	        return new StringDataType(value);
		}else{
	        throw new SyntaxErrorException("Unknown node type " );
		}
    }
}
