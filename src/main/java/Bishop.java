import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Bishop extends Figure {
    private Image image;
    Bishop(String color) {
        super(color);
        loadImage();
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove() {
        int deltaX = getX() - getPreviousX();
        int deltaY = getY() - getPreviousY();
        return abs(deltaX) == abs(deltaY);
    }

    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/bishop.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }}
