import java.awt.*;
import java.util.Scanner;

public class Main {
    //all Tiles
    public static Tile[] all = new Tile[81];
    //columns array, [row] == tile
    public static Column[] columns = new Column[9];
    //rows array, ([y][x]) [row][tile]
    public static Row[] rows = new Row[9];
    //squares array [squareX][squareY][tileX][tileY]
    public static Square[] squares = new Square[9];
    public static boolean mousePressed = false;
    public static Tile selected;
    public static String input;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Base.init();
        for (int i = 0; i < 9; i++) {
            columns[i] = new Column(new Tile[0]);
            rows[i] = new Row(new Tile[0]);
            squares[i] = new Square(new Tile[0]);
        }
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Tile temp = new Tile(new int[]{i, j}, j * 9 + i, columns[i], rows[j], squares[((j / 3) * 3) + (i / 3)]);
                all[j * 9 + i] = temp;
                columns[i].addTo(temp);
                rows[j].addTo(temp);
                squares[((j / 3) * 3) + (i / 3)].addTo(temp);
            }
        }
        prepValues();
        boolean done = false;
        while (!done){
            done = prepValues2();
        }
        Base.init();
        int desiredShown = Integer.MIN_VALUE;
        while(!(desiredShown > 16 && desiredShown < 81)){
            System.out.println("Please enter the desired number of revealed Tiles (17 - 80):");
            desiredShown = sc.nextInt();
        }
        for (int i = 0; i < 81 - desiredShown; i++) {
            int index = (int) (Math.random() * 81);
            if (all[index].set){
                all[index].set = false;
                all[index].contents = new int[0];
            } else {
                i--;
            }
        }
        Base.init();
        while (true) {
            if (StdDraw.isMousePressed() && !mousePressed) {
                mousePressed = true;
                select(StdDraw.mouseX, StdDraw.mouseY);
                findSimilar();
                JFraming.createAndShowGUI();
                input = null;
                while (input == null) {
                    StdDraw.pause(5);
                }
                assignValue(input);
                Base.init();
                } else if (!StdDraw.isMousePressed() && mousePressed) {
                    mousePressed = false;
                }
            }
        }
    public static void select(double x, double y){
        selected = all[(int) (x * 9) + (int) (y * 9) * 9];
        selected.highlight(Color.YELLOW);
    }
    public static void assignValue(String str){
        if (str.length() == 0 && !selected.set){
            selected.contents = new int[0];
        }else if (str.length() == 1 && !selected.set){
            try {
                selected.contents = new int[] {Integer.parseInt(str)};
            } catch (Exception e) {
                System.out.println("Please input a proper int");
            }
        }else if (str.length() < 10 && !selected.set){
            try {
                int[] tbr = new int[0];
                for (int i = 0; i < str.length(); i++) {
                    int [] temp = new int[tbr.length + 1];
                    for (int j = 0; j < tbr.length; j++) {
                        temp[j] = tbr[j];
                    }
                    temp[tbr.length] = Integer.parseInt(str.substring(i, i + 1));
                    tbr = temp;
                }
                selected.contents = tbr;
            } catch (Exception e) {
                System.out.println("Please input a proper int array");
            }
        } else {
            System.out.println("Cant alter set tile");
        }
    }
    public static void findSimilar(){
        if (selected.contents != null && selected.contents.length == 1){
            for (Tile t: all) {
                if (t != selected && t.contents != null && t.contents.length == 1 && t.contents[0] == selected.contents[0]){
                    if ( t.col == selected.col || t.row == selected.row || t.squ == selected.squ) {
                        t.highlight(Color.RED);
                    } else {
                        t.highlight(Color.CYAN);
                    }
                }
            }
        }
    }
    public static boolean setLegal(int c, Tile tile){
            for (Tile t: all) {
                if (t != tile && t.contents != null && t.contents.length == 1 && t.contents[0] == c){
                    if ( t.col == tile.col || t.row == tile.row || t.squ == tile.squ) {
                        return false;
                    }
                }
            }
        return true;
    }
    public static void prepValues(){
        for (int i = 1; i < 10; i++) {
            int index = (int) (Math.random() * 9);
            if (squares[0].contents[index].contents == null){
                squares[0].contents[index].contents = new int[] {i};
                squares[0].contents[index].set = true;
                squares[0].contents[index].draw();
            } else {
                i--;
            }
        }
        for (int i = 1; i < 10; i++) {
            int index = (int) (Math.random() * 9);
            if (squares[4].contents[index].contents == null){
                squares[4].contents[index].contents = new int[] {i};
                squares[4].contents[index].set = true;
                squares[4].contents[index].draw();
            } else {
                i--;
            }
        }
        for (int i = 1; i < 10; i++) {
            int index = (int) (Math.random() * 9);
            if (squares[8].contents[index].contents == null){
                squares[8].contents[index].contents = new int[] {i};
                squares[8].contents[index].set = true;
                squares[8].contents[index].draw();
            } else {
                i--;
            }
        }
    }
    public static boolean prepValues2(){
        //reset Values 2
        for (int i = 0; i < 9; i++) {
            if (i % 4 != 0) {
                for (Tile t : squares[i].contents) {
                    t.contents = null;
                    t.set = false;
                    t.draw();
                }
            }
        }
        //Try Val 2 Combination
        for (int i = 0; i < 9; i++) {
            if (i % 4 != 0) {
                for (Tile t : squares[i].contents) {
                    int[] possibleVal = new int[0];
                    for (int j = 1; j < 10; j++) {
                        if (setLegal(j, t)){
                            int[] temp = new int[possibleVal.length + 1];
                            for (int k = 0; k < possibleVal.length; k++) {
                                temp[k] = possibleVal[k];
                            }
                            temp[possibleVal.length] = j;
                            possibleVal = temp;
                        }
                        else {
                        }
                    }
                    if (possibleVal.length != 0){
                        t.contents = new int[] {possibleVal[(int) (Math.random() * possibleVal.length)]};
                        t.set = true;
                        t.draw();
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}