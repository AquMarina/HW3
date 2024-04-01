package Maine;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {
    static Scanner scanner = new Scanner(System.in, "ibm866");

    public static void main(String[] args) throws IOException {

        System.out.println(
                "Введите строку через ПРОБЕЛ (строка должна содержать Ф.И.О, дату рождения - dd.mm.yyyy, номер телефона - цифры, пол - f/m ) : ");
        String data = scanner.nextLine();
        String[] arr = data.split(" ");
        if (arr.length != 6) {
            throw new IOException(
                    "Введено неккоректное количество значений, нужно 6 значений, вы ввели " + arr.length
                            + ", попробуйте ещё раз.");
        }
        String dataBirth = arr[3];
        try {
            String[] db = dataBirth.split("\\.");
            if (Integer.parseInt(db[0]) < 0 || Integer.parseInt(db[0]) > 31
                    || Integer.parseInt(db[1]) < 0 || Integer.parseInt(db[1]) > 12 || Integer.parseInt(db[2]) < 1920
                    || Integer.parseInt(db[2]) > 2024) {

                throw new ArrayIndexOutOfBoundsException(
                        "Несуществующая дата.");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Некорректно введена дата. Формат ввода должен быть таким: dd.mm.yyyy.");
        }
        String phone = arr[4];
        if (phone.length() != 10) {
            throw new NumberFormatException(
                    "Не верно введён номер телефона. Формат ввода: целое беззнаковое число, состоящие из 10 цифр.");
        }
        try {
            Long.parseLong(phone);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Номер должен состоять только из чисел.");
        }

        String denger = arr[5];
        if (!denger.equals("m") && !denger.equals("f")) {
            throw new IOException("Неккоректно введён пол, нужно f/m, попробуйте ещё раз.");
        }
        String fn = arr[0] + ".txt";
        try (FileWriter fw = new FileWriter(fn, true)) {
            fw.write(String.format("<%s> <%s> <%s> <%s> <%s> <%s> %n", arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]));
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
