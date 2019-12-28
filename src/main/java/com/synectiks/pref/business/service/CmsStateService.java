package com.synectiks.pref.business.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.domain.State;
import com.synectiks.pref.repository.StateRepository;

@Component
public class CmsStateService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    StateRepository stateRepository;

    public List<State> getStateList(){
    	List<State> list = this.stateRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o1.getStateName().compareTo(o2.getStateName()));
    	return list;
    }
    
    public State getState(String stateName){
    	State st = new State();
    	st.setStateName(stateName);
    	Optional<State> ost = this.stateRepository.findOne(Example.of(st));
    	if(ost.isPresent()) {
    		return ost.get();
    	}
    	return null;
    }
    
    public State getState(Long id){
    	Optional<State> ost = this.stateRepository.findById(id);
    	if(ost.isPresent()) {
    		return ost.get();
    	}
    	return null;
    }
    
    
}
