package main.dev.abhisek.cui;

import java.util.Scanner;
import main.dev.abhisek.cui.entities.Word;
import main.dev.abhisek.cui.repository.WordRepository;
import main.dev.abhisek.cui.services.WordService;

import java.util.List;

public class Main {
    private final Scanner scanner;
    private final WordRepository repository;
    private final WordService wordService;

    public Main() {
        scanner = new Scanner(System.in);
        repository = new WordRepository();
        wordService = new WordService(repository);
        char option;
        while (true) {
            System.out.println("""
                    Select an Option:
                        a. Add a new word
                        b. See all word
                        c. Search a word
                        d. Delete a word
                        e. exit
                    """);
            option = scanner.nextLine().charAt(0);
            switch (option) {
                case 'a' -> {
                    // input for word
                    System.out.println("Enter a word: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();
                    Word word = new Word(name, description);
                    wordService.saveWord(word);
                }
                case 'b' -> {
                    List<Word> words = wordService.findAllWords();
                    words.forEach(System.out::println);
                }
                case 'c' -> {
                    System.out.println("Enter query: ");
                    String query = scanner.nextLine();
                    List<Word> matchingWords = wordService.searchWord(query);
                    matchingWords.forEach(System.out::println);
                }
                case 'd' -> {
                    System.out.println("Enter word name: ");
                    String name = scanner.nextLine();
                    wordService.deleteWord(name);
                }
                case 'e' -> System.out.println("Exited");
                default -> System.out.println("Invalid Input");
            }

            if (option == 'e')
                break;
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}