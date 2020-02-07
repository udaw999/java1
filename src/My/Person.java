package My;

public class Person {

    private String name, country;
    private int age;

    public Person(){
        country = "RU";
    }

    public Person(String name, int age){
        this();
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCountry() {
        return country;
    }


}
