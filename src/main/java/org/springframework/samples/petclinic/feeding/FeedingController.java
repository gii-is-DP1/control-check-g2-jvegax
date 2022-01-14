package org.springframework.samples.petclinic.feeding;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedingController {
    
    private final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";
    
    private final FeedingService feedingService;

    @Autowired
    public FeedingController(FeedingService feedingService) {
        this.feedingService = feedingService;
    }

    @GetMapping(value = "/feeding/create")
	public String initCreationForm(Map<String, Object> model) {
		Feeding feeding = new Feeding();
		model.put("feeding", feeding);
        model.put("feedingTypes", feedingService.getAllFeedingTypes());
		return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
	}

    @PostMapping(path = "/feeding/create")
    public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap modelMap) throws UnfeasibleFeedingException{
        
        if(result.hasErrors()){
            modelMap.addAttribute("feeding", feeding);
            modelMap.addAttribute("feedingType", feedingService.getAllFeedingTypes());
            return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
        } else {
            feedingService.save(feeding);
            modelMap.addAttribute("message", "feeding succesfully saved!");
        }
        return "redirect:/welcome";
    }
}
