package com.example.petitionhub.user.profile;

import com.example.petitionhub.petitions.projections.PetitionProjection;
import com.example.petitionhub.repositories.UserRepository;
import com.example.petitionhub.petitions.services.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final PetitionService petitionService;
    private final UserRepository userRepository;

    @GetMapping("/{name}")
    public String profile(Model model, @PathVariable(name = "name", required = false) String name) {
        List<PetitionProjection> petitions = petitionService.findPetitionsByAuthorUsername(name);

        model.addAttribute("petitions", petitions);
        model.addAttribute("user", userRepository.findUserEntityByUsername(name).get());
        model.addAttribute("countOfSub", petitions.stream()
                .map(PetitionProjection::getCountOfSignatures)
                .reduce(Integer::sum)
                .orElse(0));

        if (model.containsAttribute("petitionDto")) {
            model.addAttribute("petitionDto", model.getAttribute("petitionDto"));
        }

        return "user/profile";
    }

    @GetMapping
    public String redirectToProfile(Principal principal) {
        return "redirect:/profile/" + principal.getName();
    }


}
