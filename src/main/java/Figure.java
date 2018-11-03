import java.awt.*;

public abstract class Figure {
    // X, Y - fields 0-8
    // cordXY - on map 0-1000
    private int x, y, cordX, cordY;
    private String color;
    private int size, marginX, marginY;
    private int previousX, previousY;
    private boolean isInMove;

    Figure(String color, int size, int marginX, int marginY)
    {
        this.color = color;
        this.size = size;
        this.marginX = marginX;
        this.marginY = marginY;
        this.isInMove = false;
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

    String getColor() { return this.color;}

    public boolean isInMove(){
        return this.isInMove;
    }

    public void setMoveTrue(){
        this.isInMove = true;
    }
    public void setMoveFalse(){
        this.isInMove = false;
    }

    abstract public void drawFigure();

    void setLocationOnBoard(int loc_x, int loc_y){
        this.x = loc_x;
        this.y = loc_y;

        convertLocToCord();
    }

    void convertLocToCord(){
        this.cordX = this.x * this.size + this.marginX;
        this.cordY = this.y * this.size + this.marginY;
    }

    void convertCordToLoc(){
        this.previousX = this.x;
        this.previousY = this.y;

        double tx = (double)(this.cordX - this.marginX + this.size / 2) / (double) this.size;
        double ty = (double)(this.cordY - this.marginY + this.size / 2) / (double) this.size;

        if(tx < 0.0 || ty < 0.0) {
            this.x = -1;
            this.y = -1;
        }
        else {
            this.x = (this.cordX - this.marginX + this.size / 2) / this.size;
            this.y = (this.cordY - this.marginY + this.size / 2) / this.size;
        }
    }

    void restoreXY(){
        this.x = previousX;
        this.y = previousY;
    }

    // center = size/2
    void setCoords(int CX, int CY){
        this.cordX = CX - this.size / 2 ;
        this.cordY = CY - this.size / 2 ;
    }

    void checkIfInBoard(){
        if (this.x >= 0 && this.x <= 7 && this.y >= 0 && this.y <= 7){
            return;
        }
        else {
            restoreXY();
        }
    }

    abstract public boolean checkIfCanMove();
    abstract public Image getImage();

}
