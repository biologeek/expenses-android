package io.biologeek.expenses.repositories;

import java.util.List;

/**
 * Created by xavier on 09/06/18.
 */

interface CrudRepository<T, U> {
    T get(U id);
    List<T> getAll();
    T save(T toSave);
    T update(T toSave);
}
