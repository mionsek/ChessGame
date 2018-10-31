import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private Pawn pawn;
    private int size;

    Board(int size){
        this.size = size;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        gameInit();
        drawBoard(g2d);
        drawFigures(g2d);
    }

    private void gameInit(){
        pawn = new Pawn(1, 1, "Pawn");
        int i = 4;
    }

    private void drawFigures(Graphics g) {
        g.drawImage(pawn.getImage(), 100,100,50,50, this);
    }

    private void drawBoard(Graphics g) {
        int x = 0, y = 0, col = 0, marginY = 50, marginX = 100;
        for (int i = 0; i < 64; i++) {
            Color rectColor;
            if (col%2 == 0){
                rectColor = new Color(0.5f, 0.3f, 0.1f, 0.5f);;
            }
            else {
                rectColor = Color.WHITE;
            }
            g.setColor(rectColor);
            g.fillRect(this.size*x + marginX, this.size*y + marginY, this.size, this.size);

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
}
