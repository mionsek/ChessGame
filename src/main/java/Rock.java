import javax.swing.*;
import java.awt.*;

public class Rock extends Figure {
    private Image image;
    Rock(String color) {
        super(color);
        loadImage();
    }

    public void drawFigure() {

    }

    public boolean checkIfCanMove() {
        return getX() == getPreviousX() || getY() == getPreviousY();
    }

    void loadImage() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/" + getColor() + "/rock.png"));
        image = ii.getImage();
    }

    public Image getImage(){
        return this.image;
    }
}
