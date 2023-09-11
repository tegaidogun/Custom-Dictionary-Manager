public class WordMeaningNode
{
	WordMeaningNode counter;
	WordMeaning word;

	public WordMeaningNode(){
		counter = null;
		word = new WordMeaning("");
	}

	public WordMeaningNode(String word){
		this.word = new WordMeaning(word);
	}
}