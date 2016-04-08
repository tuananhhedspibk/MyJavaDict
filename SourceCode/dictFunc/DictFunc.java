package dictFunc;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

import auxilary.*;
import fieldOfDict.*;

public class DictFunc implements DictFuncInterface{
	Auxilary aux = new Auxilary();
	public void addWord(FieldOfDict field,String fileIndex){
		try{
			String valueOfSoundex = new String();
			BufferedReader inFile = new BufferedReader(new FileReader(fileIndex));
			String text,word = new String(),startOfMeaning = new String(),longOfMeaning = new String(),meaning = new String(),soundexSound = new String();
			int i,i1,turn,len;

			while((text = inFile.readLine()) != null){
				i = 0;
				i1 = 0;
				turn = 1;
				len = text.length();
				while(i < len){
					if(text.charAt(i) != '\t' && i < len - 1){
						i++;
					}
					else{
						if(turn == 1){
							word = text.substring(0,i);
							i++;
							i1 = i;
						}
						else if(turn == 2){
							startOfMeaning = text.substring(i1,i);
							i++;
							i1 = i;
						}
						else{
							longOfMeaning = text.substring(i1,i + 1);
							i++;
						}
						turn ++;
					}
				}			
				meaning = startOfMeaning + " " + longOfMeaning;
				soundexSound = aux.soundex(word);
				field.dict.put(word,meaning);
				checkSoundex(soundexSound,word,field);
			}
			inFile.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}

	public void setTextForArea(FieldOfDict field){			// --- Clear text of TextArea ---
		field.resultArea.setText("");
		field.soundexArea.setText("");
		field.inputArea.setText("");
	}

	public void checkSoundex(String soundexSound,String word,FieldOfDict field){			// --- Push data to SoundexDict ---
		String valueOfSoundex = new String();				// --- Store set of words have same soundex ---
		if(field.soundexDict.containsKey(soundexSound)){
			valueOfSoundex = field.soundexDict.get(soundexSound).toString();
			valueOfSoundex = valueOfSoundex + "\n" + word;
			field.soundexDict.remove(soundexSound);
			field.soundexDict.put(soundexSound,valueOfSoundex);
		}
		else{
			field.soundexDict.put(soundexSound,word);
		}
	}

	public void searchWord(FieldOfDict field){
		if(!field.dict.isEmpty()){ 
			try{
				String str = field.inputArea.getText();
				String startOfMeaning = new String(),longOfMeaning = new String();
				String meaning = new String();
				String soundexSound = new String();
				if(field.dict.containsKey(str)){
					addWordToHistory(field,str);
					field.soundexArea.setText("");
					meaning = field.dict.get(str).toString();
					if(meaning.charAt(meaning.length() - 1) == '$'){
						StringBuffer buffer = new StringBuffer();
						for(int i = 0 ; i < meaning.length() - 1 ; i++){
							buffer.insert(i,meaning.charAt(i));
						}
						field.resultArea.setText(buffer.toString());
					}
					else{ 
						int i = 0,i1 = 0;
						int len = meaning.length();
						while(i < len){
							if(meaning.charAt(i) != ' '){
								i++;
							}
							else{
								startOfMeaning = meaning.substring(0,i);
								i++;
								longOfMeaning = meaning.substring(i,len);
							}
						}
						i1 = aux.getDemicalValue(startOfMeaning);
						len = aux.getDemicalValue(longOfMeaning);

						try{
							RandomAccessFile reader = new RandomAccessFile(field.nameOfDict,"r");
							byte cbuf[] = new byte[5000];
							reader.seek(i1);
							reader.read(cbuf,0,len);
							String mean = new String(cbuf);
							field.resultArea.setText(mean);
							reader.close();
						}
						catch(Exception exp){
							System.out.println(exp.getMessage());	
						}
					}
				}
				else{
					String out = new String(""); 
					field.resultArea.setText("");
					soundexSound = aux.soundex(str);
					if(field.soundexDict.containsKey(soundexSound)){ 
						field.soundexArea.setText(field.soundexDict.get(aux.soundex(str)).toString());
					}
					else{
						field.soundexArea.setText("");
					}
					for(Map.Entry<String,String> entry : field.dict.entrySet()) {
						String key = entry.getKey();
						if(key.startsWith(str)){
							out = out + key + "\n";
						}
					}
					field.resultArea.setText(out);
				}
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		else{
			field.resultArea.setText("Dict is null. Can't use.");
		}
	}

	public void helpFunc(FieldOfDict field){				// --- Display help 's frame ---
		JTextArea helpText = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(helpText);
		helpText.setEditable(false);
		helpText.setFont(field.font);
		try{
			StringBuffer buffer = new StringBuffer();
			String text;
			BufferedReader inReader = new BufferedReader(new FileReader("Database/helpInfo.dat"));
			while((text = inReader.readLine()) != null){
				buffer.append(text + "\n");
			}
			inReader.close();
			helpText.setText(buffer.toString());
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		field.helpFrame.add(scrollPane);
		field.helpFrame.setSize(900,600);
		field.helpFrame.setVisible(true);
	}

	public void deleteWord(FieldOfDict field){
		if(!field.dict.isEmpty()){
			String word = JOptionPane.showInputDialog(null,"Input The Word That You Want To Delete","Delete Word",JOptionPane.QUESTION_MESSAGE);
			if(word == null){
				JOptionPane.showMessageDialog(null,"Delete Word Unsuccessful Or Doesn't Delete","Announce",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				if(field.dict.containsKey(word)){
					field.dict.remove(word);
					JOptionPane.showMessageDialog(null,"Delete Word Successfully","Announce",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null,"Delete Word Unsuccessfully Or Doesn't Delete","Announce",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			setTextForArea(field);
		}
		else{
			field.resultArea.setText("Dict is null. Can't use.");
		}
	}

	public void infor(){						// --- Display infor about app ---
		Icon icon = new ImageIcon("Database/Picture/dictionary.png");
		String msg;
		msg = "This Is My Dictionary Application, Designed By Java Language\nWritten By AnhTT - HUST\nCopyright (c) 2015\nContact Me At :tuananhhedspibk@gmail.com For More Details";
		JOptionPane.showMessageDialog(null,msg,"Information Of Dict",JOptionPane.INFORMATION_MESSAGE,icon);
	}

	public void addNewWord(FieldOfDict field){
		if(!field.dict.isEmpty()){
			String word = JOptionPane.showInputDialog(null,"Input The Word That You Want To Add","Add New Word",JOptionPane.QUESTION_MESSAGE);
			if(word == null || word.equals("")){
				JOptionPane.showMessageDialog(null,"Add Word Unsuccessful Or Doesn't Add","Announce",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				if(field.dict.containsKey(word)){
					JOptionPane.showMessageDialog(null,"This Word Has Existed","Announce",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					String meaning = JOptionPane.showInputDialog(null,"Input Meaning of Word","Add New Word",JOptionPane.QUESTION_MESSAGE);
					if(meaning == null || meaning.equals("")){
						JOptionPane.showMessageDialog(null,"Add Word Unsuccessful Or Doesn't Add","Announce",JOptionPane.INFORMATION_MESSAGE);
					}
					else{
							try{
								String mean = new String();
								mean = "@" + word + "\n-" + meaning + "$";
								field.dict.put(word,mean);
								checkSoundex(aux.soundex(word),word,field);
								JOptionPane.showMessageDialog(null,"Add Word Successful","Announce",JOptionPane.INFORMATION_MESSAGE);
							}
							catch(Exception exc){
								System.out.println(exc.getMessage());
							}
						}
				}
			}
			setTextForArea(field);
		}
		else{
			field.resultArea.setText("Dict is null. Can't use.");
		}
	}

	public void addWordToHistory(FieldOfDict field,String str){					// --- History will store list words have been searched ---
		int i;
		for(i = 0 ; i < 20 ; i++){
			if(field.historyList[i].equals(str)){
				for(int j = i ; j >= 1 ; j--){
					field.historyList[j] = field.historyList[j - 1];
				}
				field.historyList[0] = str;
				return  ;
			}
		}
		for(i = 19 ; i >= 1 ; i--){
			field.historyList[i] = field.historyList[i - 1];
		}
		field.historyList[0] = str;
	}

	public void copyWord(FieldOfDict field){						// --- Copy func ---
		String str = new String("");
		field.subStr = "";
		if(field.flag != 0){ 
			if(field.flag == 1){ 
				str = field.inputArea.getText();
			}
			else if(field.flag == 2){
				str = field.resultArea.getText();
			}
			else if(field.flag == 3){
				str = field.soundexArea.getText();
			}
			if(!str.equals(""))
			{
				if(field.currentCaretPositionFinish <= field.currentCaretPositionStart){
					field.subStr = str.substring(field.currentCaretPositionFinish,field.currentCaretPositionStart);
				}
				else{
					field.subStr = str.substring(field.currentCaretPositionStart,field.currentCaretPositionFinish);
				}
			}
		}
		field.flag = 0;
	}
}