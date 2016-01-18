package gui;

import fieldOfDict.*;

public interface GuiInterface{
	public void buildGUI(FieldOfDict field);
	public void initForMenu(FieldOfDict field);
	public void addComponentForMenu(FieldOfDict field);
	public void initForTextArea(FieldOfDict field);
	public void initForButton(FieldOfDict field);
	public void initForLabel(FieldOfDict field);
	public void initForPanel(FieldOfDict field);
	public void addComponent(FieldOfDict field);
}