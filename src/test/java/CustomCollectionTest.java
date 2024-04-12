import org.example.CustomCollection;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class CustomCollectionTest {
    CustomCollection<Integer> collection;

    @Test
    void add() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        assertEquals(4, collection.size(), "Размер коллекции должен быть равен 4");
        assertEquals(4, collection.get(3), "Под индексом 3 должно храниться значение 4");
    }

    @Test
    void getObject_whenNotFoundIndex_thenIndexOutOfBoundsExceptionThrow() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        assertThrows(IndexOutOfBoundsException.class, () -> collection.get(5), "Элемент должен отсутствовать по данному индексу");
    }

    @Test
    void getObject_whenObjectPresent_thenReturnObject() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        assertEquals(4, collection.get(3), "Под индексом 3 должно храниться значение 4");
        assertEquals(2, collection.get(1), "Под индексом 1 должно храниться значение 2");
    }


    @Test
    void remove() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        collection.remove(2);

        assertThrows(IndexOutOfBoundsException.class, () -> collection.get(3), "Элемент должен отсутствовать по индексу 3");
        assertEquals(3, collection.size(), "Размер коллекции должен быть равен 3");
        assertEquals(4, collection.get(2), "Элемент с индексом 2 равен 4");
    }

    @Test
    void clear() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        collection.clear();

        assertEquals(0, collection.size(), "Размер коллекции должен быть равен 0");
        assertThrows(IndexOutOfBoundsException.class, () -> collection.get(0), "Элемент должен отсутствовать по данному индексу");
    }

    @Test
    void sorted() {
        collection.add(4);
        collection.add(3);
        collection.add(2);
        collection.add(1);

        collection.sorted();

        assertEquals(1, collection.get(0), "Под индексом 0 должно храниться значение 1");
        assertEquals(2, collection.get(1), "Под индексом 1 должно храниться значение 2");
        assertEquals(3, collection.get(2), "Под индексом 2 должно храниться значение 3");
        assertEquals(4, collection.get(3), "Под индексом 3 должно храниться значение 4");
    }

    @Test
    void size() {
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);

        assertEquals(4, collection.size(), "Размер коллекции должен быть равен 4");

        collection.remove(0);

        assertEquals(3, collection.size(), "Размер коллекции должен быть равен 3");

        collection.clear();

        assertEquals(0, collection.size(), "Размер коллекции должен быть равен 0");
    }

    @Test
    void quickSort() {
        collection.add(5);
        collection.add(2);
        collection.add(7);
        collection.add(1);
        collection.add(9);

        Comparator<Integer> comparator = Comparator.naturalOrder();

        collection.quickSort(comparator);

        assertEquals(1, collection.get(0), "Под индексом 0 должно храниться значение 1");
        assertEquals(2, collection.get(1), "Под индексом 1 должно храниться значение 2");
        assertEquals(5, collection.get(2), "Под индексом 2 должно храниться значение 5");
        assertEquals(7, collection.get(3), "Под индексом 3 должно храниться значение 7");
        assertEquals(9, collection.get(4), "Под индексом 3 должно храниться значение 9");

    }
}
