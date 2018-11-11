import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class King extends Figure {
    private Image image;
    King(String color) {
        super(color);
        this.loadImage();
    }

    public void drawFigure() {

    }


    public boolean checkIfCanMove() {
        int deltaX = abs(getX() - getPreviousX());
        int deltaY = abs(getY() - getPreviousY());

        return (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || deltaX == 1 && deltaY == 1;
    }
    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/king.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
