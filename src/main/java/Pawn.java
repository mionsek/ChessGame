import javax.swing.*;
import java.awt.*;

public class Pawn extends Figure {
    private Image image;
    public Pawn(String color, int size, int marginX, int marginY) {
        super(color, size, marginX, marginY);
        loadImage();
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove() {
        return false;
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/pawn.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
