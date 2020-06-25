package it.polito.tdp.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	Set<String> dictionaryWords = new HashSet<>();
	
	public void loadDictionary (String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null)
				dictionaryWords.add(word);
			br.close();
		}
		catch (IOException e) {
			System.err.println("Errore nella lettura del file.");
		}
	}

	public Set<String> getDictionaryWords() {
		return dictionaryWords;
	}
	
	public List<RichWord> spellCheckText (List<String> inputTextList) {
		List <RichWord> list = new LinkedList<>();
		for (String s: inputTextList) {
			RichWord r = new RichWord (s);
			System.out.println(s+"\n");
			if (dictionaryWords.contains(s))
				r.setCorrect(true);
			else
				r.setCorrect(false);
			list.add(r);
		}
		return list;
	}
}
