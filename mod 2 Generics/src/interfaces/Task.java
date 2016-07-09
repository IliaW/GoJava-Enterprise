package interfaces;

public interface Task<T extends Number> {

    // Метода запускает таск на выполнение
    void execute();

    // Возвращает результат выполнения
    T getResult();

}