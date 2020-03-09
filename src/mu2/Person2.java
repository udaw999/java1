package mu2;

public class Person2 {
    public String name;
    public Person2(String name) {
        this.name = name;
    }

    abstract class PersonCompare {
        public int compare(Person2 p1, Person2 p2) {
            return 0;
        }
    }
    //Анонимный класс
    PersonCompare personCompare =
            new PersonCompare(){
                @Override
                public int compare(Person2 p1, Person2 p2) {
                    return p1.name.compareTo(p2.name);
                }
            };
    public static void main(String[] args) {
    Person2     p1 = new Person2("TEST");
    Person2 p2 = new Person2("TEST");

        System.out.println(p1.personCompare.compare(p2,p1));

    }
}
