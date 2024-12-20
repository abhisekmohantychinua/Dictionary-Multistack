package main.dev.abhisek.cui.repository;

import main.dev.abhisek.cui.entities.Word;

import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private final List<Word> words;

    public WordRepository() {
        words = new ArrayList<>();
    }

    public void add(Word word) {
        if(words.isEmpty()){
            words.add(word);
            return;
        }
        boolean found = false;
        for(Word w : words){
            if(w.getName().equals(word.getName())){
                found = true;
                break;
            }
        }
        if(!found){
            words.add(word);
        }
    }


    public List<Word> getAll() {
        return words;
    }

    public void remove(Word word) {
        words.remove(word);
    }
}
