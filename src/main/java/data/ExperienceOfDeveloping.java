package data;

public enum ExperienceOfDeveloping {
    JAVA("Java");

    private String name;
    ExperienceOfDeveloping(String  name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
