import javax.swing.*;
import java.awt.*;

class ChessFrame extends JFrame{
    private ChessPanel Panel;
    //private JFrame window;
    //private Field board[] = new Field[64];
    private Field board;
    ChessFrame(){
        Panel = new ChessPanel();
        board = new Field(70);
        //setPreferredSize(new Dimension(1000, 800));
        //window = new JFrame("Chess Application");
        //window.setSize(1000, 800);
        //window.setVisible(true);
        //drawBoard();
        //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        window.pack();
//        window.setLocationRelativeTo(null);
        this.setSize(new Dimension(1000, 800));
        this.setContentPane(board);
        //Panel.add()
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
