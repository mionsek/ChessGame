import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Knight extends Figure {
    private Image image;
    Knight(String color) {
        super(color);
        loadImage();
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove() {
        int deltaX = abs(getX() - getPreviousX());
        int deltaY = abs(getY() - getPreviousY());

        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/knight.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
