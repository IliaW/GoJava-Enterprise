package lesson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsExtendsSuper {

    @Test
    public void testName() {

        List<Integer> ints = Arrays.asList(10, 20, 30);
        List objects = Arrays.asList(10, 20, 30);

        ints.add(10);
        int i = ints.get(0);

    }

    @Test
    public void testPersonList() throws Exception {
        List<Person> persons = Arrays.asList(new Person("Pasha", "Pavlov"), new Person("Lena", "Lenina"), new Person("Sasha", ""));
        System.out.println(isValidList(persons, new PersonValidator()));

        List<Citizen> citizens = Arrays.asList(new Citizen("Pasha", "Pavlov", "Lviv"), new Citizen("Lena", "Lenina", ""), new Citizen("Sasha", "", ""));
        System.out.println(isValidList(citizens, new PersonValidator()));

        List<Citizen> filteredCitizents = filterInvalid(citizens, new PersonValidator());
    }

    public boolean isValidList(List<? extends Person> persons, Validator<Person> personValidator) {
        for (Person person : persons) {
            if(!personValidator.isValid(person)) {
                return false;
            }
        }
        return true;
    }

    public <T> List<T> filterInvalid(List<T> values, Validator<? super T> validator) {
        List<T> result = new ArrayList<T>();
        for (T value : values) {
            if (validator.isValid(value)) {
                result.add(value);
            }
        }
        return result;
    }

    public interface Validator<T> {
        boolean isValid(T value);
    }

    public static class PersonValidator implements Validator<Person> {

        @Override
        public boolean isValid(Person person) {
            return isNotBlank(person.name) && isNotBlank(person.surname);
        }

    }

    public static class Person {
        public String name;
        public String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }
    }

    public static class Citizen extends Person {
        public String address;

        public Citizen(String name, String surname, String address) {
            super(name, surname);
            this.address = address;
        }
    }
}
