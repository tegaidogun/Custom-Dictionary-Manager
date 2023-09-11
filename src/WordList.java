import javax.swing.*;
import java.util.ArrayList;

public class WordList
{

	WordMeaningNode top;
	ArrayList<String> del = new ArrayList<>();
	
	public WordList(){
		top = new WordMeaningNode();
	}
	
	public WordList(String addString){
		top = new WordMeaningNode(addString);
	}

	public void insertWord(String word){
		WordMeaningNode wordNode = new WordMeaningNode(word);
		WordMeaningNode pointer = top;
		if(top == null){
			top = wordNode;
		}
		else if(word.equalsIgnoreCase(pointer.word.Word) ){

		}
		else if(word.compareTo(top.word.Word) < 0){
			wordNode.counter = top;
			top = wordNode;
		}
		else{
			boolean check = false;
			while(pointer.counter != null && word.compareTo(pointer.counter.word.Word) >= 0 ){
				pointer = pointer.counter;
				if(word.equalsIgnoreCase(pointer.word.Word)){
					check = true;
				}
			}
			if(!check) {
				wordNode.counter = pointer.counter;
				pointer.counter = wordNode;
			}
		}

	}

	public void insertDefinition(String word, String def){
		WordMeaningNode pointer = top;
		while(!word.equalsIgnoreCase(pointer.word.Word) && pointer.counter != null){
			pointer = pointer.counter;
		}
		pointer.word.addDefinition(def);
	}

	public void deleteWord(String word){
		WordMeaningNode source = top;
		if(source.word.Word.equalsIgnoreCase(word)){
			top = top.counter;
			del.add(word);
			System.out.println(del);
		}
		else{
			try {
				while (source.counter != null && !source.counter.word.Word.equalsIgnoreCase(word)) {
					source = source.counter;
				}
				if (source.counter == null) {
					JOptionPane.showMessageDialog(null, "Word not found cannot delete", "Error", JOptionPane.ERROR_MESSAGE);
				}
				del.add(word);
				System.out.println(del.toString());
				source.counter = source.counter.counter;
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}