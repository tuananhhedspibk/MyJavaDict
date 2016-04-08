public interface AuxilaryInterface{
	public int getDemicalValue (String str);	// --- Converte from 64(base) to 10(base) ---
	public String get64Value (long value);		// --- Converte from 10(base) to 64(base) ---
	public String soundex(String s);			// --- Create soundex string ---
}