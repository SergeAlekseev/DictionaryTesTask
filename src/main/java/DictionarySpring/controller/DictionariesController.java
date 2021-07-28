package DictionarySpring.controller;

import DictionarySpring.entity.DictionaryEntity;
import DictionarySpring.entity.TranslateEntity;
import DictionarySpring.entity.WordEntity;
import DictionarySpring.model.NewTranslateModel;
import DictionarySpring.model.NewWordModel;
import DictionarySpring.model.TranslateModel;
import DictionarySpring.model.WordModel;
import DictionarySpring.repository.DictionaryRepository;
import DictionarySpring.repository.TranslateRepository;
import DictionarySpring.repository.WordRepository;
import DictionarySpring.service.DictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/bibl")
public class DictionariesController {


    private final DictionariesService dictionariesService;


    public DictionariesController(DictionariesService dictionariesService) {
        this.dictionariesService = dictionariesService;
    }

    @GetMapping("/dict/{id}")
    @ResponseBody
    public DictionaryEntity getDict(@PathVariable Long id, Model model) {
        return dictionariesService.getDict(id);
    }

    @GetMapping("/dicts")
    @ResponseBody
    public List<DictionaryEntity> getDictionaries(Model model) {
        return dictionariesService.getDictionaries();
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Long deleteWord(@RequestBody WordModel wordModel, Model model) {
        return dictionariesService.deleteWord(wordModel);
    }

    @DeleteMapping("/deleteTrans")
    @ResponseBody
    public Long deleteTrans(@RequestBody TranslateModel translateModel, Model model) {
        return dictionariesService.deleteTrans(translateModel);
    }

    @GetMapping("/translates/{id}")
    @ResponseBody
    public Set<TranslateEntity> getTranslateWord(@PathVariable Long id, Model model) {
        return dictionariesService.getTranslateWord(id);
    }

    @GetMapping("/searchAllByWord/{word}")
    @ResponseBody
    public List<WordEntity> getWordsByWord(@PathVariable String word, Model model) {
        return dictionariesService.getWordsByWord(word);
    }

    @GetMapping("/searchByWord/{dictId}/{word}")
    @ResponseBody
    public List<WordEntity> getWordsByWord(@PathVariable Long dictId, @PathVariable String word, Model model) {
        return dictionariesService.getWordsByWord(dictId, word);
    }

    @GetMapping("/searchAllByTranslate/{translate}")
    @ResponseBody
    public Set<WordEntity> getWordsByTranslate(@PathVariable String translate, Model model) {
        return dictionariesService.getWordsByTranslate(translate);

    }

    @GetMapping("/searchByTranslate/{dictId}/{translate}")
    @ResponseBody
    public Set<WordEntity> getWordsByTranslate(@PathVariable Long dictId, @PathVariable String translate, Model model) {
        return dictionariesService.getWordsByTranslate(dictId, translate);

    }

    @PostMapping("/addWord")
    @ResponseBody
    public WordEntity addWord(@RequestBody NewWordModel newWordModel, Model model) {
        return dictionariesService.addWord(newWordModel);

    }

    @PostMapping("/addTranslate")
    @ResponseBody
    public TranslateModel addTranslate(@RequestBody NewTranslateModel newTranslateModel, Model model) {
        return dictionariesService.addTranslate(newTranslateModel);
    }


}
