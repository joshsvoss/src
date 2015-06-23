package scopes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import types.InvalidValueException;

public class IfWhile extends Scope{
	
	private Pattern legalConditions = Pattern.compile("true|false|([a-zA-Z_]{1}+\\w*)|(-?+[0-9]++(\\.{1}+[0-9]+)?)");
	private String booleanOperatorSeparator = "\\s*([|][|]){1}\\s*|\\s*([&][&]){1}\\s*";
	private String[] conditions;

	public IfWhile(String name, String conditions, int depth) throws InvalidValueException {
		super(name, conditions, depth);
		this.conditions = separateConditions(conditions);
		for(int i = 0; i < this.conditions.length; i++){
			checkParamLogic(this.conditions[i]);
		}
		
	}
	
	private String[] separateConditions(String conditions){
		return conditions.split(booleanOperatorSeparator);
	}

	// TODO is this necessary?
	public void updateLogic() {
		// TODO Auto-generated method stub
		
	}
	
	public int getDepth(){
		return this.depth;
	}

	@Override
	public boolean checkParamLogic(String condition) throws InvalidValueException {
		Matcher legalConditionMatch = legalConditions.matcher(condition);
		if(legalConditionMatch.matches()){
			return true;
		}
		throw new InvalidValueException("Invalid condition for an if/while block.");
	}

}
