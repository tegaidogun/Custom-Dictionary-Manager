import javax.swing.*;

public class Dictionary
{
	
	public static void main(String[] args){
		WordList newdict = new WordList();
		viewthemenu(newdict);
	}
	public static void viewthemenu(WordList newDictionary){
		String allDeletedWords = "";
		for(String words : newDictionary.del){
			allDeletedWords += words + "\n";
		}
		JTextArea delwordslist = new JTextArea(allDeletedWords);
		JScrollPane scroll = new JScrollPane(delwordslist);


		String[] options = {"Dictionary","Deleted","Cancel"};
		int response1 = JOptionPane.showOptionDialog(null, "Welcome to the Dictionary App\n\n" +
				"Dictionary: shows dictionary options" +
				"\nDeleted: Shows a list of all deprecated words from the Dictionary"
				, "Dictionary Program",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options,options[0]);


		if(response1 == 0) {
			while (true) {
				viewthedictionary(newDictionary);
			}
		}
		else if(response1 == 1){
			if(newDictionary.del.size() == 0){
				int result = JOptionPane.showOptionDialog(null, "No Deleted Words", "Error", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(result == JOptionPane.OK_OPTION){viewthemenu(newDictionary); }
				else{
					viewthemenu(newDictionary);
				}
			}
			for(@SuppressWarnings("unused") String deleted_words : newDictionary.del){
				int resultD = JOptionPane.showOptionDialog(null, scroll,"Deleted Words",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(resultD == JOptionPane.OK_OPTION){
					viewthemenu(newDictionary);
				}
				else{
					System.exit(0);
				}
			}
		}
		else if (response1 == JOptionPane.CLOSED_OPTION || response1 == JOptionPane.CANCEL_OPTION){
			System.exit(0);
		}
	}

	public static void viewthedictionary(WordList dictionary0){
		String[] options2 = {"Add Word", "Delete Word", "View Dictionary", "Go Back"};
		int response2 = JOptionPane.showOptionDialog(null, "Choose an Option",
				"Edit/View Dictionary",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options2,options2[0]);
		switch(response2){
		case 0:
			try {
				String add_word = JOptionPane.showInputDialog(null, "Which word are you adding?");
				add_word.toLowerCase();
				if (!add_word.equalsIgnoreCase("") && add_word != null) {
					dictionary0.insertWord(add_word);
					String added_definition = JOptionPane.showInputDialog(null, "What is the meaning of " + add_word + "?");
					added_definition.toLowerCase();
					while(added_definition.equalsIgnoreCase("") || added_definition == null) {
						added_definition = JOptionPane.showInputDialog(null, "What is the meaning of " + add_word + "?");
					}
					dictionary0.insertDefinition(add_word, added_definition);
				}
				else{
					throw new RuntimeException("Please type something!");
				}
			}
			catch(RuntimeException e){
				JOptionPane.showMessageDialog(null, "Empty Input Option Found", "Blasphemous Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 1:
			try {
				String del = JOptionPane.showInputDialog(null, "What are you deleting?");

				while (del.equalsIgnoreCase("") || del == null) {
					del = JOptionPane.showInputDialog(null, "What are you deleting?");
				}

				dictionary0.deleteWord(del);
			}
			catch(NullPointerException e){
				viewthedictionary(dictionary0);
			}
			break;
		case 2:
			WordMeaningNode link = dictionary0.top;
			String allcurrentdictionarywords = "";

			while(link != null){
				String multiDef = "";
				for(String defs : link.word.Def){
					multiDef +=" - " + defs + "\n";
				}
				allcurrentdictionarywords += link.word.Word + multiDef;
				link = link.counter;
			}
			JTextArea dictionaryWords = new JTextArea(allcurrentdictionarywords);
			JScrollPane dictionaryScroll = new JScrollPane(dictionaryWords);

			int resultD = JOptionPane.showOptionDialog(null, dictionaryScroll,"Dictionary",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if(resultD == JOptionPane.OK_OPTION){
				viewthedictionary(dictionary0);
			}
			else{
				viewthedictionary(dictionary0);
			}

			break;
		case 3:
			viewthemenu(dictionary0);
			break;
		case JOptionPane.CLOSED_OPTION:
			System.exit(0);
			break;
		}
	}
}