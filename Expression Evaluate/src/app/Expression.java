package app;

import java.io.*;
import java.util.*;

import structures.Stack;


public class Expression {

	public static String delims = " \t*+-/()[]";
			
    /**
     * Complete this method
     * 
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    		
    		expr = expr.trim();
    		expr = expr.replaceAll("\\s+", "");
    		expr = expr.replaceAll("\\t+", "");
    		
    		StringTokenizer st = new StringTokenizer(expr, " \t*+-/()]" + "1234567890");
    		
    		while (st.hasMoreTokens()) {
    			
    			String sub = st.nextToken();
    			
    			while (true) {
    				if (sub.contains("[")) {
        				int temp = sub.indexOf("[");
        				Array a = new Array(sub.substring(0, temp));
        				if (arrays.contains(a)) { break; }
        				arrays.add(a);	
        				//	System.out.println("Array: " + a);
        				try {
        					sub = sub.substring(temp + 1);
        					if (sub.equals("")) { break; }
        				}
        				catch (NullPointerException e) { break; }
            		}
            		else {
            			Variable v = new Variable(sub);
            			if (vars.contains(v)) { break; }
            			vars.add(v);		
            			//	System.out.println("Variable: " + sub);
            			break;
            		}
    			}
    		}
    }
    

	/**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    
    /**
     * Complete this method
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    		
    		// getting rid of whitespace in expression
    		expr = expr.trim();
		expr = expr.replaceAll("\\s+", "");
		expr = expr.replaceAll("\\t+", "");
		
		float answer = 0;
		
		// if expression has brackets
		while (contains(expr,"[]")) {
			StringTokenizer st = new StringTokenizer(expr, " \t*+-/()]");
			String temp = "", name = "";
			while (st.hasMoreTokens()) {
				temp = st.nextToken();
				try {
					name = temp.substring(0, temp.indexOf("["));
					break;
				}
				catch (IndexOutOfBoundsException e) { continue; }
			}	// used to find the name of the array
			
			int begin = expr.indexOf("["), end = 0, count = 0;
			for (int i = begin; count >= 0; i++) {
				if (expr.charAt(i) == ']') {
					count -= 1;
					if (count == 0) { end = i; break; }		// found final "]"
				}
				else if (expr.charAt(i) == '[') {
					count += 1;
				}
			}
			// recursion on everything inside brackets
			float x = evaluate(expr.substring(begin+1, end),vars,arrays);
			for (int i = 0; i < arrays.size(); i++) {
				if (arrays.get(i).name.equals(name)) {
					x = arrays.get(i).values[(int) x];
				}
			}	// used to obtain array[x] value
			expr = expr.substring(0,begin - name.length())	// concatenating out array[x]
					+ Float.toString(x) + expr.substring(end+1);	
			//	System.out.println("Expression after bracket loop: "+expr);
		}
		
		// if expression has parenthesis
		while (contains(expr,"()")) {
			int begin = expr.indexOf("("), end = 0, count = 0;
			for (int i = begin; count >= 0; i++) {
				if (expr.charAt(i) == ')') {
					count -= 1;
					if (count == 0) { end = i; break; }		// found final ")"
				}
				else if (expr.charAt(i) == '(') {
					count += 1;
				}
			}
			// recursion on everything inside parenthesis
			float x = evaluate(expr.substring(begin+1, end),vars,arrays);	
			expr = expr.substring(0,begin) 					// concatenating out "()"
					+ Float.toString(x) + expr.substring(end+1);	
			//	System.out.println("Expression after parenthesis loop: "+expr);
		}
		
		/**** RECURSIVE BASE CASE ****//* expression is either a digit or a variable */
		if (!contains(expr,delims) || negativeDigit(expr)) {
			//	System.out.println("Base case: "+expr);
			try { return Float.parseFloat(expr); }			// if digit
			catch (NumberFormatException e) {
				for (int i = 0; i < vars.size(); i++) {		// if variable
					if (vars.get(i).name.equals(expr)) {
						return vars.get(i).value;
					}
				}
			}
		}
		
		// simple expression with only basic computations
		else if (contains(expr,"*+-/")) {
			expr = doubleNegatives(expr);
			//	System.out.println("Basic computation start: " + expr);
			Stack<Float> opposite = new Stack<Float>();				
			StringTokenizer st = new StringTokenizer(expr, " \t*+/()[]");
			while(st.hasMoreTokens()) {
				String tem = st.nextToken();
				if (contains(tem,"-") && !negativeDigit(tem)){		// account for "6*-7"
					StringTokenizer stt = new StringTokenizer(tem, delims);
					while(stt.hasMoreTokens()) {
						opposite.push(evaluate(stt.nextToken(),vars,arrays));
					} 
				} else {
					opposite.push(evaluate(tem,vars,arrays));
				}
			}	// load variables and numbers into stack
			Stack<Float> Numbers = new Stack<Float>();		
			while(!opposite.isEmpty()) {
				Numbers.push(opposite.pop());
			}	// reverse stack order into "Numbers"
			
			Stack<Character> opp = new Stack<Character>();
			for (int i = 0; i < expr.length(); i++) {
	    			if (expr.charAt(i) == '+' || 
	    					expr.charAt(i) == '*'|| expr.charAt(i) == '/'){
	    				opp.push( expr.charAt(i) );
	    			}
	    			try {
	    				if (expr.charAt(i) == '-' 	//"-" is minus operator and ! negative operator
		    					&& hasNegativeDigit(expr.substring(i-1)) == false) {
		    				opp.push( expr.charAt(i) );
		    			}
	    			}
	    			catch (StringIndexOutOfBoundsException e) {
	    				continue;				// negative digit is the first thing in string
	    			}
			}	// load operator symbols into stack
			Stack<Character> Operations = new Stack<Character>();
			while(!opp.isEmpty()) {
				Operations.push(opp.pop());
			}	// reverse stack order into "Operations"
			
			while (!Numbers.isEmpty()) { 
				char op = Operations.pop();
				try {
					char check = Operations.peek();
					if (hasPriority(op, check)) {		// check priority of math operations
						float a = Numbers.pop(), b = Numbers.pop();
						answer = math(a,b,op);			// computation
						if (!Operations.isEmpty()) {
							Numbers.push(answer);
						} 
					}
					else {		// if top item in "Operations" has priority
						float temp = Numbers.pop(); 
						char tempOp = op; op = Operations.pop();
						float a = Numbers.pop(), b = Numbers.pop();
						answer = math(a,b,op);			// computation
						Numbers.push(answer); Numbers.push(temp);
						Operations.push(tempOp);			// placing priority answer into stack	
					}
				}
				catch (NoSuchElementException e) {		//"Operations" is empty, final operation
					float a = Numbers.pop(),b = Numbers.pop();
					answer = math(a,b,op); 				// computation
				}
			}
		}
		//	System.out.println("Method completion");
		return answer;
    }

    /**
     * hasPriority method to find priority
     * @return true if a has priority, false if b does
     */
    private static boolean hasPriority(char a, char b) {
    		if (a == '+' || a == '-' ) {
    			if (b == '*' || b == '/') {
    				return false;
    			}
    		} return true;
    }
    
    /**
     * math method to perform math
     * @throws NullPointerException if @param op is not a math operator
     * @return a+b, a-b, a*b, a/b based on @param op
     */
    private static float math(float a, float b, char op) {
    		switch (op) {
    			case '+': return a + b;
    			case '-': return a - b;
    			case '*': return a * b;
    			case '/': return a / b;
    		} throw new NullPointerException("math failure");		// should never happen
    }
    
    /**
     * contains method to check if @param regex is in @param expr
     * @return boolean
     */
    private static boolean contains(String expr, String regex) {
	    	regex = regex.replaceAll("\\s+", "").trim();
    		char[] arr = regex.toCharArray();
    		for (int i = 0; i < arr.length; i++) {
    			if (expr.contains(Character.toString(arr[i]))) {
    				return true;
    			}
    		} return false;
    }
    
    /**
     * double negatives method looking for "--" "-+" "+-"
     * @return string after searching for double negatives
     */
    private static String doubleNegatives(String expr) {
    		int nn = expr.indexOf("--");
    		while (nn > 0) { 
    			expr = expr.substring(0, nn) + "+" + expr.substring(nn+2);
    			nn = expr.indexOf("--", nn+1);
    		}
    		int np = expr.indexOf("-+");		// should never happen
    		while (np > 0) {
    			expr = expr.substring(0, np) + "-" + expr.substring(np+2);
    			np = expr.indexOf("-+", np+1);
    		}
    		int pn = expr.indexOf("+-");
    		while (pn > 0) {
    			expr = expr.substring(0, pn) + "-" + expr.substring(pn+2);
    			pn = expr.indexOf("+-", pn+1);
    		} return expr;
    }
    
    /**
     * negative digit method to check expression
     * @return true if expression INCLUDES a negative digit "-7.0"
     */
    private static boolean hasNegativeDigit(String expr) {
    		expr.replaceAll("\\s+", "").trim();
    		for (int i = 0; i < expr.length(); i++) {
    			if (expr.charAt(i) == '-') { 
    				try {
    					if (expr.charAt(i-1) == '*' || expr.charAt(i-1) == '/') {
    						StringTokenizer st = new StringTokenizer
    												(expr.substring(i+1),delims);
    						Float.parseFloat(st.nextToken());
    						return true;
    					}
    				}
    				catch (NumberFormatException e) { return false; }
    				catch (StringIndexOutOfBoundsException e) {
    					StringTokenizer st = new StringTokenizer
											(expr.substring(i+1),delims);
    					Float.parseFloat(st.nextToken());
    				}
    			} 
    		} return false;
    }
    
    /**
     * negative digit method to check expr
     * @return true if expression IS a negative digit "-7.0"
     */
    private static boolean negativeDigit(String expr) {
		expr.replaceAll("\\s+", "").trim(); 
		if (expr.charAt(0) == '-') { 
			try { 
				Float.parseFloat(expr.substring(1));
				return true;
			}
			catch (NumberFormatException e){ return false; }
		} return false;
    }
}
