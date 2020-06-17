package testPr;

public class Hanoi {
    public static void hanoiTowers(int quantity, int from, int to, int buf_peg)	{
        if (quantity != 0)
        {
            hanoiTowers(quantity-1, from, buf_peg, to);

            System.out.println("" + from + " -> " + to );

            hanoiTowers(quantity-1, buf_peg, to, from);
        }
    }

    public static void main(String[] args) {
        //plate_quantity - число колец
        //start_peg - начальное положение колец(1-3)
        //destination_peg - конечное положение колец(1-3)
        //buffer_peg - промежуточный колышек(1-3)

        int plate_quantity = 3, start_peg = 1, destination_peg = 2, buffer_peg = 3 ;
        hanoiTowers(plate_quantity, start_peg, destination_peg, buffer_peg);
    }
}
