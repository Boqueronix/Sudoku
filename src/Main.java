import java.awt.*;

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
        Base.init();
        for (int i = 0; i < 9; i++) {
            columns[i] = new Column(new Tile[0]);
            rows[i] = new Row(new Tile[0]);
            squares[i] = new Square(new Tile[0]);
        }
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Tile temp = new Tile(new int[]{i, j}, i * 9 + j);
                all[j * 9 + i] = temp;
                columns[i].addTo(temp);
                rows[j].addTo(temp);
                squares[((j / 3) * 3) + (i / 3)].addTo(temp);
            }
        }
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
        if (str.length() == 0){
            selected.contents = new int[0];
        }else if (str.length() == 1){
            try {
                selected.contents = new int[] {Integer.parseInt(str)};
            } catch (Exception e) {
                System.out.println("Please input a proper int");
            }
        }else if (str.length() < 10){
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
        }
    }
    public static void findSimilar(){
        if (selected.contents != null && selected.contents.length == 1){
            Tile[] similar = {selected};
            int content = selected.contents[0];
            for (Tile t:all) {
                if (!t.equals(selected) && t.contents != null && t.contents.length == 1 && t.contents[0] == content){
                    Tile[] temp = new Tile[similar.length + 1];
                    for (int i = 0; i < similar.length; i++) {
                        temp[i] = similar[i];
                    }
                    temp[similar.length] = t;
                    similar = temp;
                    t.highlight(Color.CYAN);
                }
            }
            System.out.println("Searching columns");
            // columns
            for (Column col: columns) {
                int count = 0;
                Tile[] high = new Tile[0];
                for (Tile t: col.contents) {
                    for (Tile s: similar) {
                        if (s.equals(t)){
                            System.out.println("found similar in column");
                            count++;
                            Tile[] temp = new Tile[high.length + 1];
                            for (int i = 0; i < high.length; i++) {
                                temp[i] = high[i];
                            }
                            temp[high.length] = t;
                            high = temp;
                        }
                    }
                }
                if (count >= 2){
                    for (Tile t: high){
                        if (!t.equals(selected)){
                            t.highlight(Color.RED);
                        }
                    }
                }
            }
            for (Row row: rows) {
                int count = 0;
                Tile[] high = new Tile[0];
                for (Tile t: row.contents) {
                    for (Tile s: similar) {
                        if (s.equals(t)){
                            System.out.println("found similar in row");
                            count++;
                            Tile[] temp = new Tile[high.length + 1];
                            for (int i = 0; i < high.length; i++) {
                                temp[i] = high[i];
                            }
                            temp[high.length] = t;
                            high = temp;
                        }
                    }
                }
                if (count >= 2){
                    for (Tile t: high) {
                        if (!t.equals(selected)){
                            t.highlight(Color.RED);
                        }
                    }
                }
            }
            for (Square squ: squares) {
                int count = 0;
                Tile[] high = new Tile[0];
                for (Tile t: squ.contents) {
                    for (Tile s: similar) {
                        if (s.equals(t)){
                            System.out.println("found similar in square");
                            count++;
                            Tile[] temp = new Tile[high.length + 1];
                            for (int i = 0; i < high.length; i++) {
                                temp[i] = high[i];
                            }
                            temp[high.length] = t;
                            high = temp;
                        }
                    }
                }
                if (count >= 2){
                    for (Tile t: high) {
                        if (!t.equals(selected)){
                            t.highlight(Color.RED);
                        }
                    }
                }
            }
        }
    }
}