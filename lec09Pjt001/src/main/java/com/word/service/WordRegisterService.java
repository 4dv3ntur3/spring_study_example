package com.word.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterService {

	private WordDao wordDao;
	
	@Autowired
	// 자동으로 찾아주래
	// 자기 컨테이너 안에 있는 객체 중에 worddao 찾아서 넣어줌 
	public WordRegisterService(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	// 중복 단어 등록 x
	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if(verify(wordKey)) {
			wordDao.insert(wordSet);
		} else {
			System.out.println("The word has already registered.");
		}
	}
	
	public boolean verify(String wordKey){
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}
	
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
}