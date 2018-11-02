import javax.swing.*;
import java.awt.*;

class ChessFrame extends JFrame{
    ChessFrame(){
        Board board = new Board(70);
        board.setOpaque(false); // tez nie dziala
        this.setSize(new Dimension(1000, 800));
        this.getContentPane().add(board);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
