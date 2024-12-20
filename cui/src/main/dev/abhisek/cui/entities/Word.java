package main.dev.abhisek.cui.entities;

public class Word {
    private String name;
    private String description;

    public Word() {
    }

    public Word(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String toCsv(Word word){
        return String.format("%s,%s\n", word.name, word.description);
    }
    public static Word fromCsv(String csv){
        String[] words = csv.split(",");
        return new Word(words[0], words[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Word word){
            return name.equals(word.name) && description.equals(word.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description='" + description + '\'';
    }
}
