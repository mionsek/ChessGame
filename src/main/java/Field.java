import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {

    private int size;
    private int posX;
    private int posY;
    private Color rectColor;

    private final int marginX = 100;
    private final int marginY = 50;

    Field(int size){
        this.size = size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0, y = 0, col = 0;
        for (int i = 0; i < 64; i++) {
            if (col%2 == 0){
                this.rectColor = Color.BLACK;
            }
            else {
                this.rectColor = Color.WHITE;
            }
            g.setColor(this.rectColor);
            g.fillRect(this.size*x + this.marginX, this.size*y + this.marginY, this.size, this.size);

            x++;
            if (x%8 == 0) {
                y++;
                x = 0;
                col--;
            }
            col++;
        }

        //board
        g.setColor(Color.DARK_GRAY);
        g.drawRect(marginX, marginY, size*8, size*8);
    }


    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g.drawRect (this.posX + this.marginX, this.posY+ this.marginY, this.size, this.size);
        g.setColor(this.rectColor);
        g.fillRect(this.posX+ this.marginX, this.posY + this.marginY, this.size, this.size);

    }
}
