import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;

import gui.*;
import fieldOfDict.*;

class Dictionary {
	FieldOfDict field = new FieldOfDict();
	Gui gui = new Gui();
}

public class Main{
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				dict.gui.buildGUI(dict.field);
			}
		});
	}
}