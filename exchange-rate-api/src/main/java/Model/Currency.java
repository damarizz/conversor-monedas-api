package Model;

public class Currency {
    String code;
    String name;
    double result;

    public Currency(String code, String name) {

        this.code = code;
        this.name = name;
        this.result = 0.0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getResult() {
        return result;
    }
}
