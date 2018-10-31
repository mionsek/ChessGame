import javax.swing.*;
import java.awt.*;

class ChessFrame extends JFrame{
    private Board board;
    ChessFrame(){
        board = new Board(70);
        this.setSize(new Dimension(1000, 800));
        //this.getContentPane().add(figures);
        this.getContentPane().add(board);
        this.getContentPane().setBackground(Color.WHITE);
        //this.pack();
        //this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
