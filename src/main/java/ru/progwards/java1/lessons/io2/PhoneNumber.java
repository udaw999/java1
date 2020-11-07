package ru.progwards.java1.lessons.io2;
//форматирует телефон под формат +7(999)111-2233, входящий формат может быть различным
public class PhoneNumber {

    public static String format(String phone) throws Exception {
        String nevPhone = "";
        //проходим цыклом по строке оставляем только цифры
        for (char c : phone.toCharArray()){
            if (Character.isDigit(c)){
                nevPhone += c;
            }

        }
        //если 10 или 11 цифр формируем номер иначе выбрасываем исключение
        if (nevPhone.length() == 10) {

            nevPhone = "+7(" + nevPhone.substring(0,3) + ")" +nevPhone.substring(3,6) + "-" + nevPhone.substring(6,10);

        } else  if (nevPhone.length() == 11 ){
            nevPhone = "+7(" + nevPhone.substring(1,4) + ")" +nevPhone.substring(4,7) + "-" + nevPhone.substring(7,11);
        } else {
            throw new Exception();
        }



        return nevPhone;
    }
    public static void main(String[] args) throws Exception {
        String phone = "8 999 111 22 33";
        System.out.println(format(phone));

    }
}
