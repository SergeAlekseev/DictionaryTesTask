package DictionaryFileView;

import DictionaryLib.DictionariesInFileWithRegexController;
import DictionaryLib.DictionaryInFile;
import DictionaryLib.DictionaryInFileWithRegex;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.beans.BeanProperty;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ComponentScan
@Configuration
public class Main {

    @Bean
    @Scope("prototype")
    @Lazy
    DictionaryInFileWithRegexView dictView(DictionaryInFile dictionary) throws IOException, InterruptedException {
       return new DictionaryInFileWithRegexView((DictionaryInFileWithRegex) dictionary, bufferedReader());
    }

    @Bean
    DictionariesInFileWithRegexView dictsView() throws IOException, InterruptedException {
       return new DictionariesInFileWithRegexView() {
           @Override
           DictionaryInFileWithRegexView getDictView(DictionaryInFile dictionary) throws IOException, InterruptedException {
               return dictView(dictionary);
           }
       };
    }

    @Bean
    DictionariesInFileWithRegexController dictsController() {
        return new DictionariesInFileWithRegexController();
    }

    @Bean
    BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext ap = new AnnotationConfigApplicationContext(Main.class);
        ap.getBean(DictionariesInFileWithRegexView.class).start();
    }

}
