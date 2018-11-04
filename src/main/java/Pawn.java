import javax.swing.*;
import java.awt.*;

public class Pawn extends Figure {
    private Image image;
    private boolean moved;
    public Pawn(String color) {
        super(color);
        loadImage();
        moved = false;
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove() {
        if((getX() == getPreviousX() && (getY() == getPreviousY() + 1)) ||
           (getX() == getPreviousX() && (getY() == getPreviousY() + 2) && (!moved))){
            moved = true;
            return true;
        }
        return false;
    }

    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/pawn.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
