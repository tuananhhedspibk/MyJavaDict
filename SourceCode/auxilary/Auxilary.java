package auxilary;

public class Auxilary{ 
	public int getDemicalValue (String str){				// --- Converte from 64(base) to 10(base) ---
		String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		int decValue = 0,pos;
		int len = str.length();
		
		for (int i = 0 ; i < len ; i++){
			pos = base64.indexOf(str.charAt(i));
			decValue += (int)Math.pow(64,len - i - 1) * pos;
		}
		return decValue;
	}

	public String get64Value (long value){					// --- Converte from 10(base) to 64(base) ---
		String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		char cbuf[] = new char[300];
		int i = 0,length;
		String reverse = "";
		String value64;
		while(value != 0){
			cbuf[i++] = base64.charAt((int)(value % 64));
			value /= 64;
		}
		value64 = new String(cbuf);
		length = value64.length();
		for (i = length - 1 ; i >= 0 ; i-- ){
			reverse = reverse + value64.charAt(i);
		}
		return reverse;
	}

	public String soundex(String s) { 
		char []x = s.toUpperCase().toCharArray();
		char firstLetter = x[0];
		// convert letters to numeric code
		for (int i = 0; i < x.length; i++) {
			switch (x[i]) {
				case 'B':
				case 'F':
				case 'P':
				case 'V':
					x[i] = '1';
					break;
				case 'C':
				case 'G':
				case 'J':
				case 'K':
				case 'Q':
				case 'S':
				case 'X':
				case 'Z':
					x[i] = '2';
					break;
				case 'D':
				case 'T':
					x[i] = '3';
					break;
				case 'L':
					x[i] = '4';
					break;
				case 'M':
				case 'N':
					x[i] = '5';
					break;
				case 'R':
					x[i] = '6';
					break;
				default:{
					x[i] = '0';
					break;
				}
			}
		}
		// remove duplicates
		String output = "" + firstLetter;
		for (int i = 1; i < x.length ; i++)
			if(x[i] != x[i-1] && x[i] != '0'){
				output += x[i];
			}
		// pad with 0's or truncate
		output = output + "0000";
		return output.substring(0, 4);
	}
}