package BL.BL_T.Entities;

public class LicenseTypeForTruck {

    private String licenseType;
    private TruckModel truckModel;

    public LicenseTypeForTruck(String licenseType, TruckModel truckModel) {
        this.licenseType = licenseType;
        this.truckModel = truckModel;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public TruckModel getTruckModel() {
        return truckModel;
    }

    @Override
    public String toString() {
        return "License {" +
                "license id='" + licenseType + '\'' +
                ", truck Model='" + truckModel + '\'' +
                '}';
    }
    public void setTruckModel(TruckModel truckModel) {
        this.truckModel = truckModel;
    }
}
