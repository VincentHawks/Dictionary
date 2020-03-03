package ru.hse.dictionary.repositories;

import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.hse.dictionary.models.Entry;

import java.util.List;
import java.util.Optional;

public interface EntryRepository extends MongoRepository<Entry, String> {
    @Override
    Optional<Entry> findById(@NonNull String id);
}
