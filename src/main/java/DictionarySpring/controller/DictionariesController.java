package DictionarySpring.controller;

import DictionarySpring.entity.Dictionary;
import DictionarySpring.entity.Translate;
import DictionarySpring.entity.Word;
import DictionarySpring.model.ModelNewTranslate;
import DictionarySpring.model.ModelNewWord;
import DictionarySpring.model.ModelTranslate;
import DictionarySpring.model.ModelWord;
import DictionarySpring.repository.DictionaryRepository;
import DictionarySpring.repository.TranslateRepository;
import DictionarySpring.repository.WordRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/bibl")
public class DictionariesController {

    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;
    private final TranslateRepository translateRepository;

    public DictionariesController(DictionaryRepository dictionaryRepository, WordRepository wordRepository, TranslateRepository translateRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
        this.translateRepository = translateRepository;
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        model.addAttribute("dict", dictionaryRepository.getById(id));
        return "edit";
    }

    @GetMapping("/dicts")
    public String getDictionaries(Model model) {
        model.addAttribute("dict1", dictionaryRepository.getById(2L));
        model.addAttribute("dict2", dictionaryRepository.getById(3L));

        return "dictionaries";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteWord(@RequestBody ModelWord modelWord, Model model) {
        wordRepository.deleteById(modelWord.getIdWord());
        return modelWord.getIdWord();
    }

    @DeleteMapping("/deleteTrans")
    @ResponseBody
    public Long deleteTrans(@RequestBody ModelTranslate modelTranslate, Model model) {
        translateRepository.deleteById(modelTranslate.getIdTranslate());
        return modelTranslate.getIdTranslate();
    }

    @GetMapping("/translates/{id}")
    @ResponseBody
    public List<Translate> getTranslateWord(@PathVariable Long id, Model model) {
        return wordRepository.getById(id).getTranslates();
    }

    @GetMapping("/searchAllByWord/{word}")
    @ResponseBody
    public List<Word> getWordsByWord(@PathVariable String word, Model model) {
        return wordRepository.findAllByWord(word);
    }

    @GetMapping("/searchAllByWord/{dictId}/{word}")
    @ResponseBody
    public List<Word> getWordsByWord(@PathVariable Long dictId, @PathVariable String word, Model model) {
        return wordRepository.findAllByWordAndDictId(word, dictId);
    }

    @GetMapping("/searchAllByTranslate/{translate}")
    @ResponseBody
    public Set<Word> getWordsByTranslate(@PathVariable String translate, Model model) {
        List<Translate> translates = translateRepository.findAllByTranslate(translate);
        Set<Word> words = new HashSet<>();
        for (Translate trans : translates) {
            words.add(trans.getWord());
        }

        return words;
    }

    @GetMapping("/searchAllByTranslate/{dictId}/{translate}")
    @ResponseBody
    public Set<Word> getWordsByTranslate(@PathVariable Long dictId, @PathVariable String translate, Model model) {
        List<Translate> translates = translateRepository.findAllByTranslate(translate);
        Set<Word> words = new HashSet<>();
        Word word;
        for (Translate trans : translates) {
            word = trans.getWord();
            if (word.getDictId().equals(dictId))
                words.add(word);
        }

        return words;
    }

    @PostMapping("/addWord")
    @ResponseBody
    public String addWord(@RequestBody ModelNewWord modelNewWord, Model model) {
        Dictionary dictionary = dictionaryRepository.getById(modelNewWord.getIdDict());
        Pattern pattern = Pattern.compile(dictionary.getRegex());
        Matcher matcher = pattern.matcher(modelNewWord.getWord());
        Word word = null;
        if (matcher.find()) {
            word = new Word(modelNewWord);
            wordRepository.saveAndFlush(word);
            return "Added" + modelNewWord.getWord();
        }

        return "Not add";
    }

    @PostMapping("/addTranslate")
    @ResponseBody
    public String addWord(@RequestBody ModelNewTranslate modelNewTranslate, Model model) {
        Translate translate = new Translate(modelNewTranslate, wordRepository.getById(modelNewTranslate.getIdWord()));
        translateRepository.saveAndFlush(translate);
        return "Added"+modelNewTranslate.getTranslate();
    }


}
