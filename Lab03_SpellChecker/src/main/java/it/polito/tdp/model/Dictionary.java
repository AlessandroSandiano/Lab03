package it.polito.tdp.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	List<String> dictionaryWords = new LinkedList<>();
	//List<String> dictionaryWords = new ArrayList<>();
	
	
	public void loadDictionary (String language) {
		if (dictionaryWords.size() > 0)
			return;
		try {
			FileReader fr = new FileReader("src/main/resources/" + language + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null)
				dictionaryWords.add(word);
			br.close();
			System.out.println("Dictionary loaded. Found " + dictionaryWords.size() + " words");
		}
		catch (IOException e) {
			System.err.println("Errore nella lettura del file.");
		}
	}

	public List<String> getDictionaryWords() {
		return dictionaryWords;
	}
	
	public List<RichWord> spellCheckTextLinear (List<String> inputTextList) {
		List <RichWord> list = new LinkedList<>();
		for (String s: inputTextList) {
			RichWord r = new RichWord (s);
			for (String st: dictionaryWords)
				if(st.compareTo(s) == 0) {
					r.setCorrect(true);
					break;
				}
				else
					r.setCorrect(false);
			list.add(r);
		}
		return list;
	}
	
	public List<RichWord> spellCheckTextDichotomic (List<String> inputTextList) {
		List <RichWord> list = new LinkedList<>();
		int l = dictionaryWords.size()/2;
		String s1 = dictionaryWords.get(l).toLowerCase();
		for (String s: inputTextList) {
			RichWord r = new RichWord (s);
			if (s.compareTo(s1) == 0)
				r.setCorrect(true);
			else if (s.compareTo(s1) < 0)
				for (int i=0; i<l; i++)
					if (s.compareTo(dictionaryWords.get(i)) == 0) {
						r.setCorrect(true);
						break;
					}
					else r.setCorrect(false);
				else if (s.compareTo(s1) > 0)
						for (int i=l+1; i<dictionaryWords.size(); i++) 
							if (s.compareTo(dictionaryWords.get(i)) == 0) {
								r.setCorrect(true);
								break;
							}
							else r.setCorrect(false);
			list.add(r);
		}
		return list;
	}
}
