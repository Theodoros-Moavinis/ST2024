public class Partner {
    protected String CompanyName;
    protected String AFM;
    protected String Location;//τοποθεσία της επιχείρησης/υπηρεσίας

    public Partner(String companyName, String aFM, String location) {
	
		CompanyName = companyName;
		AFM = aFM;
		Location = location;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getAFM() {
        return AFM;
    }

    public void setAFM(String aFM) {
        AFM = aFM;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}

