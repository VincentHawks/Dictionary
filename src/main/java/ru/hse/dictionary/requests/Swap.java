
package ru.hse.dictionary.requests;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Swap {

    private ArrayList<String> body;
    private int index;

}
