import javax.swing.*;
import java.awt.*;

public class ChessPanel extends JPanel {
    ChessPanel() {
        // set a preferred size for the custom panel.
        //setPreferredSize(new Dimension(420,420));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("BLAH", 20, 20);
        g.drawRect(200, 200, 200, 200);
    }
}

