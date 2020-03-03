package ru.hse.dictionary.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "entries")
public class Entry {
    @Id
    private String id;
    private String entry;
    private ArrayList<ArrayList<String>> definitions;

    public Entry(String id, String entry, ArrayList<ArrayList<String>> definitions) {
        this.id = id;
        this.entry = entry;
        this.definitions = definitions;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setEntry(String entry){
        this.entry = entry;
    }
    public String getEntry(){
        return this.entry;
    }
    public void setDefinitions(ArrayList<ArrayList<String>> definitions){
        this.definitions = definitions;
    }
    public ArrayList<ArrayList<String>> getDefinitions(){
        return this.definitions;
    }
    public ArrayList<String> getDefinitionByIndex(int index) {
        return definitions.get(index);
    }

    public void setDefinitionByIndex(int index, ArrayList<String> definition) {
        definitions.set(index, definition);
    }

    public void delete(int index) {
        definitions.remove(index);
    }

}
