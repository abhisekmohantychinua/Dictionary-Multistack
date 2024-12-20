package main.dev.abhisek.cui.services;

import main.dev.abhisek.cui.entities.Word;
import main.dev.abhisek.cui.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;

public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void saveWord(Word word) {
        wordRepository.add(word);
    }

    public List<Word> findAllWords() {
        return wordRepository.getAll();
    }

    public List<Word> searchWord(String query) {
        List<Word> words = findAllWords();
        List<Word> matchingWords = new ArrayList<>();
        for (Word word : words) {
            if (word.getName().toLowerCase().startsWith(query.toLowerCase())) {
                matchingWords.add(word);
            }
        }
        return matchingWords;
    }

    public void deleteWord(String name) {
        List<Word> words = findAllWords();
        Word found = null;
        for (Word word : words) {
            if (word.getName().equals(name)) {
                found = word;
            }
        }
        if (found != null)
            wordRepository.remove(found);
    }
}
