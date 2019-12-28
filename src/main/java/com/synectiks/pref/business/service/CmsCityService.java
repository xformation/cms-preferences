package com.synectiks.pref.business.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.pref.domain.City;
import com.synectiks.pref.domain.State;
import com.synectiks.pref.repository.CityRepository;
import com.synectiks.pref.repository.StateRepository;

@Component
public class CmsCityService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    CityRepository cityRepository;
    
    @Autowired
    CmsStateService cmsStateService;
    

    public List<City> getCityList(){
    	List<City> list = this.cityRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o1.getCityName().compareTo(o2.getCityName()));
    	return list;
    }
    
    public List<City> getCityList(Long stateId){
    	State state = this.cmsStateService.getState(stateId);
    	List<City> list = this.cityRepository.findAll();
    	Collections.sort(list, (o1, o2) -> o1.getCityName().compareTo(o2.getCityName()));
    	return list;
    }
    
    public City getCity(String cityName){
    	City city = new City();
    	city.setCityName(cityName);
    	Optional<City> oct = this.cityRepository.findOne(Example.of(city));
    	if(oct.isPresent()) {
    		return oct.get();
    	}
    	return null;
    }
    
    public City getCity(Long id){
    	Optional<City> ost = this.cityRepository.findById(id);
    	if(ost.isPresent()) {
    		return ost.get();
    	}
    	return null;
    }
    
    
}
