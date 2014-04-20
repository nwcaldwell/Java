package gamecontrollers.rules;

import gamecontrollers.Message;

/**
 * //TODO [Nathan, Kevin, Will] [Chris]
 */
public abstract class Rule {
	boolean isValid;
	public abstract void update();
	public abstract Message getErrorMessage();

	public boolean getValidity() {return isValid;}
	protected void setValidity(boolean b) {isValid = b;}
}