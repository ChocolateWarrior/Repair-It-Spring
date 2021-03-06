package helvetica.application.controllers;

import helvetica.application.dtos.RequestDTO;
import helvetica.application.entities.Specification;
import helvetica.application.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class RequestRegistrationController {

    private RequestService requestService;

    @Autowired
    RequestRegistrationController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping
    public String getRegistrationForm(Model model){
        model.addAttribute("request", new RequestDTO());
        model.addAttribute("all_specifications", Specification.values());
        return "request";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String executeRegistration(RequestDTO dto, Model model){
        requestService.pushRequest(dto);
        model.addAttribute("message", "Request sent!");
        model.addAttribute("request", dto);
        model.addAttribute("all_specifications", Specification.values());
        return "request";
    }

}
