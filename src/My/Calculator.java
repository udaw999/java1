package My;

public class Calculator {

    private int result;

    public Calculator(){
        this.result = 0;
    }

    public int set(int num){

        return result = num;
    }

    public int add(int num){
        return result = result + num;
    }

    public int sub(int num){
        return result = result - num;
    }

    public  int getResult(){
        return result;
    }

    public static void main(String[] args) {
        Calculator calculator1 = new Calculator();

        System.out.println(calculator1.add(1));
        System.out.println(calculator1.sub(5));
        System.out.println(calculator1.set(312));
        System.out.println(calculator1.getResult());
    }

}
