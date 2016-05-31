import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

public class TestCollection {

    final private String TEXT = "(_I_)";
    final private int NUMBER_OF_CHECKS = 100;
    final private int[] SIZE_OF_THE_ARRAY = {100, 1000, 10000};
    private Formatter formater;

    public void creatingList() throws FileNotFoundException {

        formater = new Formatter(new FileOutputStream("file.fmt"));
        formater.format("%20s %15s %16s %17s %15s %20s %23s%n", "add", "get", "remove", "contains", "populate", "ListIterator.add()", "ListIterator.remove()");

        formater.format("ArrayList" + "\n");
        fillingCollectionList(new ArrayList<>());
        formater.format("LinkedList" + "\n");
        fillingCollectionList(new LinkedList<>());

        formater.format("HashSet" + "\n");
        fillingCollectionSet(new HashSet<>());
        formater.format("ThreeSet" + "\n");
        fillingCollectionSet(new TreeSet<>());

        formater.close();
    }

    // Заполнение коллекции List
    private void fillingCollectionList(List<String> list) {
        for (int sizeValue : SIZE_OF_THE_ARRAY) {

            for (int i = 0; i < sizeValue; i++) {
                list.add(TEXT);
            }
            outputValuesOnTheScreenForList(testTimeAddForList(list), testTimeGet(list), testTimeRemoveForList(list),
                    testTimeContainsForList(list), testTimePopulateForList(list, sizeValue),
                    testTimeListIteratorAdd(list), testTimeListIteratorRemove(list));
        }
    }

    // Заполнение коллекции Set
    private void fillingCollectionSet(Set<String> set) {
        for (int sizeValue : SIZE_OF_THE_ARRAY) {

            for (int i = sizeValue; i > 0; i--) {
                set.add(i + "");
            }
            outputValuesOnTheScreenForSet(testTimeAddForSet(set), testTimeRemoveForSet(set),
                    testTimeContainsForSet(set), testTimePopulateForSet(set, sizeValue));
        }
    }

    // Вывод результатов на экран
    private void outputValuesOnTheScreenForList(long nanoTimeAdd, long nanoTimeGet, long nanoTimeRemove, long nanoTimeContains, long nanoTimePopulate, long nanoTimeListIteratorAdd, long nanoTimeListIteratorRemove) {
        formater.format("               %-15d %-15d %-15d %-15d %-15d %-20d %-20d%n", nanoTimeAdd, nanoTimeGet, nanoTimeRemove, nanoTimeContains, nanoTimePopulate, nanoTimeListIteratorAdd, nanoTimeListIteratorRemove);
    }

    private void outputValuesOnTheScreenForSet(long nanoTimeAdd, long nanoTimeRemove, long nanoTimeContains, long nanoTimePopulate) {
        String empty = "-";
        formater.format("               %-15d %-15s %-15d %-15d %-15d %-20s %-20s%n", nanoTimeAdd, empty, nanoTimeRemove, nanoTimeContains, nanoTimePopulate, empty, empty);
    }

    // Тест времени выполнения метода Add
    private long testTimeAddForList(List<String> list) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < list.size(); i++) {
                long startTime = System.nanoTime();
                list.add(list.size() / 2, TEXT);
                long endTime = System.nanoTime();
                list.remove(list.size() - 1);
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    private long testTimeAddForSet(Set<String> set) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < set.size(); i++) {
                long startTime = System.nanoTime();
                set.add((set.size() / 2) + "555");
                long endTime = System.nanoTime();
                set.remove((set.size() - 1) + "");
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    // Тест времени выполнения метода Get
    private long testTimeGet(List<String> list) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < list.size(); i++) {
                long startTime = System.nanoTime();
                list.get(list.size() / 2);
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    // Тест времени выполнения метода Remove
    private long testTimeRemoveForList(List<String> list) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < list.size(); i++) {
                long startTime = System.nanoTime();
                list.remove(list.size() / 2);
                long endTime = System.nanoTime();
                list.add(TEXT);
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    private long testTimeRemoveForSet(Set<String> set) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < set.size(); i++) {
                long startTime = System.nanoTime();
                set.remove(i + "");
                long endTime = System.nanoTime();
                set.add((NUMBER_OF_CHECKS + 1) + "");
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    // Тест времени выполнения метода Contains
    private long testTimeContainsForList(List<String> list) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < list.size(); i++) {
                list.add(list.size() / 2, "Bobrik");
                list.remove(list.size() - 1);
                long startTime = System.nanoTime();
                list.contains("Bobrik");
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    private long testTimeContainsForSet(Set<String> set) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            for (int i = 0; i < set.size(); i++) {
                long startTime = System.nanoTime();
                set.contains(i + "");
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    // Тест времени выполнения метода Populate
    private long testTimePopulateForList(List<String> list, int size) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            list.removeAll(list);
            for (int i = 0; i < size; i++) {
                long startTime = System.nanoTime();
                list.add(TEXT);
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    private long testTimePopulateForSet(Set<String> set, int size) {
        long allTime = 0;
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            set.removeAll(set);
            for (int i = 0; i < size; i++) {
                long startTime = System.nanoTime();
                set.add(TEXT);
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    // Тест времени выполнения метода ListIterator.add()
    private long testTimeListIteratorAdd(List<String> list) {
        long allTime = 0;
        ListIterator<String> listItr = list.listIterator();
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            while (listItr.nextIndex() != list.size() / 2) {
                long startTime = System.nanoTime();
                listItr.add("Barsik");
                long endTime = System.nanoTime();
                allTime += (endTime - startTime);
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }

    private long testTimeListIteratorRemove(List<String> list) {
        long allTime = 0;
        ListIterator<String> listItr = list.listIterator();
        for (int k = 0; k < NUMBER_OF_CHECKS; k++) {
            while (listItr.hasNext()) {
                if (listItr.next().equals(TEXT)) {
                    long startTime = System.nanoTime();
                    listItr.remove();
                    long endTime = System.nanoTime();
                    allTime += (endTime - startTime);
                }
            }
        }
        return allTime / NUMBER_OF_CHECKS;
    }
}