package DictionaryFileView;

import DictionaryLib.DictionaryInFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DictionaryInFileView {

    @Autowired
    BufferedReader br;

    DictionaryInFile dictionary;

    public DictionaryInFileView(DictionaryInFile dictionary,BufferedReader br) throws IOException, InterruptedException {
        this.dictionary = dictionary;
        this.br = br;
        start();
    }

    public void start() throws IOException, InterruptedException {
        Boolean run = true;
        while (run) {
            clearComandLine();
            System.out.format("Работа со словарем %s\n" +
                    "Введите номер пункта меню или exit для выхода\n" +
                    "1. Найти слово\n" +
                    "2. Добавить слово\n" +
                    "3. Удалить слово\n" +
                    "4. Просмотреть все слова\n" +
                    "5. Сохранить словарь\n" +
                    "6. Загрузить словарь заново\n", dictionary.getName());
            switch (br.readLine()) {
                case "1":
                    search();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    showAll();
                    break;
                case "5":
                    dictionary.save();
                    break;
                case "6":
                    dictionary.load();
                    break;
                case "exit":
                    dictionary.save();
                    run = false;
                    break;
                default:
                    clearComandLine();
            }
        }
    }

    protected void showAll() throws IOException {
        clearComandLine();
        System.out.println(dictionary.toString());
        System.out.println("Чтобы вернуться в меню нажмите Enter");
        br.readLine();
    }

    protected void delete() throws IOException, InterruptedException {
        clearComandLine();
        System.out.println("Удаление слова ");
        System.out.println("Введите слово: ");
        String word = br.readLine();
        if (dictionary.delete(word))
            System.out.println("Слово удалено");
        else
            System.out.println("Слово не удалено");
        Thread.sleep(1500);
    }

    protected void add() throws IOException, InterruptedException {
        clearComandLine();
        System.out.println("Добавление слова ");
        System.out.println("Введите слово: ");
        String word = br.readLine();
        if (dictionary.search(word) == null)
            addWithTranslate(word);
        else
            System.out.println("Слово уже существует");
        Thread.sleep(1500);
    }

    protected void addWithTranslate(String word) throws IOException, InterruptedException {
        System.out.println("Введите перевод: ");
        String translate = br.readLine();
        if (dictionary.add(word, translate))
            System.out.format("Слово %s добалвено с переводом %s \n", word, translate);
        else
            System.out.println("Слово не добавлено");
        Thread.sleep(1500);

    }

    protected void search() throws IOException, InterruptedException {
        clearComandLine();
        System.out.println("Поиск слова ");
        System.out.println("Введите слово: ");
        String word = br.readLine();
        String translate = dictionary.search(word);
        if (translate != null){
            System.out.format("%s - %s \n", word, translate);
            System.out.println("Чтобы вернуться в меню нажмите Enter");
            br.readLine();
        }
        else {
            System.out.println("Слово не найдено");
            Thread.sleep(1500);
        }

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
}
