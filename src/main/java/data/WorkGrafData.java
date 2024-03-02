package data;

public enum WorkGrafData {

    REMOTELY("Удаленно");

    private String name;

    WorkGrafData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}