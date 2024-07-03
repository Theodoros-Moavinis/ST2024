import java.time.LocalDate;

public class TouristPackage {
    public LocalDate StartDate;
    public LocalDate EndDate;
    public String Location;
    public String Accomodation;
    public String Transportation;

    public TouristPackage(LocalDate startDate, LocalDate endDate, String location, String accomodation,
            String transportation) {
        StartDate = startDate;
        EndDate = endDate;
        Location = location;
        Accomodation = accomodation;
        Transportation = transportation;
    }

    @Override
    public String toString() {
        return "Start" + StartDate + ", End" + EndDate + ", Location=" + Location
                + ", Accomodation=" + Accomodation + ", Transportation=" + Transportation;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAccomodation() {
        return Accomodation;
    }

    public void setAccomodation(String accomodation) {
        Accomodation = accomodation;
    }

    public String getTransportation() {
        return Transportation;
    }

    public void setTransportation(String transportation) {
        Transportation = transportation;
    }

    
}
