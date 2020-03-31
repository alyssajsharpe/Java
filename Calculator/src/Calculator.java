//Alyssa Sharpe
//Lab 5    5.31.19
//Calculator.java
import java.util.StringTokenizer;
import java.util.Scanner; 
public class Calculator {

	static Stack5<String> operator = new Stack5<String>(); 
	static Stack5<String> operand = new Stack5<String>(); 
	//eval section
	//pop operator and use a switch statement between + - * / 
	
	public static void eval() {
		//pop and turn into double variable
		double rightVal = Double.parseDouble(operand.pop());
		double leftVal = Double.parseDouble(operand.pop());
		String s = operator.pop();
		double result; 
		switch(s) {
		case "+":
				result = leftVal+rightVal;
				break;
		case "-":
				result = leftVal - rightVal;
				break;
		case "/":
				result = leftVal / rightVal;
				break;		
		case "*":
				result = leftVal * rightVal;
				break;
		default:
			throw new IllegalArgumentException("invalid postfix expression");
		}//end switch
		operand.push(Double.toString(result));
		
	}//end eval
	
	public static void main (String [] args) {
		Scanner scan = new Scanner(System.in);
		boolean check = true;
		boolean unaryOk = false;
		String str;
		do {
		System.out.print("Please enter an expression to evaluate or type 'quit' to quit: ");
		str = scan.nextLine();
			if(str.equals("quit")){
				check = false; 
			}
			else {
				check = checkInput(str);
			}
			str = str.replaceAll("\\s+", "");
		if(check) {
		StringTokenizer tokenizer = new StringTokenizer(str, "+-/*()", true);	
			while(tokenizer.hasMoreTokens()){
					String token = tokenizer.nextToken();
					switch(token) {
					case "(": 
						operator.push(token);
						unaryOk = true;
						break;
					case ")": 
						unaryOk = false;
						while(!operator.peek().equals("(")) {
							eval();
							
						}
						operator.pop();	
						break;
					case "+":
						if(unaryOk) {
							operand.push(Double.toString(Double.parseDouble(tokenizer.nextToken())));
								unaryOk = false;
								break;	
						}
						if(!operator.isEmpty()) {
							while(!operator.isEmpty() && (operator.peek().equals("+") || operator.peek().equals("-") || 
									operator.peek().equals("*") || operator.peek().equals("/"))) {
									eval();
								}
						}
						try {
						operator.push(token);
						}
						catch(Exception e) {
							
						}
						break;	
					case "-":
						if(unaryOk) {
							operand.push(Double.toString(-1*Double.parseDouble(tokenizer.nextToken())));
								unaryOk = false;
								break;	
						}
						if(!operator.isEmpty()) {
							while(!operator.isEmpty() && (operator.peek().equals("+") || operator.peek().equals("-") || 
									operator.peek().equals("*") || operator.peek().equals("/"))) {
									eval();
								}
						}
						operator.push(token);
						break;			
					case "*":
					case "/":
							while(!operator.isEmpty() && (operator.peek().equals("*") || operator.peek().equals("/"))) {
							eval();
							}	
							operator.push(token);			
						break;
					default: 
						//Double.parseDouble(token);
						operand.push(token);
						unaryOk = false;
						//break; 
					}//end switch
				}//end while
			while(!operator.isEmpty()) {
				eval();
				unaryOk = false;
			}
		System.out.println("Result: " +operand.pop());
		}//end if
		}
		while(!str.equals("quit")); 
			scan.close();
	}//end main

	private static boolean checkInput(String s) {
		
		String [] array = {"+","-","/","(",")"};
		StringTokenizer test = new StringTokenizer(s,"+-/*()", true);
		boolean boolean2 = true;
		while(test.hasMoreTokens()) {
		String t = test.nextToken();
		boolean boolean1 = false; 
			for(int i = 0; i<array.length; i++) 
				if(t.equals(array[i])) {
					boolean1 = true;
					break;
				}
				if(!boolean1) {
					try {
						Double.parseDouble(t);
						boolean2 = true;
					}
					catch(NumberFormatException e) {
						boolean2 = false; 
					}
				}
				else
					boolean2 = true;
			}//end while
		return boolean2; 
		}//end method checkInput
	}//end calculator