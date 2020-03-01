package ru.progwards.java1.lessons.bigints;

public class AbsInteger {

    public static AbsInteger add(AbsInteger num1, AbsInteger num2){
        Integer num11 = Integer.parseInt(num1.toString());
        Integer num22 = Integer.parseInt(num2.toString());
        Integer sum = num11 + num22;

        System.out.println(num11 + " + " + num22 + " = " + sum);
        if ( sum > -128 &&  sum <= 127){
//           String ssum = Integer.toString(sum);
//           byte bsum = Byte.parseByte(ssum);
//            System.out.println("byte");
           return new ByteInteger(Byte.parseByte(Integer.toString(sum)));
        } else if ((sum > -32768 && sum < -128) || (127 < sum && sum < 32767)){
          return new ShortInteger(Short.parseShort(Integer.toString(sum)));
        }
        else {
            return new IntInteger(sum);
        }

    }


    public static void main(String[] args) {
        byte n  = 127;
        ByteInteger byteInteger = new ByteInteger(n);
        System.out.println(byteInteger.toString());

        short n2 = 5;
        ShortInteger shortInteger = new ShortInteger(n2);
        System.out.println(shortInteger.toString());

        int n3 = 8;
        IntInteger intInteger = new IntInteger(n3);
        System.out.println(intInteger.toString());

        AbsInteger absInteger = new AbsInteger();
        System.out.println("сложение");
        System.out.println(absInteger.add(byteInteger,shortInteger));
    }

}
