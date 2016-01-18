package dictFunc;

import javax.swing.*;
import java.util.*;
import java.awt.*;

import fieldOfDict.*;

public interface DictFuncInterface{
	public void addWord(FieldOfDict field,String fileIndex);
	public void setTextForArea(FieldOfDict field);
	public void checkSoundex(String soundexSound,String word,FieldOfDict field);
	public void searchWord(FieldOfDict field);
	public void helpFunc(FieldOfDict field);
	public void deleteWord(FieldOfDict field);
	public void infor();
	public void addNewWord(FieldOfDict field);
	public void addWordToHistory(FieldOfDict field,String str);
	public void copyWord(FieldOfDict field);
}