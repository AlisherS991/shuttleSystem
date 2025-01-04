package kz.serikov.shutelling.DAO;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DestinationDAO {
    private final String[] planets = {
            "Mercury", "London", "Tokyo", "Paris", "Berlin"
                               };
    public String find(String planet){
        for (String s : planets) {
            if (s.equals(planet)) {
                return s;
            }
        }
        return "no one matches";
    }

}
