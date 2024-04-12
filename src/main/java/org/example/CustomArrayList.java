package org.example;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Кастомный ArrayList с собственной реализацией
 *
 * @param <T> Тип хранимых данных
 */
public class CustomArrayList<T> implements CustomCollection<T> {
    /**
     * Массив данных
     */
    private T[] array;
    /**
     * Размер коллекции
     */

    private int size = 0;

    /**
     * Создание пустой коллекции
     */
    public CustomArrayList() {
        array = (T[]) new Object[10];
    }

    /**
     * Добавление элемента в конец коллекции
     *
     * @param obj сохраняемый объект
     */
    @Override
    public void add(T obj) {
        checkGrowLength();
        array[size] = obj;
        size++;
    }

    /**
     * Метод для сохранения элемента в коллекцию по указанному индексу
     *
     * @param index Индекс в который нужно сохранить объект
     * @param obj   Сохраняемый объект
     */
    @Override
    public void add(int index, T obj) {
        checkInputIndex(index);

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    /**
     * Проверка входящего индекса на корректность
     *
     * @param index Проверяемый индекс
     * @throws IndexOutOfBoundsException Если получаемый индекс выходит за размер коллекции
     */
    private void checkInputIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Несуществующий индекс");
        }
    }

    /**
     * Получить хранимый объект по заданному индексу
     *
     * @param index Индекс по которому запрашивается объект
     * @return Запрашиваемый объект
     */
    @Override
    public T get(int index) {
        checkInputIndex(index);
        return array[index];
    }

    /**
     * Удалить хранимый объект по его индексу
     *
     * @param index Индекс удаляемого объекта
     */
    @Override
    public void remove(int index) {
        checkInputIndex(index);
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        size--;
        array[size] = null;
    }

    /**
     * Очистить всю коллекцию
     */
    @Override
    public void clear() {
        array = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Отсортировать коллекцию с помощью метода compareTo() интерфейса Comparable
     *
     * @throws IllegalArgumentException Если класс сортируемых объектов не реализует интерфейс Comparable
     * @throws RuntimeException         Если в коллекции содержится null
     */
    @Override
    public void sorted() {
        for (int i = 0; i < size; i++) {
            if (array[i] != null) {
                if (!(array[i] instanceof Comparable)) {
                    throw new IllegalArgumentException("Класс хранимых объектов должен реализовывать интерфейс Comparable");
                }
            } else throw new RuntimeException("Для сортировки список не должен содержать null");
        }
        Arrays.sort(array, 0, size);
    }

    /**
     * Проверка необходимости расширения коллекции
     */
    private void checkGrowLength() {
        if (array.length == size) extension();
    }

    /**
     * Расширение коллекции в 1.5 раза
     */
    private void extension() {
        int newLength = (int) (size * 1.5);
        T[] arrayNew = (T[]) new Object[newLength];

        System.arraycopy(array, 0, arrayNew, 0, array.length);

        array = arrayNew;
    }

    /**
     * Получить кол-во значений коллекции
     *
     * @return число объектов
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Сортирует список с использованием алгоритма быстрой сортировки (QuickSort).
     * Метод принимает компаратор для сравнения элементов списка.
     *
     * @param comparator компаратор для сравнения элементов списка
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSortHelper(0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод для быстрой сортировки (QuickSort).
     * Рекурсивно вызывается для сортировки подмассивов.
     *
     * @param low        начальный индекс массива
     * @param high       конечный индекс массива
     * @param comparator компаратор для сравнения элементов списка
     */
    private void quickSortHelper(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pi = partition(low, high, comparator);

            quickSortHelper(low, pi - 1, comparator);
            quickSortHelper(pi + 1, high, comparator);
        }
    }

    /**
     * Метод разделения массива для быстрой сортировки (QuickSort).
     * Выбирает опорный элемент (pivot), перемещает все элементы, меньшие опорного, влево от него,
     * а все элементы, большие опорного, вправо от него.
     *
     * @param low        начальный индекс массива
     * @param high       конечный индекс массива
     * @param comparator компаратор для сравнения элементов списка
     * @return индекс опорного элемента после разделения
     */
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}