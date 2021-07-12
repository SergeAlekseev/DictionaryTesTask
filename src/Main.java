import DictionaryLib.DictionaryInFileWithRegex;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        Boolean run = true;
        while (run) {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Это приложение работает со словарями\n" +
                    "Введите номер пункта меню или exit для выхода" +
                    "1. Создать и открыть словарь" +
                    "2. Открыть словарь из предложенных" +
                    "3. Удалить словарь");
            switch (br.readLine()) {
                case "1":
                    create(br);
                    break;
                case "2":
                    open(br);
                    break;
                case "3":
                    delete(br);
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    clearComandLine();
            }
        }

    }

    private static void delete(BufferedReader br) {
    }

    private static void open(BufferedReader br) {
    }

    static void create(BufferedReader br) throws IOException {
        clearComandLine();
        System.out.println("Введите имя словаря: ");
        String dictionaryName = br.readLine();

    }

    static void clearComandLine(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
