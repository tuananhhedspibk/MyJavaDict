package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import fieldOfDict.*;
import dictFunc.*;

public class Gui extends JFrame implements GuiInterface{
	DictFunc func = new DictFunc();

	public void buildGUI(FieldOfDict field){
		field.frame.setLayout(new GridLayout(1,3));
		field.frame.setSize(950,680);
		field.frame.setVisible(true);
		field.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initForMenu(field);
		addComponentForMenu(field);
		initForTextArea(field);
		initForButton(field);
		initForLabel(field);
		initForPanel(field);
		addComponent(field);
	}	

	public void initForMenu(FieldOfDict field){
			for(int i = 0 ; i < 20 ; i++){
				field.historyList[i] = new String("");
			}

			field.ev.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String string = e.getActionCommand();
					if(string.equals("English-VietNamese Dict")){
						for(int i = 0 ; i < 20 ; i++){
							field.historyList[i] = "";
						}
						func.setTextForArea(field);
						field.nameOfDictArea.setText("English-VietNamese Dict");
						field.dict.clear();
						field.soundexDict.clear();
						field.favorite.removeAll();
						field.nameOfDict = new String("Database/AnhViet/EV/anhviet109K.dict");
						func.addWord(field,"Database/AnhViet/EV/anhviet109K.index");
					}
				}
			});
			field.ve.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String string = e.getActionCommand();
					if(string.equals("VietNamese-English Dict")){
						for(int i = 0 ; i < 20 ; i++){
							field.historyList[i] = "";
						}
						func.setTextForArea(field);
						field.nameOfDictArea.setText("VietNamese-English Dict");
						field.dict.clear();
						field.soundexDict.clear();
						field.favorite.removeAll();
						field.nameOfDict = new String("Database/VietAnh/VE/vietanh.dict");
						func.addWord(field,"Database/VietAnh/VE/vietanh.index");
					}
				}
			});
			field.fv.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String string = e.getActionCommand();
					if(string.equals("French-VietNamese Dict")){
						for(int i = 0 ; i < 20 ; i++){
							field.historyList[i] = "";
						}
						func.setTextForArea(field);
						field.nameOfDictArea.setText("French-VietNamese Dict");
						field.dict.clear();
						field.soundexDict.clear();
						field.favorite.removeAll();
						field.nameOfDict = new String("Database/PhapViet/FV/phapviet.dict");
						func.addWord(field,"Database/PhapViet/FV/phapviet.index");
					}
				}
			});
			field.vf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String string = e.getActionCommand();
					if(string.equals("VietNamese-French Dict")){
						for(int i = 0 ; i < 20 ; i++){
							field.historyList[i] = "";
						}
						func.setTextForArea(field);
						field.nameOfDictArea.setText("VietNamese-French Dict");
						field.dict.clear();
						field.soundexDict.clear();
						field.favorite.removeAll();
						field.nameOfDict = new String("Database/VietPhap/FV/vietphap.dict");
						func.addWord(field,"Database/VietPhap/FV/vietphap.index");
					}
				}
			});
			field.vv.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String string = e.getActionCommand();
					if(string.equals("VietNamese-VietNamese Dict")){
						for(int i = 0 ; i < 20 ; i++){
							field.historyList[i] = "";
						}
						func.setTextForArea(field);
						field.nameOfDictArea.setText("VietNamese-VietNamese Dict");
						field.dict.clear();
						field.soundexDict.clear();
						field.favorite.removeAll();
						field.nameOfDict = new String("Database/VietViet/VV/vv30K.dict");
						func.addWord(field,"Database/VietViet/VV/vv30K.index");
					}
				}
			});
			field.history.addMenuListener(new MenuListener(){
				public void menuSelected(MenuEvent me) {
					try{
						int i,counter = 0;
						field.history.removeAll();

						for(i = 0 ; i < 20 ; i++){
							if(!field.historyList[i].equals("")){
								counter ++;
							}
						}

						JMenuItem menuItem[] = new JMenuItem[counter];
						for(i = 0; i < counter ; i ++){
							if(field.dict.containsKey(field.historyList[i])){ 
								menuItem[i] = new JMenuItem(field.historyList[i]);
								field.history.add(menuItem[i]); 
								String text = menuItem[i].getText();
								menuItem[i].addActionListener(new ActionListener(){
									public void actionPerformed(ActionEvent e){
										field.inputArea.setText(text);
										func.searchWord(field);
									}
								});
							}
						}
					}
					catch(Exception ex){
						System.out.println(ex.getMessage());
					}
				}
				
				public void menuDeselected(MenuEvent me) {

				}
				
				public void menuCanceled(MenuEvent me) {

				}
			});
			field.favorite.addMenuListener(new MenuListener(){
				public void menuSelected(MenuEvent me){
					JMenuItem temp;
					int i,counter = 0,numberItem = field.favorite.getItemCount();
					String buffer[];
					for(i = 0 ; i < numberItem ; i++){
						temp = field.favorite.getItem(i);
						if(field.dict.containsKey(temp.getText())){
							counter ++;
						}
					}
					buffer = new String[counter];
					counter = 0;
					for(i = 0 ; i < numberItem ; i++){
						temp = field.favorite.getItem(i);
						String text = temp.getText();
						if(field.dict.containsKey(text)){
							buffer[counter++] = new String(text);
						}
					}
					field.favorite.removeAll();
					for(i = 0 ; i < counter ; i++){
						JMenuItem item = new JMenuItem(buffer[i]);
						field.favorite.add(item);
						item.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								field.inputArea.setText(item.getText());
								func.searchWord(field);
							}
						});
					}
				}
				
				public void menuDeselected(MenuEvent me) {

				}
				
				public void menuCanceled(MenuEvent me) {

				}
			});
			field.favoriteWord.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					func.copyWord(field);
					if(!field.subStr.equals("")){
						if(!field.dict.isEmpty()){
							if(field.dict.containsKey(field.subStr)){
								int numberItem = field.favorite.getItemCount(),counter = 0; 
								JMenuItem item;
								for(int i = 0 ; i < numberItem ; i++){
									item = field.favorite.getItem(i);
									String text = item.getText();
									if(field.subStr.equals(text)){
										counter ++;
									}
								}
								if(counter == 0){
									item = new JMenuItem(field.subStr);
									field.favorite.add(item);
								}
							}
							else{
								JOptionPane.showMessageDialog(null,"Word Isn't Exist.","Announce",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else{
							field.resultArea.setText("Dict is null. Can't use.");
						}
					}
				}
			});
			field.copy.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					func.copyWord(field);
				}
			});
			field.paste.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(field.turn == 1){
						field.inputArea.append(field.subStr);
					}
					else if(field.turn ==2){
						field.resultArea.append(field.subStr);
					}
					else if(field.turn == 3){
						field.soundexArea.append(field.subStr);
					}
				}
			});
	}
	public void addComponentForMenu(FieldOfDict field){
		field.setData.add(field.ev);
		field.setData.add(field.ve);
		field.setData.add(field.fv);
		field.setData.add(field.vf);
		field.setData.add(field.vv);

		field.popupMenu.add(field.copy);
		field.popupMenu.add(field.paste);
		field.popupMenu.add(field.favoriteWord);

		field.service.add(field.history);
		field.service.add(field.favorite);

		field.menuBar.add(field.setData);
		field.menuBar.add(field.service);
		field.frame.setJMenuBar(field.menuBar);
	}

	public void initForTextArea(FieldOfDict field){
		field.inputArea.setLineWrap(true);
		field.nameOfDictArea.setLineWrap(true);

		field.resultArea.setFont(field.font);
		field.soundexArea.setFont(field.font);
		field.inputArea.setFont(field.font);
		field.nameOfDictArea.setFont(field.font);
	
		field.resultArea.setEditable(false);
		field.soundexArea.setEditable(false);
		field.nameOfDictArea.setEditable(false); 

		field.resultArea.setBorder(BorderFactory.createEtchedBorder());
		field.inputArea.setBorder(BorderFactory.createEtchedBorder());
		field.soundexArea.setBorder(BorderFactory.createEtchedBorder());
		field.nameOfDictArea.setBorder(BorderFactory.createEtchedBorder());

		field.resultArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionStart = field.resultArea.getCaretPosition();
				}
			}

			public  void mouseReleased(MouseEvent me){
				field.flag = 2;
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionFinish = field.resultArea.getCaretPosition();
				}
			}

			public void mouseExited(MouseEvent me){
				field.turn = 2;
			}
		});
		field.inputArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionStart = field.inputArea.getCaretPosition();
				}
			}

			public  void mouseReleased(MouseEvent me){
				field.flag = 1;
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionFinish = field.inputArea.getCaretPosition();
				}
			}

			public void mouseExited(MouseEvent me){
				field.turn = 1;
			}
		});
		field.soundexArea.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent me){
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionStart = field.soundexArea.getCaretPosition();
				}
			}

			public  void mouseReleased(MouseEvent me){
				field.flag = 3;
				if(me.isPopupTrigger()){
					field.popupMenu.show(me.getComponent(),me.getX(),me.getY());
				}
				else{
					field.currentCaretPositionFinish = field.soundexArea.getCaretPosition();
				}
			}
			public void mouseExited(MouseEvent me){
				field.turn = 3;
			}
		});
	}

	public void initForButton(FieldOfDict field){
		field.buttonAdd.setBackground(Color.WHITE);
		field.buttonDelete.setBackground(Color.WHITE);
		field.buttonSearch.setBackground(Color.WHITE);
		field.buttonHelp.setBackground(Color.WHITE);
		field.buttonExit.setBackground(Color.WHITE);
		field.buttonInfo.setBackground(Color.WHITE);

		field.buttonAdd.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				func.addNewWord(field);
			}
		});
		field.buttonSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				func.searchWord(field);
			}
		});
		field.buttonDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				func.deleteWord(field);
			}
		});
		field.buttonInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				func.infor();
			}
		});
		field.buttonHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				func.helpFunc(field);
			}
		});
		field.buttonExit.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				System.exit(1);
			}
		});
	}

	public void initForLabel(FieldOfDict field){
		field.labelSearch.setFont(field.font);
		field.labelAdd.setFont(field.font);
		field.labelDelete.setFont(field.font);
		field.labelHelp.setFont(field.font);
		field.labelInfo.setFont(field.font);
		field.labelExit.setFont(field.font);

		field.labelAdd.setBackground(Color.WHITE);
		field.labelSearch.setBackground(Color.WHITE);
		field.labelDelete.setBackground(Color.WHITE);
		field.labelInfo.setBackground(Color.WHITE);
		field.labelExit.setBackground(Color.WHITE);
		field.labelHelp.setBackground(Color.WHITE);

		field.labelAdd.setBorder(BorderFactory.createEtchedBorder());
		field.labelHelp.setBorder(BorderFactory.createEtchedBorder());
		field.labelExit.setBorder(BorderFactory.createEtchedBorder());
		field.labelDelete.setBorder(BorderFactory.createEtchedBorder());
		field.labelInfo.setBorder(BorderFactory.createEtchedBorder());
		field.labelSearch.setBorder(BorderFactory.createEtchedBorder());
	}

	public void initForPanel(FieldOfDict field){
		field.left.setBorder(BorderFactory.createEtchedBorder());
		field.center.setBorder(BorderFactory.createEtchedBorder());
		field.right.setBorder(BorderFactory.createEtchedBorder());

		field.center.setLayout(new GridLayout(6,1));
		field.center1.setLayout(new BoxLayout(field.center1,BoxLayout.X_AXIS));
		field.center2.setLayout(new BoxLayout(field.center2,BoxLayout.X_AXIS));
		field.center3.setLayout(new BoxLayout(field.center3,BoxLayout.X_AXIS));
		field.center4.setLayout(new BoxLayout(field.center4,BoxLayout.X_AXIS));
		field.center5.setLayout(new BoxLayout(field.center5,BoxLayout.X_AXIS));
		field.center6.setLayout(new BoxLayout(field.center6,BoxLayout.X_AXIS));
	}

	public void addComponent(FieldOfDict field){
		field.center.add(field.center1);
		field.center.add(field.center2);
		field.center.add(field.center3);
		field.center.add(field.center4);
		field.center.add(field.center5);
		field.center.add(field.center6);		

		field.left.add(field.scrollPaneInput);
		field.left.add(field.scrollPaneResult);
		field.right.add(field.scrollPaneName);
		field.right.add(field.scrollPaneSoundex);
		
		field.center1.add(field.buttonSearch);
		field.center1.add(Box.createHorizontalGlue());
		field.center1.add(field.labelSearch);
		field.center2.add(field.buttonAdd);
		field.center2.add(Box.createHorizontalGlue());
		field.center2.add(field.labelAdd);
		field.center3.add(field.buttonDelete);
		field.center3.add(Box.createHorizontalGlue());
		field.center3.add(field.labelDelete);
		field.center4.add(field.buttonInfo);
		field.center4.add(Box.createHorizontalGlue());
		field.center4.add(field.labelInfo);
		field.center5.add(field.buttonHelp);
		field.center5.add(Box.createHorizontalGlue());
		field.center5.add(field.labelHelp);
		field.center6.add(field.buttonExit);
		field.center6.add(Box.createHorizontalGlue());
		field.center6.add(field.labelExit);

		field.frame.add(field.left);
		field.frame.add(field.center);
		field.frame.add(field.right);
	}
}