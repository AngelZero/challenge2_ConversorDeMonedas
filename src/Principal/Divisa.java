package Principal;

import java.util.HashMap;
import java.util.Map;

public class Divisa {
    private String name;
    private Map<String, Double> cambios = new HashMap<>();

    public Divisa(DivisaER divisaEr) {
        this.name = divisaEr.base_code();
        this.cambios = divisaEr.conversion_rates();
    }

    public Map<String, Double> getCambios() {
        return cambios;
    }
}
