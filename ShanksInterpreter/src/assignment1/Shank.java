package assignment1;

import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shank {
	public static void main(String[] args) throws SyntaxErrorException, IOException {
		/**
		 * Ensure that there is one and only one argument (args).
		 * If there are none or more than 1, it must print an appropriate error message and exit. 
		 */
		if(args.length != 1) {
			System.out.println("Error");
			System.exit(1);
		}
		//readAllLines
		Path myPath1 = Paths.get("C:/Users/livio/eclipse-workspace/Assignment1/src/Parse.shank");
		List<String> lines1 = Files.readAllLines(myPath1, StandardCharsets.UTF_8);
		
		/**
		 * parse all lines using the lex method of the Lexer class (calling it repeatedly). 
		 * If lex throws an exception,catch the exception, print there was an exception. 
		 */
		try {
			Lexer a = new Lexer();
			try {
		      for(String lin : lines1) {
		    	  a.Lex(lin);
		      }
		      for (int i = 0; i < a.getToken().size(); i++) {
		    	  System.out.println(a.getToken().get(i).toString());
		      }
			  Parser p = new Parser(a.getToken());
		      ProgramNode program = (ProgramNode) p.parse();
		      SemanticAnalysis s = new SemanticAnalysis();
		      s.checkAssignments(program);
		      Interpreter interpreter = new Interpreter();
		      interpreter.interpretFunction(p.function());
			}catch(SyntaxErrorException e) {
				System.out.println("There was an error in line " + a.getLineNumber() + " " + e.getMessage());
			}
		      for(Token tokens : a.getToken()) {
				if(tokens.getType() == Token.tokenType.ENDOFLINE) {
					System.out.println(Token.tokenType.ENDOFLINE);
				}else
					System.out.print(tokens.toString());
				
			}
		}catch(Exception e) {
			System.out.println("There was an exception: " + e.getMessage());
		}
	}
	
}
