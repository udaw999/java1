package mu2;
import java.util.Arrays;
public class EratosthenesTane {


        private boolean[] sieve;
        public EratosthenesTane(int N) {
            int a = N;
           // boolean[] sieve = new boolean[a];
            sieve = new boolean[a];
            Arrays.fill(sieve, true);
            sift();
        }

        private void sift (){
//            for (int i = 2; i < sieve.length; i++) {
//                int j=i*i;
//                while (j < sieve.length) {
//                    if (sieve [j] == true) {
//                        sieve[j] = false;
//                        j=j*i;
//                    }
//                }
//            }
            for (int i = 2; i < sieve.length-1; i++) {
                if (sieve[i]){
                    for (int j = 2; i*j < sieve.length; j++){
                        sieve[i*j] = false;
                    }

                }
            }

            System.out.println(Arrays.toString(sieve));
        }

        public boolean isSimple(int n1) {
            boolean a2 = sieve[n1];
            return a2;
        }
        public static void main(String[] args){
            EratosthenesTane Eratosthenes1 = new EratosthenesTane(20);
          // System.out.println(Arrays.toString(Eratosthenes1.sieve));
            System.out.println(Eratosthenes1.isSimple(15));

        }

}
