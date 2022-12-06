import java.awt.*;
public class Base {
    public static void init(){
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLUE);
        for (int i = 0; i < 10; i++){
            StdDraw.setPenRadius(i % 3 == 0? 0.005 : 0.002);
            StdDraw.line(i/9.0,0,i/9.0,1);
            StdDraw.line(0,i/9.0,1,i/9.0);
        }
        for (Tile t: Main.all) {
            if (t != null) {
                t.draw();
            }
        }
    }
}
