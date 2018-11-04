import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Queen extends Figure {
    private Image image;
    Queen(String color) {
        super(color);
        loadImage();
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove()
    {
        int deltaX = abs(getX() - getPreviousX());
        int deltaY = abs(getY() - getPreviousY());

        return (deltaX == 0 || deltaY == 0) || deltaX == deltaY;
    }


    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/queen.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
