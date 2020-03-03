package ru.hse.dictionary.services;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

import ru.hse.dictionary.exceptions.NotFoundException;
import ru.hse.dictionary.models.Entry;
import ru.hse.dictionary.repositories.EntryRepository;

@Service
public class EntryService {

    private EntryRepository repository;

    public EntryService(EntryRepository repository) {
        this.repository = repository;
    }

    public List<Entry> getAll() {
        return repository.findAll();
    }

    public Entry getByID(@NonNull String id) throws NotFoundException{
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void save(@NonNull Entry entry) {
        repository.save(entry);
    }

    public Entry saveAndFlush(@NonNull Entry entry) {
        repository.save(entry);
        return entry;
    }

    public void delete(@NonNull String id) {
        repository.deleteById(id);
    }

    public boolean contains(@NonNull Entry entry) {
        return repository.findAll().contains(entry);
    }
}
