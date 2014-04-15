package gamecontrollers.rules;

/**
 * //TODO [Nathan, Kevin, Will] [Chris]
 */
public abstract class Rule {
	boolean isValid;
	public abstract void update();
	public abstract String getErrorMessage();

	public boolean getValidity() {return isValid;}
	protected void setValidity(boolean b) {isValid = b;}
}