
package ru.hse.dictionary.requests;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

import ru.hse.dictionary.models.Entry;

@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class EntryRequestObject {

    private ArrayList<ArrayList<String>> definitions;
    private String entry;
    private String id;

    public Entry toEntry() {
        return new Entry(this.id, this.entry, this.definitions);
    }
}
