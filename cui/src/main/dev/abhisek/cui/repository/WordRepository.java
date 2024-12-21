package main.dev.abhisek.cui.repository;

import main.dev.abhisek.cui.entities.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    private static final String STORAGE_PATH = "./store.txt";
    private File file;

    public WordRepository() {
        try {
            file = new File(STORAGE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            file = null;
            throw new RuntimeException(e);
        }
    }

    public void add(Word word) {
        List<Word> words = getAll();
        if (words.isEmpty()) {
            write(word);
            return;
        }
        boolean found = false;
        for (Word w : words) {
            if (w.getName().equals(word.getName())) {
                found = true;
                break;
            }
        }
        if (!found) {
            write(word);
        }
    }

    private void write(Word word) {
        try {
            FileWriter writer = new FileWriter(file, true);
            String wordAsCsv = Word.toCsv(word);
            writer.write(wordAsCsv);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Word> getAll() {
        try {

            FileReader fileReader = new FileReader(file);
            int b;
            StringBuilder line = new StringBuilder();
            List<String> lines = new ArrayList<>();
            while ((b = fileReader.read()) != -1) {
                char c = (char) b;
                if (c == '\n') {
                    lines.add(line.toString());
                    line = new StringBuilder();
                } else {
                    line.append(c);
                }
            }
            fileReader.close();
            List<Word> words = new ArrayList<>();
            for (String s : lines) {
                Word word = Word.fromCsv(s);
                words.add(word);
            }
            return words;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public void remove(Word word) {
        try {
            List<Word> words = getAll();
            words.remove(word);

            StringBuilder text = new StringBuilder();
            FileWriter writer = new FileWriter(file);
            for (Word w : words) {
                text.append(Word.toCsv(w));
            }
            writer.write(text.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
