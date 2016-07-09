package implementations;

import interfaces.Task;

public class LongTask<T extends Number> implements Task<T> {

    private T result;

    public LongTask(T number) {
        this.result = number;
    }

    @Override
    public void execute() {

    }

    @Override
    public T getResult() {
        return result;
    }
}