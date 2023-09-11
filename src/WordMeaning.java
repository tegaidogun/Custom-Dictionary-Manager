import java.util.ArrayList;

public class WordMeaning
{

	String Word;
	ArrayList<String> Def = new ArrayList<>();

	public WordMeaning(String title){
		Word = title;
	}

	public void addDefinition(String def){
		Def.add(def);
	}
}