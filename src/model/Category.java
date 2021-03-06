package model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int id;
    private String name;
    private List<Word> wordList;

    public List<Word> getWordList() {
        return wordList;
    }

    public int getId() {
        return id;
    }

    public Category(String name) {
        this.name = name;
        wordList = new ArrayList<>();
    }


    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addWordToList(Word newWord) {
        wordList.add(newWord);
    }

    public int getLastIdOfWord() {
        return wordList.size() > 0 ? wordList.get(wordList.size() - 1).getId() : 0;
    }

    public boolean wordExists(String word) {
        for (Word wordInList : wordList) {
            if(word.equals(wordInList.getName())) {
                return true;
            }
        }
        return false;
    }

    public void reindexWordList() {
        int index = 1;
        for (Word word: wordList
             ) {
            word.setId(index++);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
