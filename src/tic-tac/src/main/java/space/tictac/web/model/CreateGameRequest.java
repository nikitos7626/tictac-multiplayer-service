package space.tictac.web.model;

public class CreateGameRequest {
    private boolean vsComputer;

    // Конструктор по умолчанию (обязателен для десериализации)
    public CreateGameRequest() {}

    public CreateGameRequest(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }

    public boolean isVsComputer() {
        return vsComputer;
    }

    public void setVsComputer(boolean vsComputer) {
        this.vsComputer = vsComputer;
    }
}