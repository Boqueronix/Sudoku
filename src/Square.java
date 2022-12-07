//import java.awt.*;
public class Square {
    public Tile[] contents;
    public Square(Tile [] tileArray){
        contents = tileArray;
    }
    public void addTo(Tile t){
        Tile[] temp = new Tile[contents.length + 1];
        for (int i = 0; i < contents.length; i++) {
            temp[i] = contents[i];
        }
        temp[contents.length] = t;
        contents = temp;
    }
//    public void highlight(){
//        StdDraw.setPenColor(Color.RED);
//        StdDraw.setPenRadius(0.005);
//        StdDraw.rectangle((contents[0].coords[4] + 0.5) / 9.0, (contents[0].coords[4] + 0.5) / 9.0, 1 / 6.0, 1 / 6.0);
//    }
}
