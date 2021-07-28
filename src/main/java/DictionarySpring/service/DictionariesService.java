package DictionarySpring.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DictionariesService {


    private final DictionaryRepository dictionaryRepository;
    private final WordRepository wordRepository;
    private final TranslateRepository translateRepository;


    public DictionariesService(DictionaryRepository dictionaryRepository, WordRepository wordRepository, TranslateRepository translateRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordRepository = wordRepository;
        this.translateRepository = translateRepository;
    }

    public DictionaryEntity getDict(Long id) {
        return dictionaryRepository.findDictById(id);
    }

    public List<DictionaryEntity> getDictionaries() {
        return dictionaryRepository.findDicts();
    }

    public Long deleteWord(WordModel wordModel) {
        if (wordRepository.deleteWord(wordModel.getIdWord()))
            return wordModel.getIdWord();
        else
            return -1L;
    }

    public Long deleteTrans(TranslateModel translateModel) {
        if (translateRepository.deleteById(translateModel.getIdTranslate()))
            return translateModel.getIdTranslate();
        else
            return -1L;
    }


    public Set<TranslateEntity> getTranslateWord(Long id) {
        return wordRepository.findWordById(id).getTranslates();
    }


    public List<WordEntity> getWordsByWord(String word) {
        return wordRepository.findAllByWord(word);
    }


    public List<WordEntity> getWordsByWord(Long dictId, String word) {
        return wordRepository.findAllByWordAndDictId(word, dictId);
    }


    public Set<WordEntity> getWordsByTranslate(String translate) {
        List<TranslateEntity> translateEntities = translateRepository.findAllByTranslate(translate);
        Set<WordEntity> wordEntities = new HashSet<>();
        for (TranslateEntity trans : translateEntities) {
            wordEntities.add(trans.getWord());
        }

        return wordEntities;
    }


    public Set<WordEntity> getWordsByTranslate(Long dictId, String translate) {
        List<TranslateEntity> translateEntities = translateRepository.findAllByTranslate(translate);
        Set<WordEntity> wordEntities = new HashSet<>();
        WordEntity wordEntity;
        for (TranslateEntity trans : translateEntities) {
            wordEntity = trans.getWord();
            if (wordEntity.getDictionary().getId().equals(dictId))
                wordEntities.add(wordEntity);
        }

        return wordEntities;
    }


    public WordEntity addWord(NewWordModel newWordModel) {
        DictionaryEntity dictionaryEntity = dictionaryRepository.findDictById(newWordModel.getIdDict());
        Pattern pattern = Pattern.compile(dictionaryEntity.getRegex());
        Matcher matcher = pattern.matcher(newWordModel.getWord());
        if (matcher.find()) {
            WordEntity wordEntity = new WordEntity(newWordModel, dictionaryEntity);
            wordRepository.saveAndFlush(wordEntity);
            return wordEntity;
        }

        return null;
    }

    public TranslateModel addTranslate(NewTranslateModel newTranslateModel) {
        TranslateEntity translateEntity = new TranslateEntity(newTranslateModel, wordRepository.findWordById(newTranslateModel.getIdWord()));
        translateRepository.saveAndFlush(translateEntity);
        TranslateModel translateModel = new TranslateModel();
        translateModel.setTranslate(newTranslateModel.getTranslate());
        translateModel.setIdTranslate(translateEntity.getId());
        return translateModel;
    }
}
