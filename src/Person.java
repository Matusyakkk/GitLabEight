/**
 * <a href="https://www.cpp.edu/~ftang/courses/CS240/lectures/hashing.htm"><h2>Hash</h2></a>
 * */

public class Person {
    private int age;
    private String name;
    private boolean gender;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (getAge() != person.getAge()) return false;
        if (isGender() != person.isGender()) return false;
        return getName() != null ? getName().equals(person.getName()) : person.getName() == null;
    }

    // hashing the who string with polynomial hashing
    static int hashcode(String s) {
        int hash = 0;
        int n = s.length();

        for (int i = 0; i < n; i++)
            hash = 31*hash + s.charAt(i);
        return hash;
    }

    @Override
    public int hashCode() {
        int result = getAge();
        result = 31 * result + (getName() != null ? hashcode(getName()) : 0);
        return result;
    }
}
