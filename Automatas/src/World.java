import java.util.Arrays;

public class World {

    private String screen, conditions, boardTam;
    private Integer rule, actualRow;
    private Boolean animation;
    private int[][] automata;

    public World(String screen, Integer rule, String conditions, int[][] automata,
                 Integer actualRow, String boardTam, Boolean animation) {
        this.screen = screen;
        this.rule = rule;
        this.conditions = conditions;
        this.automata = automata;
        this.actualRow = actualRow;
        this.boardTam = boardTam;
        this.animation = animation;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getBoardTam() {
        return boardTam;
    }

    public void setBoardTam(String boardTam) {
        this.boardTam = boardTam;
    }

    public Integer getRule() {
        return rule;
    }

    public void setRule(Integer rule) {
        this.rule = rule;
    }

    public Integer getActualRow() {
        return actualRow;
    }

    public void setActualRow(Integer actualRow) {
        this.actualRow = actualRow;
    }

    public Boolean getAnimation() {
        return animation;
    }

    public void setAnimation(Boolean animation) {
        this.animation = animation;
    }

    public int[][] getAutomata() {
        return automata;
    }

    public void setAutomata(int[][] automata) {
        this.automata = automata;
    }

    @Override
    public String toString() {
        return "World{" +
                "screen='" + screen + '\'' +
                ", conditions='" + conditions + '\'' +
                ", boardTam='" + boardTam + '\'' +
                ", rule=" + rule +
                ", actualRow=" + actualRow +
                ", animation=" + animation +
                ", automata=" + Arrays.toString(automata) +
                '}';
    }
}
