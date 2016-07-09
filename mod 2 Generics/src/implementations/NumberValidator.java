package implementations;

import interfaces.Validator;

public class NumberValidator implements Validator<Number> {

    @Override
    public boolean isValid(Number result) {
        return (result != null ? result.doubleValue() : 0) >= 0;
    }
}
