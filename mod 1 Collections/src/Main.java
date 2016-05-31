//        Написать программу для сравнения эффективности коллекций:
//
//        Программа должна сравнивать различные имплементации коллекций по эффективности выполнения следующих операций:
//
//        List
//
//        add(index)
//        get(index)
//        remove(index)
//        contains(value)
//        populate (наполнение коллекции)
//        ListIterator.add()
//        ListIterator.remove()

//        Set
//
//        add(value)
//        remove(value)
//        contains(value)
//        populate (наполнение коллекции)
//
//
//        Сравнения должны выполнятся на объемах: 10К (10 000) 100К 1000К элементов.
//
//        Для каждого набора (10К, 100К, 1000К) выполнить не менее 100 измерений и вычислить среднее значение.

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Formatter;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        TestCollection testCollection = new TestCollection();
        testCollection.creatingList();


    }
}