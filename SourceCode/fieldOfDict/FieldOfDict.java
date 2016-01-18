package fieldOfDict;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class FieldOfDict{
	public JMenuBar menuBar = new JMenuBar();
	public JMenu setData = new JMenu("Set Data");
	public JMenu service = new JMenu("Service");
	public JMenu history = new JMenu("History");
	public JMenu favorite = new JMenu("Favorite");
	public JMenuItem copy = new JMenuItem("Copy");
	public JMenuItem paste = new JMenuItem("Paste");
	public JMenuItem favoriteWord = new JMenuItem("Add To Favorite");
	public JMenuItem ev = new JMenuItem("English-VietNamese Dict");
	public JMenuItem ve = new JMenuItem("VietNamese-English Dict");
	public JMenuItem fv = new JMenuItem("French-VietNamese Dict");
	public JMenuItem vf = new JMenuItem("VietNamese-French Dict");
	public JMenuItem vv = new JMenuItem("VietNamese-VietNamese Dict");

	public JButton	buttonAdd = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/pencil.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));
	public JButton	buttonSearch = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/magnifier.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));
	public JButton	buttonDelete = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/eraser.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));
	public JButton	buttonHelp = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/float.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));		
	public JButton	buttonExit = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/exit.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));		
	public JButton	buttonInfo = new JButton(new ImageIcon(((new ImageIcon("Database/Picture/info.png").getImage().getScaledInstance(46,46,java.awt.Image.SCALE_SMOOTH)))));		
	
	public Font font = new Font("Times new Roman",Font.BOLD,14);

	public JFrame helpFrame = new JFrame("Help");
	public JFrame frame = new JFrame("Dictionary");

	public JTextArea resultArea = new JTextArea("",32,20);
	public JTextArea soundexArea = new JTextArea("",32,20);
	public JTextArea inputArea = new JTextArea("",2,20);
	public JTextArea nameOfDictArea = new JTextArea("",2,20);

	public JScrollPane	scrollPaneInput = new JScrollPane(inputArea);
	public JScrollPane scrollPaneName = new JScrollPane(nameOfDictArea);
	public JScrollPane scrollPaneResult = new JScrollPane(resultArea);
	public JScrollPane scrollPaneSoundex = new JScrollPane(soundexArea);

	public JPanel left = new JPanel();
	public JPanel center = new JPanel();
	public JPanel right = new JPanel();
	public JPanel center1 = new JPanel();
	public JPanel center2 = new JPanel();
	public JPanel center3 = new JPanel();
	public JPanel center4 = new JPanel();
	public JPanel center5 = new JPanel();
	public JPanel center6 = new JPanel();


	public JLabel labelAdd = new JLabel("Add Word");
	public JLabel labelSearch = new JLabel("Search Word");
	public JLabel labelDelete = new JLabel("Delete Word");
	public JLabel labelHelp = new JLabel("Help");
	public JLabel labelExit = new JLabel("Exit Dict");
	public JLabel labelInfo = new JLabel("Infor About Dict");

	public TreeMap<String,String> dict = new TreeMap(),soundexDict = new TreeMap();
	
	public String nameOfDict = new String();
	public String subStr = new String();
	public String historyList[] = new String[20];

	public JPopupMenu popupMenu = new JPopupMenu();

	public int turn = 0,flag = 0,currentCaretPositionStart,currentCaretPositionFinish;
}