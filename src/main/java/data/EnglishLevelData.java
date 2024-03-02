package data;

public enum EnglishLevelData {
    BEGINNER("Начальный уровень (Beginner)");

    private String name;
    EnglishLevelData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}