package ru.hse.dictionary.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.dictionary.exceptions.NotFoundException;
import ru.hse.dictionary.models.Entry;
import ru.hse.dictionary.requests.PatchRequestObject;
import ru.hse.dictionary.services.EntryService;
import java.util.List;


@RestController
public class EntryController {

    final EntryService service;

    public EntryController(EntryService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(service.getByID(id), HttpStatus.OK);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/",
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry) {
        try {
            service.getByID(entry.getId());
            return new ResponseEntity<>(entry, HttpStatus.CONFLICT);
        } catch(NotFoundException e) {
            return new ResponseEntity<>(service.saveAndFlush(entry), HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public /*ResponseEntity<String>*/ void addOrReplaceEntry(@RequestBody Entry entry) {

//        // PUT requires a NO_CONTENT response if an entity was replaced
//        // and CREATED if a new entity was created
//        // See https://developer.mozilla.org/ru/docs/Web/HTTP/Methods/PUT
//
//        if(service.contains(entry)) {
//            service.saveAndFlush(entry);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        else {
//            service.saveAndFlush(entry);
//            return new ResponseEntity<>(HttpStatus.CREATED);
//        }
        // That's all well and good, but does not work
        service.saveAndFlush(entry);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> changeEntry(@PathVariable("id") String id,
                                              @RequestBody PatchRequestObject request) {

        // This can, and probably should, be refactored later
        try {
            Entry buffer = service.getByID(id);
            if (request.getSwap() != null) {
                buffer.setDefinitionByIndex(request.getSwap().getIndex(),
                                                         request.getSwap().getBody());
            }
            if (request.getAdd() != null) {
                buffer.getDefinitions().add(request.getAdd());
            }
            if (request.getDelete() != null) {
                buffer.delete(request.getDelete());
            }
            service.saveAndFlush(buffer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeEntry(@PathVariable("id") String id) {
        service.delete(id);
    }
}
