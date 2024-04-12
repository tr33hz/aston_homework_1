package org.example;

import java.util.Comparator;

public interface CustomCollection<T> {
    /**
     * Добавление элемента в конец коллекции
     *
     * @param obj сохраняемый объект
     */
    void add(T obj);

    /**
     * Метод для сохранения элемента в коллекцию по указанному индексу
     *
     * @param index Индекс в который нужно сохранить объект
     * @param obj   Сохраняемый объект
     */
    void add(int index, T obj);

    /**
     * Получить хранимый объект по заданному индексу
     *
     * @param index Индекс по которому запрашивается объект
     * @return Запрашиваемый объект
     */
    T get(int index);

    /**
     * Удалить хранимый объект по его индексу
     *
     * @param index Индекс удаляемого объекта
     */
    void remove(int index);

    /**
     * Очистить всю коллекцию
     */
    void clear();

    /**
     * Отсортировать коллекцию с помощью метода compareTo() интерфейса Comparable
     */
    void sorted();

    int size();

    void quickSort(Comparator<? super T> comparator);
}
