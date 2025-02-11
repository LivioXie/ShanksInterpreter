package assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Interpreter {
	
	public HashMap<String, InterpreterDataType> getIDT() {
		return IDT;
	}

	//hashmap
	public HashMap<String, InterpreterDataType> IDT = new HashMap<String, InterpreterDataType>();
	public HashMap<String, FunctionNode> function = new HashMap<String, FunctionNode>();
	
	public void interpretFunction(FunctionNode function) throws SyntaxErrorException {
	    List<VariableNode> constant = function.getConstants();
	    //loop over constant
	    for(VariableNode s : constant) {
	        //create a new IDTS 
	        if(s.getType1() == VariableNode.variableType.REAL) {
	        	InterpreterDataType idt = new RealDataType(0);
	        	IDT.put(s.getName(), idt);
	        }else if(s.getType1() == VariableNode.variableType.INTEGER) {
	        	InterpreterDataType idt = new IntegerDataType(0);
	        	IDT.put(s.getName(), idt);
	        }else if(s.getType1() == VariableNode.variableType.STRING) {
	        	InterpreterDataType idt = new StringDataType("");
	        	IDT.put(s.getName(), idt);
	        }else if(s.getType1() == VariableNode.variableType.CHARACTER) {
	        	InterpreterDataType idt = new CharacterDataType(' ');
	        	IDT.put(s.getName(), idt);
	        }else if(s.getType1() == VariableNode.variableType.BOOLEAN) {
	        	InterpreterDataType idt = new BooleanDataType(false);
	        	IDT.put(s.getName(), idt);
	        }else 
	        	throw new SyntaxErrorException("Unknown variable type");
	    } 
	    List<VariableNode> variable = function.getVariables();
	    //loop over variable
	    for(VariableNode s : variable) {
	        //create a new IDTS
	    	   if(s.getType1() == VariableNode.variableType.REAL) {
		        	InterpreterDataType idt = new RealDataType(0);
		        	IDT.put(s.getName(), idt);
		        }else if(s.getType1() == VariableNode.variableType.INTEGER) {
		        	InterpreterDataType idt = new IntegerDataType(0);
		        	IDT.put(s.getName(), idt);
		        }else if(s.getType1() == VariableNode.variableType.STRING) {
		        	InterpreterDataType idt = new StringDataType("");
		        	IDT.put(s.getName(), idt);
		        }else if(s.getType1() == VariableNode.variableType.CHARACTER) {
		        	InterpreterDataType idt = new CharacterDataType(' ');
		        	IDT.put(s.getName(), idt);
		        }else if(s.getType1() == VariableNode.variableType.BOOLEAN) {	
		        	InterpreterDataType idt = new BooleanDataType(false);
		        	IDT.put(s.getName(), idt);
		        }else 
		        	throw new SyntaxErrorException("Unknown variable type");
	    }
	    //pass the hash map and the collection of StatementNode to interpretBlock(). 
	    List<StatementNode> statement = function.getStatements();
	    interpretBlock(statement, IDT);
	    FunctionCallNode functioncall = (FunctionCallNode) function.getParameters();
	    Execute(functioncall);
	}

    public void Execute(FunctionCallNode functionCallNode) throws SyntaxErrorException {
        //Locate the function definition
        String functionName = functionCallNode.getName();
        FunctionNode functioncall = function.get(functionName);
        if (functioncall == null) {
            throw new SyntaxErrorException("Function not found ");
        }
        //Check parameter count
        List<ParameterNode> arguments = functionCallNode.getParameter();
        List<VariableNode> parameters = functioncall.getParameters();
        boolean isVariadic = functioncall.isVariadic();
        if (arguments.size() != parameters.size() && !isVariadic) {
            throw new SyntaxErrorException("Incorrect number of parameters");
        }
        //Evaluate and collect parameter values
        List<InterpreterDataType> argumentValues = new ArrayList<>();
        for (Node argument : arguments) {
            InterpreterDataType argumentValue = expression(argument,IDT);
            argumentValues.add(argumentValue);
        }        
     // Call the function
        if (functioncall instanceof BuiltInRead) {
            BuiltInRead builtInFunction = (BuiltInRead) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInWrite) {
            BuiltInWrite builtInFunction = (BuiltInWrite) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInLeft) {
            BuiltInLeft builtInFunction = (BuiltInLeft) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInRight) {
            BuiltInRight builtInFunction = (BuiltInRight) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInSubstring) {
            BuiltInSubstring builtInFunction = (BuiltInSubstring) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInSquareRoot) {
            BuiltInSquareRoot builtInFunction = (BuiltInSquareRoot) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInGetRandom) {
            BuiltInGetRandom builtInFunction = (BuiltInGetRandom) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInIntegerToReal) {
            BuiltInIntegerToReal builtInFunction = (BuiltInIntegerToReal) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInRealToInteger) {
            BuiltInRealToInteger builtInFunction = (BuiltInRealToInteger) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInStart) {
            BuiltInStart builtInFunction = (BuiltInStart) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof BuiltInEnd) {
            BuiltInEnd builtInFunction = (BuiltInEnd) functioncall;
            builtInFunction.execute(argumentValues);
        }else if (functioncall instanceof FunctionNode) {
            FunctionNode userFunction = (FunctionNode) functioncall;
            interpretFunction(userFunction);
        }else {
            throw new SyntaxErrorException("Unknown function type");
        }
     // Loop over the values passed back from the function
        for (int i = 0; i < argumentValues.size(); i++) {
            VariableNode parameter = functioncall.getParameters().get(i);
            InterpreterDataType argumentValue = argumentValues.get(i);            
            // If the function is variadic or the parameter is marked as VAR and the invocation is marked as VAR
            if (functionCallNode.isVariadic() || (parameter.isChangeAble() && functionCallNode.getParameter().get(i).isVar())) {
                String parameterName = parameter.getName();
                // Update the variable value with the value passed back from the function
                IDT.put(parameterName, argumentValue);
            }
        }
    }
	
	//expression method
	private InterpreterDataType expression(Node s,HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		//handle mathOpNode
		if(s instanceof MathOpNode) {
			return interpretMathOpNode((MathOpNode) s, IDT);
			//handle variableReference
		}else if(s instanceof VariableReferenceNode) {
			String variable = ((VariableReferenceNode) s).getName();
	        if (IDT.containsKey(variable)) {
	            return IDT.get(variable);
	        } else {
	            throw new RuntimeException("Variable not defined.");
	        }
	        //handle intergerNOde
		}else if(s instanceof IntegerNode) {
			int value = ((IntegerNode) s).getMember();
		    return new IntegerDataType(value);
		    //handle RealNode
		}else if(s instanceof RealNode) {
			float value = ((RealNode) s).getMember();
	        return new RealDataType(value);
	        //handle String Node
		}else if (s instanceof StringNode) {
	        String value = ((StringNode) s).getMember();
	        return new StringDataType(value);
		}else{
	        throw new SyntaxErrorException("Unknown node type " );
		}
	}
	//interpretBlock method
	private void interpretBlock(List<StatementNode> statement, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		//loop over Statement collection
		for(StatementNode s : statement) {
			if(s instanceof IfNode)
				//call interpretIfNode
				interpretIfNode((IfNode) s, IDT);
			else if(s instanceof AssignmentNode)
				//call interpretAssignmentNOde
				interpretAssignmentNode((AssignmentNode) s, IDT);
			else if(s instanceof WhileNode)
				//call interpretWhileNOde
				interpretWhileNode((WhileNode) s, IDT);
			else if(s instanceof ForNode)
				//call interpretForNOde
				interpretForNode((ForNode) s, IDT);
			else if(s instanceof RepeatNode)
				//call interpretRepeatNode
				interpretRepeatNode((RepeatNode) s, IDT);
			else if(s instanceof VariableReferenceNode)
				//call interpretVariableReferenceNode
				interpretVariableReferenceNode((VariableReferenceNode) s, IDT);
			else
				throw new SyntaxErrorException("Unknown statement type");
		}
	}

	//interpret MathOpNode
	private InterpreterDataType interpretMathOpNode(MathOpNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		//call expression
		InterpreterDataType left = expression(s.getLeft(), IDT);
		InterpreterDataType right = expression(s.getRight(), IDT);
		//handle integerDataType
		if(left instanceof IntegerDataType && right instanceof IntegerDataType) {
			 int lvalue = ((IntegerDataType) left).getStore();
	         int rvalue = ((IntegerDataType) right).getStore();
	         switch(s.getMathOperation()) {
	         case ADD:
	        	 return new IntegerDataType(lvalue + rvalue);
	         case SUBTRACT:
	        	 return new IntegerDataType(lvalue - rvalue);
	         case MULTIPLY:
	        	 return new IntegerDataType(lvalue * rvalue);
	         case DIVIDE:
	        	 return new IntegerDataType(lvalue / rvalue);
	         default:
	        	 throw new SyntaxErrorException("Unknown operator");
	         }
	         //handle realDataType
		}else if(left instanceof RealDataType && right instanceof RealDataType) {
			 double lvalue = ((RealDataType) left).getStore();
	         double rvalue = ((RealDataType) right).getStore();
	         switch(s.getMathOperation()) {
	         case ADD:
	        	 return new RealDataType(lvalue + rvalue);
	         case SUBTRACT:
	        	 return new RealDataType(lvalue - rvalue);
	         case MULTIPLY:
	        	 return new RealDataType(lvalue * rvalue);
	         case DIVIDE:
	        	 return new RealDataType(lvalue / rvalue);
	         default:
	        	 throw new SyntaxErrorException("Unknown operator");
	         }
		}else {
            throw new SyntaxErrorException("not math operator");
		}
	}
	//interpretVariableReferenceNode
	private void interpretVariableReferenceNode(VariableReferenceNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		//look for name
		String variable = s.getName();
		//if inside hashmap, have IDT
	    if (IDT.containsKey(variable)) {
	        IDT.get(variable);
	        //else throw exception
	    } else {
	    	throw new SyntaxErrorException("Variable not defined.");
	    }
	}

	//interpretRepeatNode
	private void interpretRepeatNode(RepeatNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		//call booleanCompare
		InterpreterDataType result = booleanCompare(s.getCondition(), IDT);
	    while(!((BooleanDataType) result).isStore()) {
	    	//call interpretBlock
	        interpretBlock(s.getStatement(), IDT);
	        result = booleanCompare(s.getCondition(), IDT);
	    }
	}

	//booleanCompare method
	private InterpreterDataType booleanCompare(BooleanCompareNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		//call expression
	    InterpreterDataType left = expression(s.getLeft(), IDT);
	    InterpreterDataType right = expression(s.getRight(), IDT);
	    boolean result = false;
	    switch(s.getType()) {
	    //equal 
	        case EQUAL:
	        	//handle integerDataType
	            if (left instanceof IntegerDataType && right instanceof IntegerDataType) {
	                result = ((IntegerDataType) left).getStore() == ((IntegerDataType) right).getStore();
	                //handle realDataType
	            } else if (left instanceof RealDataType && right instanceof RealDataType) {
	                result = ((RealDataType) left).getStore() == ((RealDataType) right).getStore();
	                //handle booleanDataType
	            } else if (left instanceof BooleanDataType && right instanceof BooleanDataType) {
	                result = ((BooleanDataType) left).isStore() == ((BooleanDataType) right).isStore();
	                //handle StringDataType
	            } else if (left instanceof StringDataType && right instanceof StringDataType) {
	                result = ((StringDataType) left).getStore().equals(((StringDataType) right).getStore());
	            } else {
	                throw new SyntaxErrorException("Incompatible types for comparison.");
	            }
	            break;
	            //lessthan
	        case LESSTHAN:
	        	//handle IntegerDataType
	            if (left instanceof IntegerDataType && right instanceof IntegerDataType) {
	                result = ((IntegerDataType) left).getStore() < ((IntegerDataType) right).getStore();
	                //handle RealDataType
	            } else if (left instanceof RealDataType && right instanceof RealDataType) {
	                result = ((RealDataType) left).getStore() < ((RealDataType) right).getStore();
	            } else {
	                throw new SyntaxErrorException("Incompatible types for comparison.");
	            }
	            break;
	            //greaterthan
	        case GREATERTHAN:
	        	//handle IntegerDataType
	            if (left instanceof IntegerDataType && right instanceof IntegerDataType) {
	                result = ((IntegerDataType) left).getStore() > ((IntegerDataType) right).getStore();
	              //handle RealDataType
	            } else if (left instanceof RealDataType && right instanceof RealDataType) {
	                result = ((RealDataType) left).getStore() > ((RealDataType) right).getStore();
	            } else {
	                throw new SyntaxErrorException("Incompatible types for comparison.");
	            }
	            break;
	            //lessthanandequalto
	        case LESSTHANANDEQUALTO:
	        	//handle IntegerDataType
	            if (left instanceof IntegerDataType && right instanceof IntegerDataType) {
	                result = ((IntegerDataType) left).getStore() <= ((IntegerDataType) right).getStore();
	              //handle RealDataType
	            } else if (left instanceof RealDataType && right instanceof RealDataType) {
	                result = ((RealDataType) left).getStore() <= ((RealDataType) right).getStore();
	            } else {
	                throw new SyntaxErrorException("Incompatible types for comparison.");
	            }
	            break;
	            //greaterthanandequalto
	        case GREATERTHANANDEQUALTO:
	        	//handle IntegerDataType
	            if (left instanceof IntegerDataType && right instanceof IntegerDataType) {
	                result = ((IntegerDataType) left).getStore() >= ((IntegerDataType) right).getStore();
	              //handle RealDataType
	            } else if (left instanceof RealDataType && right instanceof RealDataType) {
	                result = ((RealDataType) left).getStore() >= ((RealDataType) right).getStore();
	            } else {
	                throw new SyntaxErrorException("Incompatible types for comparison.");
	            }
	            break;
	        default:
	            throw new SyntaxErrorException("Invalid boolean comparison operator.");
	    }
	    return new BooleanDataType(result);
	}

	//interpret ForNode
	private void interpretForNode(ForNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		InterpreterDataType start = expression(s.getFrom(), IDT);
		InterpreterDataType end = expression(s.getTo(), IDT);
		if(start instanceof IntegerDataType && end instanceof IntegerDataType) {
			int start2 = ((IntegerDataType) start).getStore();
			int end2 = ((IntegerDataType) end).getStore();
			String variable = ((VariableReferenceNode) s.getFrom()).getName();
			//loop over the range
			for(int i = start2; i <= end2; i++) {
				IDT.put(variable, end);
				//call interpretBlock
				interpretBlock(s.getStatement(),IDT);
			}
		}else
			throw new SyntaxErrorException("Invalid for loop range");
	}

	//interpret WhileNode
	private void interpretWhileNode(WhileNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		while(true) {
			//call booleanCompare
			InterpreterDataType boolCompare = booleanCompare(s.getCondition(), IDT);
			//check if boolCompare instanceof BooleanDataType
			if(boolCompare instanceof BooleanDataType) {
	            if(((BooleanDataType) boolCompare).isStore()) {
	            	//call interpretBlock
	                interpretBlock(s.getStatement(), IDT);
	            } else {
	                break;
	            }
	        } else {
	            throw new SyntaxErrorException("expected boolean expression");
	        }
		}
	}

	//interpret IfNode
	private void interpretIfNode(IfNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		//call booleanCompare
		InterpreterDataType boolCompare = booleanCompare(s.getCondition(), IDT);
		//check if boolCompare instance of BooleanDataType
		if(boolCompare instanceof BooleanDataType) {
			if(((BooleanDataType) boolCompare).isStore())
				//call interpretBlock
				interpretBlock(s.getStatement(), IDT);
			else if(s.getNextIf() != null) 
				//call interpretIfNode
				interpretIfNode(s.getNextIf(), IDT);
		}else
			throw new SyntaxErrorException("expected boolean expression");
	}

	//interpret AssignmentNode
	private void interpretAssignmentNode(AssignmentNode s, HashMap<String, InterpreterDataType> IDT) throws SyntaxErrorException {
		// TODO Auto-generated method stub
		InterpreterDataType value = expression(s.getValue(), IDT);
		// Check target is a variable reference
	    if (!(s.getTarget() instanceof VariableReferenceNode)) {
	        throw new SyntaxErrorException("Invalid target");
	    }
	    // Get the variable name
	    String varName = ((VariableReferenceNode) s.getTarget()).getName();
	    // Replace the IDT entry for the variable with the new value
	    if (IDT.containsKey(varName)) {
	        IDT.replace(varName, value);
	    } else {
	        IDT.put(varName, value);
	    }
	}
}
