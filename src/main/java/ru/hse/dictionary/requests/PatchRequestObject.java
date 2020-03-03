
package ru.hse.dictionary.requests;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class PatchRequestObject {

    private ArrayList<String> add;
    private Integer delete;
    private Swap swap;

}
