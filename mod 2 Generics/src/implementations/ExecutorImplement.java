package implementations;

import interfaces.Executor;
import interfaces.Task;
import interfaces.Validator;

import java.util.LinkedList;
import java.util.List;

public class ExecutorImplement<T extends Number> implements Executor<T> {
    private List<T> validResult;
    private List<T> invalidResult;
    private List<Task<? super Integer>> executeTask;

    public ExecutorImplement() {
        this.validResult = new LinkedList<>();
        this.invalidResult = new LinkedList<>();
        this.executeTask = new LinkedList<>();
    }

    @Override
    public void addTask(Task<? super Integer> task) {
        this.executeTask.add(task);
    }

    @Override
    public void addTask(Task<T> task, Validator<T> validator) {
        if (task == null)
            invalidResult.add(null);
        else if (validator.isValid(task.getResult()))
            validResult.add(task.getResult());
        else invalidResult.add(task.getResult());

    }

    @Override
    public void execute() {
        NumberValidator numberValidator = new NumberValidator();
        for (Task task : executeTask) {
            if (task == null)
                invalidResult.add(null);
            else if (numberValidator.isValid(task.getResult()))
                validResult.add((T) task.getResult());
            else invalidResult.add((T) task.getResult());
        }
    }

    @Override
    public List<T> getValidResults() {
        return validResult;
    }

    @Override
    public List<T> getInvalidResults() {
        return invalidResult;
    }
}
