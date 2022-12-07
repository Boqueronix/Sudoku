import java.awt.*;

public class Tile {
    public int[] coords;
    public int id;
    public int[] contents;
    public boolean set = false;
    public Tile(int[] c, int id){
        coords = c;
        this.id = id;
    }
    public void draw(){
        if (contents != null && contents.length == 1){
            StdDraw.setFont(new Font("Sans Serif", Font.PLAIN, 30));
            StdDraw.setPenColor(set? Color.black: Color.blue);
            StdDraw.text((coords[0] + 0.5) / 9.0, (coords[1] + 0.45) / 9.0, "" + contents[0]);
        } else if (contents != null){
            StdDraw.setFont(new Font("Sans Serif", Font.PLAIN, 10));
            StdDraw.setPenColor(Color.blue);
            String str0 = "";
            String str1 = "";
            String str2 = "";
            for (int i = 0; i < contents.length; i++) {
                if (i / 3 == 0){
                    str0 += contents[i] + "   ";
                } else if (i / 3 == 1) {
                    str1 += contents[i] + "   ";
                } else {
                    str2 += contents[i] + "   ";
                }
            }
            StdDraw.text((coords[0] + 0.5) / 9.0, (coords[1] + 0.8) / 9.0, str0.strip());
            StdDraw.text((coords[0] + 0.5) / 9.0, (coords[1] + 0.5) / 9.0, str1.strip());
            StdDraw.text((coords[0] + 0.5) / 9.0, (coords[1] + 0.2) / 9.0, str2.strip());
            System.out.println(str0.strip().length());
        }
    }
    public void highlight(Color color){
        draw();
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.005);
        StdDraw.square((coords[0] + 0.5) / 9.0, (coords[1] + 0.5) / 9.0, 1 / 18.0);
    }
    public String toString(){
        return "Tile at column: " + coords[0] + ", and row: " + coords[1];
    }
}
