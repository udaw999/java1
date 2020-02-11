package ru.progwards.java1.lessons.arrays;
//Решето Эратосфена узнаем простое число n или составное
import java.util.Arrays;

public class Eratosthenes {

    private boolean[] sieve;

    public  Eratosthenes(int N){
        this.sieve = new boolean[N];
         Arrays.fill(sieve,true);
        sift();

    }

    public void sift(){

        for (int i=2; i < sieve.length-1; i++){
            for (int j=i+1; j < sieve.length; j++){
                if (j%i == 0 )
                    sieve[j] = false;
            }
        }
    }
    public boolean isSimple(int n){
        sift();
        return sieve[n];
    }

    public static void main(String[] args){
        Eratosthenes eratosthenes = new Eratosthenes(31);
        System.out.println(eratosthenes.isSimple(17));
    }

}
