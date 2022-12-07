//import java.awt.*;

public class Column {
    public Tile[] contents;
    public Column(Tile [] tileArray){
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
//        StdDraw.rectangle((contents[0].coords[0] + 0.5) / 9.0, 0.5, 1 / 18.0, 0.5);
//    }
}
