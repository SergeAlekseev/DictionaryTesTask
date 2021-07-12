import DictionaryLib.DictionariesInFileController;
import DictionaryLib.DictionariesInFileWithRegexController;
import DictionaryLib.DictionaryInFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionariesInFileView {

    DictionariesInFileController dictionariesController;
    BufferedReader br;

    public DictionariesInFileView() throws IOException, InterruptedException {
        initController();
        start();
    }

    protected void initController() {
        dictionariesController = new DictionariesInFileController();
    }

    public void start() throws IOException, InterruptedException {
        Boolean run = true;
        while (run) {
            clearComandLine();
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Это приложение работает со словарями\n" +
                    "Введите номер пункта меню или exit для выхода\n" +
                    "1. Создать и открыть словарь\n" +
                    "2. Открыть словарь\n" +
                    "3. Удалить словарь\n");
            switch (br.readLine()) {
                case "1":
                    create();
                    break;
                case "2":
                    open();
                    break;
                case "3":
                    delete();
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    clearComandLine();
            }
        }
    }

    protected void delete() throws IOException {
        clearComandLine();
        System.out.println("Удаление словаря ");
        System.out.println("Введите имя словаря: ");
        String dictionaryName = br.readLine();
        if (dictionariesController.delete(dictionaryName)) {
            System.out.format("Cловарь с именем %s удален\n", dictionaryName);
        } else {
            System.out.format("Cловаря с именем %s нет\n", dictionaryName);
        }
    }

    protected void open() throws IOException, InterruptedException {
        clearComandLine();
        System.out.println("Открытие словаря ");
        System.out.println("Введите имя словаря: ");
        String dictionaryName = br.readLine();
        DictionaryInFile dictionary ;
        if (dictionariesController.hasDictionary(dictionaryName)) {
            dictionary = dictionariesController.getDictionary(dictionaryName);
            if (dictionary != null) {
                System.out.format("Cловарь с именем %s открыт \n Переход к управлению словарем \n", dictionaryName);
                Thread.sleep(1500);
                clearComandLine();
                dictionaryMenu(dictionary);
            } else {
                System.out.format("Словарь с именем %s не смог открыться", dictionaryName);
            }

        } else {
            System.out.format("Словаря с именем %s нет", dictionaryName);
        }

    }

    protected void create() throws IOException, InterruptedException {
        clearComandLine();
        System.out.println("Создание словаря ");
        System.out.println("Введите имя словаря: ");
        String dictionaryName = br.readLine();
        if (!dictionariesController.hasDictionary(dictionaryName)) {
            initDict(dictionaryName);
        } else {
            System.out.format("Словарь с именем %s уже есть", dictionaryName);
        }
        Thread.sleep(2000);
    }

    protected void initDict(String dictionaryName) throws IOException, InterruptedException {
        DictionaryInFile dictionary;
        dictionary = dictionariesController.create(dictionaryName);
        createEnd(dictionaryName, dictionary);
    }

    protected void createEnd(String dictionaryName, DictionaryInFile dictionary) throws InterruptedException, IOException {
        System.out.format("Cловарь с именем %s создан \n Переход к управлению словарем \n", dictionaryName);
        Thread.sleep(1500);
        clearComandLine();
        dictionaryMenu(dictionary);
    }

    void clearComandLine() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void dictionaryMenu(DictionaryInFile dictionary) throws IOException, InterruptedException {
        new DictionaryInFileView(dictionary);
    }
}
