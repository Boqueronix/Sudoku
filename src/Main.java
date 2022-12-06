import java.awt.*;

public class Main {
    //all Tiles
    public static Tile[] all = new Tile[81];
    //columns array, ([x][y]) [column][tile]
    public static Tile[][] columns = new Tile[9][9];
    //rows array, ([y][x]) [row][tile]
    public static Tile[][] rows = new Tile[9][9];
    //squares array [squareX][squareY][tileX][tileY]
    public static Tile[][][][] squares = new Tile[3][3][3][3];
    public static boolean mousePressed = false;
    public static Tile selected;
    public static void main(String[] args) {
        Base.init();
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Tile temp = new Tile(new int[]{i, j},i * 9 + j);
                all[j * 9 + i] = temp;
                columns[i][j] = temp;
                rows[j][i] = temp;
                squares[i / 3][j / 3][i % 3][j % 3] = temp;
            }
        }
        while (true) {
            if (StdDraw.isMousePressed() && !mousePressed) {
                mousePressed = true;
                select(StdDraw.mouseX,StdDraw.mouseY);
            } else if (!StdDraw.isMousePressed() && mousePressed) {
                mousePressed = false;
            }
        }
    }
    public static void select(double x, double y){
        if (selected != null) {
            Base.init();
        }
        selected = all[(int) (x * 9) + (int) (y * 9) * 9];
//        selected.contents = new int[] {1};
        selected.highlight(Color.RED);
    }
}