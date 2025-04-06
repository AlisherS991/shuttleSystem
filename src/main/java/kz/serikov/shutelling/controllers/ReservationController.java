package kz.serikov.shutelling.controllers;
import kz.serikov.shutelling.DAO.AvailableVoyage;
import kz.serikov.shutelling.DAO.DestinationDAO;
import kz.serikov.shutelling.exceptions.PlanetNotFoundException;
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
    public String reservation(@RequestParam String from,
                              @RequestParam String to,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        try {
            // Try to find the planet from the "from" input
            String DepartureLocation = destinationDAO.findVoyageDeparture(new AvailableVoyage(from,to,0,0));
            String ArrivaLocation = destinationDAO.findVoyageArrival(new AvailableVoyage(from,to,0,0));
            int findAvailableSeats = destinationDAO.findAvailableSeats(new AvailableVoyage(from,to,0,0));
            double price = destinationDAO.findPrice(new AvailableVoyage(from,to,0,0));
            redirectAttributes.addFlashAttribute("destination", DepartureLocation);
            redirectAttributes.addFlashAttribute("arrival", ArrivaLocation);
            redirectAttributes.addFlashAttribute("seats", findAvailableSeats);
            redirectAttributes.addFlashAttribute("price", price);// Flash attribute for short-lived session
            return "redirect:/reservation/filteredflights";
        } catch (PlanetNotFoundException ex) {
            System.out.println( ex.getMessage());
            // If the planet is not found, handle the exception
            model.addAttribute("error", ex.getMessage()); // Add error message to the model
            return "reservations/reservation"; // Return to the reservation page with error message
        }

    }


}
