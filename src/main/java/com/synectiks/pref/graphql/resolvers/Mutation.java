package com.synectiks.pref.graphql.resolvers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.synectiks.pref.repository.UserPreferenceRepository;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private UserPreferenceRepository userPreferenceRepository;
    
    public Mutation(UserPreferenceRepository userPreferenceRepository) {
    	this.userPreferenceRepository = userPreferenceRepository;
    }
    
    public Long getAbc() {
		return null;
	}

//    public AddCountryPayload addCountry(AddCountryInput addCountryInput) {
//        final Country country = new Country();
//        country.setCountryName(addCountryInput.getCountryName());
//        country.setCountryCode(addCountryInput.getCountryCode());
//        country.setIsdCode(addCountryInput.getIsdCode());
//        countryRepository.save(country);
//        return new AddCountryPayload(country);
//    }
//
//    public UpdateCountryPayload updateCountry(UpdateCountryInput updateCountryInput) {
//        Country country = countryRepository.findById(updateCountryInput.getId()).get();
//        if (updateCountryInput.getCountryName() != null) {
//            country.setCountryName(updateCountryInput.getCountryName());
//        }
//        if (updateCountryInput.getCountryCode() != null) {
//            country.setCountryCode(updateCountryInput.getCountryCode());
//        }
//        if (updateCountryInput.getIsdCode() != null) {
//            country.setIsdCode(updateCountryInput.getIsdCode());
//        }
//
//        countryRepository.save(country);
//
//        return new UpdateCountryPayload(country);
//    }
//
//    public RemoveCountryPayload removeCountry(RemoveCountryInput removeCountryInput) {
//        Country country = countryRepository.findById(removeCountryInput.getCountryId()).get();
//        countryRepository.delete(country);
//        return new RemoveCountryPayload(Lists.newArrayList(countryRepository.findAll()));
//    }

    
}
