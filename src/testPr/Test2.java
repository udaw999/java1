package testPr;

public class Test2 {
   public static int sumSequence(int n) {
        if (n == 1)
            return n;
        return sumSequence(n-2)+n;
    }

    public static String reverseChars(String str){
       char[] strToArray = str.toCharArray();
       String strNev = "";
       int i = strToArray.length;
       if (str == "")
           return "";
       if (i == 1){
           strNev = String.valueOf(strToArray[0]);
       } else {
           strNev += String.valueOf(strToArray[i-1]) + reverseChars(str.substring(0, str.length() - 1));
           //reverseChars(str.substring(0, str.length() - 1));
//           System.out.println(str);
//           for (char c : strToArray)
//               System.out.println(c);
//
//           System.out.println("strNev " + strNev);
       }

       return strNev;
    }
    public static void main(String[] args) {
        //System.out.println(sumSequence(1));

        //System.out.println(sumSequence(2));
        //System.out.println(sumSequence(3));

        System.out.println(reverseChars(""));
    }
}
