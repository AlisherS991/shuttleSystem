package kz.serikov.shutelling.DAO;

import kz.serikov.shutelling.exceptions.PlanetNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static javax.swing.UIManager.get;

@Component
public class DestinationDAO {
    private final String[] planets = {
            "Mercury", "London", "Tokyo", "Paris", "Berlin"
    };

    AvailableVoyage[] voyages = new AvailableVoyage[] {
            new AvailableVoyage( "Barcelona", "Rome",
                    100, 600.00),
            new AvailableVoyage( "Sydney", "Fiji",
                    180, 950.00)
    };

    // Find the "from" planet, throws PlanetNotFoundException if not found
    public String findVoyageDeparture(AvailableVoyage voyage) {
        for (AvailableVoyage availableVoy : voyages) {
            if (availableVoy.equals(voyage)) { // Use equalsIgnoreCase for case-insensitive comparison
                return availableVoy.getDepartureLocation();
            }
        }
        throw new PlanetNotFoundException("Planet not found");
    }

    public String findVoyageArrival(AvailableVoyage voyage) {
        for (AvailableVoyage availableVoy : voyages) {
            if (availableVoy.equals(voyage)) { // Use equalsIgnoreCase for case-insensitive comparison
                return availableVoy.getArrivalLocation();
            }
        }
        throw new PlanetNotFoundException("Planet not found");
    }

    public int findAvailableSeats(AvailableVoyage voyage) {
        for (AvailableVoyage availableVoy : voyages) {
            if (availableVoy.equals(voyage)) { // Use equalsIgnoreCase for case-insensitive comparison
                return availableVoy.getAvailableSeats();
            }
        }
        throw new PlanetNotFoundException("Planet not found");
    }

    public double findPrice(AvailableVoyage voyage) {
        for (AvailableVoyage availableVoy : voyages) {
            if (availableVoy.equals(voyage)) { // Use equalsIgnoreCase for case-insensitive comparison
                return availableVoy.getPrice();
            }
        }
        throw new PlanetNotFoundException("Planet not found");
    }

    // Find the "to" planet, throws PlanetNotFoundException if not found
    public String findTo(String planet) throws PlanetNotFoundException { // Declare throws exception for consistency
        for (String s : planets) {
            if (s.equalsIgnoreCase(planet)) { // Use equalsIgnoreCase for case-insensitive comparison
                return s;
            }
        }
        throw new PlanetNotFoundException("Planet not found: " + planet);
    }


}
