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
            StdDraw.setPenColor(set? Color.black: Color.blue);
            StdDraw.text((coords[0] + 0.5) / 9.0, (coords[1] + 0.5) / 9.0, "" + contents[0]);
        }
    }
    public void highlight(Color color){
        draw();
        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(0.005);
        StdDraw.square((coords[0] + 0.5) / 9.0, (coords[1] + 0.5) / 9.0, 1 / 18.0);
    }
}
