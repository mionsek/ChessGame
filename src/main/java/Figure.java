import java.awt.*;

public abstract class Figure {
    // X, Y - fields 0-8
    // cordXY - on map 0-1000
    private int x, y, cordX, cordY;
    private String color;
    private int size, marginX, marginY;

    Figure(String color, int size, int marginX, int marginY)
    {
        this.color = color;
        this.size = size;
        this.marginX = marginX;
        this.marginY = marginY;
    }

    int getX(){
        return this.x;
    }

    int getY(){
        return this.y;
    }

    int getCX(){ return this.cordX;}

    int getCY(){ return this.cordY;}

    int getRX() {return this.cordX + this.size;};

    int getBY(){ return this.cordY + this.size;}

    abstract public void drawFigure();

    void setLocationOnBoard(int loc_x, int loc_y){
        this.x = loc_x;
        this.y = loc_y;
    }

    void convertLocToCord(){
        this.cordX = this.x * this.size + this.marginX;
        this.cordY = this.y * this.size + this.marginY;
    }

    void convertCordToLoc(){
        this.x = (this.cordX - this.marginX + this.size / 2) / this.size;
        this.y = (this.cordY - this.marginY + this.size / 2) / this.size;
    }


    // center = size/2
    public void setCoords(int CX, int CY){
        this.cordX = CX - this.size / 2 ;
        this.cordY = CY - this.size / 2 ;
    }

    abstract public boolean checkIfCanMove();
    abstract public Image getImage();

}
