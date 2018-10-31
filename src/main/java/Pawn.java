import javax.swing.*;
import java.awt.*;

public class Pawn extends Figure {
    private Image image;
    public Pawn(int x, int y, String name) {
        super(x, y, name);
        loadImage();
    }

    public void drawFigure() {

    }

    public void setLocation(int x, int y) {

    }

    public boolean checkIfCanMove() {
        return false;
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource("/white/pawn.png"));

        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
