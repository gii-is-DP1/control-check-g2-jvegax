package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetRepository;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;

@Service
public class FeedingService {

    @Autowired
    public FeedingRepository feedingRepository;

    @Autowired
    public PetRepository petRepository;




    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return this.feedingRepository.findAllFeedingTypes();
    }

    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.getFeedingType(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding f) throws UnfeasibleFeedingException {
        
        if(!f.getPet().getType().equals(f.getFeedingType().getPetType())){
            throw new UnfeasibleFeedingException();
        }else{
            return this.feedingRepository.save(f);
        }
      
    }

    
}
