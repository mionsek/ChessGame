public abstract class Figure {

    private int x, y;
    private String name;

    public Figure(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX(){
        return this.x;
    }

    private int getY(){
        return this.y;
    }


    abstract public void drawFigure();
    abstract public void setLocation(int x, int y);
    abstract public boolean checkIfCanMove();

}
