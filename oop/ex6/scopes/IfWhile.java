package oop.ex6.scopes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex6.main.Parser;
import oop.ex6.types.Int;
import oop.ex6.types.InvalidValueException;
import oop.ex6.types.Type;
import oop.ex6.types.Double;
import oop.ex6.types.Boolean;

/** This class performs static syntax validation checks on an if or while statement in Sjava document.  
 * 
 * If an error is found, the appropriate SJavacException will be thrown.  
 * @author Joshua Voss, shanam.
 *
 */
public class IfWhile {
	

	
	private static final Pattern BOOLEAN_INT_DOUBLE = Pattern.compile("\\s*(true|false|(-?+[0-9]++(\\.{1}+"
			+ "[0-9]+)?))\\s*");
	private static final Pattern LEGAL_VAR_NAMES = Pattern.compile("\\s*("+Parser.POSSIBLE_VAR_NAMES+")"
			+ "\\s*");
	private static final String BOOLEAN_OPERATOR_SEPARATOR = "\\s*([|][|]){1}\\s*|\\s*([&][&]){1}\\s*";


	public static void checkLogic(String name, String conditions, int depth) throws InvalidValueException,
	ParameterNotInitializedException {
		
		String[] sepConditions = separateConditions(conditions);
		for(int i = 0; i < sepConditions.length; i++){
			checkParamLogic(sepConditions[i], depth);
		}
		
	}
	
	private static String[] separateConditions(String conditions){
		return conditions.split(BOOLEAN_OPERATOR_SEPARATOR);
	}

	

	public static boolean checkParamLogic(String condition, int depth) throws InvalidValueException,
	ParameterNotInitializedException {
		Matcher boolenaIntOrDoubleMatch = BOOLEAN_INT_DOUBLE.matcher(condition);
		Matcher legalVarNameMatch = LEGAL_VAR_NAMES.matcher(condition);
		if(boolenaIntOrDoubleMatch.matches()){
			return true;
		}else if(legalVarNameMatch.matches()){
			for (int i = depth; i >= 0; i--) {

				Type conditionVar = Parser.searchSymbolTableList(condition, i);
				if(conditionVar != null){
					if((conditionVar instanceof Int) || (conditionVar instanceof Double) || 
							(conditionVar instanceof Boolean)){
						if(!conditionVar.isInitialized()){
							throw new ParameterNotInitializedException();
						}
						return true;
						
					}
					break;
				}
			}
		}
		throw new InvalidValueException("Invalid condition for an if/while block.");
	}

}
