package kz.serikov.shutelling.controllers;

import kz.serikov.shutelling.DAO.DestinationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/reservation")
@Controller
public class ReservationController {
    private final DestinationDAO destinationDAO;

    @Autowired
    public ReservationController(DestinationDAO destinationDAO) {
        this.destinationDAO = destinationDAO;
    }

    @GetMapping()
    public String reservation(){
        return "reservations/reservation";
    }

    @GetMapping("/filteredflights")
    public String filteredFlights() {
        return "reservations/filteredflights";
    }

    @PostMapping("/frfr")
    public String reservation(@RequestParam String from, RedirectAttributes redirectAttributes) {
        String planet = destinationDAO.find(from);
        if (planet == null) {
            planet = "Unknown Planet";
        }
        redirectAttributes.addFlashAttribute("planet", planet); // Flash attribute for short-lived session
        return "redirect:/reservation/filteredflights";
    }


}
