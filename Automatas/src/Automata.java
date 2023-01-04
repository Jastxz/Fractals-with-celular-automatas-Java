import java.util.Arrays;

public class Automata {

    protected static int[][] voidedAutomata() {
        return new int[][]{};
    }

    protected static int[][] initializeAutomata(String condition,String board) {

        int[][] res;

        switch (board) {
            case GraphicUtilities.smallBoard -> res = new int[25][25];
            case GraphicUtilities.standardBoard -> res = new int[50][50];
            case GraphicUtilities.bigBoard -> res = new int[75][75];
            case GraphicUtilities.veryBigBoard -> res = new int[100][100];
            default -> res = voidedAutomata();
        }
        if (condition.equals(GraphicUtilities.randomCondition)) {
            res = randomAutomata(res);
        } else if (condition.equals(GraphicUtilities.oneCondition)) {
            res = oneAutomata(res);
        }

        return res;
    }

    protected static void updateAutomata(World world) {

        int[][] automata = world.getAutomata();
        int rule = world.getRule();
        int row = world.getActualRow();

        for (int i = 0; i < automata.length; i++) {
            int[] previousRow = automata[row-1];
            int left;int center = previousRow[i];int right;
            if (i == 0) {
                left = previousRow[previousRow.length-1];
                right = previousRow[i+1];
            } else if (i == previousRow.length - 1) {
                left = previousRow[i-1];
                right = previousRow[0];
            } else {
                left = previousRow[i-1];
                right = previousRow[i+1];
            }
            automata[row][i] = applyRule(rule,left,center,right);
        }

        world.setAutomata(automata);world.setActualRow(row+1);
    }

    private static int[][] randomAutomata(int[][] matrix) {

        int[][] res = matrix.clone();

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                res[i][j] = toBinary(Math.random());
            }
        }

        return res;
    }

    private static int[][] oneAutomata(int[][] matrix) {

        int[][] res = matrix.clone();
        res[0][res.length/2] = 1;

        for (int i = 1; i < res.length; i++) {
            Arrays.fill(res[i], 0);
        }

        return res;
    }

    private static int toBinary(double random) {

        int res = 0;
        random = random*10;
        if (random%2 >= 1) {res = 1;}

        return res;
    }

    private static int applyRule(int rule, int left, int center, int right) {

        int res;
        String ruleChain = Integer.toString(left) + center + right;

        switch (rule) {
            case 30 -> res = rule30(ruleChain);
            case 73 -> res = rule73(ruleChain);
            case 90 -> res = rule90(ruleChain);
            case 105 -> res = rule105(ruleChain);
            case 122 -> res = rule122(ruleChain);
            case 124 -> res = rule124(ruleChain);
            case 126 -> res = rule126(ruleChain);
            case 150 -> res = rule150(ruleChain);
            case 193 -> res = rule193(ruleChain);
            case 195 -> res = rule195(ruleChain);
            default -> res = 0;
        }

        return res;
    }

    private static int rule30(String rule){

        int res;

        switch (rule) {
            case "100","011","010","001" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule73(String rule){

        int res;

        switch (rule) {
            case "110","011","000" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule90(String rule){

        int res;

        switch (rule) {
            case "110","100","011","001" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule105(String rule){

        int res;

        switch (rule) {
            case "110","101","011","000" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule122(String rule){

        int res;

        switch (rule) {
            case "010","000" -> res = 0;
            default -> res = 1;
        }

        return res;
    }

    private static int rule124(String rule){

        int res;

        switch (rule) {
            case "111","001","000" -> res = 0;
            default -> res = 1;
        }

        return res;
    }

    private static int rule126(String rule){

        int res;

        switch (rule) {
            case "111","000" -> res = 0;
            default -> res = 1;
        }

        return res;
    }

    private static int rule150(String rule){

        int res;

        switch (rule) {
            case "111","100","010","001" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule193(String rule){

        int res;

        switch (rule) {
            case "111","110","000" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

    private static int rule195(String rule){

        int res;

        switch (rule) {
            case "111","110","001","000" -> res = 1;
            default -> res = 0;
        }

        return res;
    }

}
