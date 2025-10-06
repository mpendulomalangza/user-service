package za.co.uride.userservice.enums;

public enum EEntityType {
    DRIVER(2, "Driver"),
    PASSENGER(1, "Passenger");
    private final long id;
    private final String description;

    EEntityType(long id, String description) {
        this.id = id;
        this.description = description;
    }
}
