package ac.th.ssru.it.hydroponicsapp.model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DeviceModel {
    private double humidity;
    private double ph;
    private double temperature;
    private double Nitrogen;
    private double Phosphorus;
    private double Potassium;
    private double Fertility;

    public DeviceModel() {
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getNitrogen() {
        return Nitrogen;
    }

    public void setNitrogen(double nitrogen) {
        Nitrogen = nitrogen;
    }

    public double getPhosphorus() {
        return Phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        Phosphorus = phosphorus;
    }

    public double getPotassium() {
        return Potassium;
    }

    public void setPotassium(double potassium) {
        Potassium = potassium;
    }

    public double getFertility() {
        return Fertility;
    }

    public void setFertility(double fertility) {
        Fertility = fertility;
    }
}
