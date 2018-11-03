import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class Board extends JPanel implements MouseListener, MouseMotionListener {

    private Figure[] whiteTeam;
    private Figure[] blackTeam;
    private Pawn pawn;
    private int size;
    private int marginY = 50, marginX = 100;
    private int mX, mY;
    private boolean isMouseDrag;
    private int index;

    Board(int size){
        this.setOpaque(false); // do sprawdzenia
        this.size = size;
        init();
        gameInit();
    }

    private void init() {
        isMouseDrag = false;
        addMouseListener(this);
        addMouseMotionListener(this);
        index = -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        drawBoard(g2d);
        drawFigures(g2d);
    }

    private void gameInit(){
        createTeams();
        setLocationsOfFigures();
        int i = 4;
    }

    private void createTeams() {
        whiteTeam = new Figure[8];
        for (int i = 0; i < 8; i++){
            whiteTeam[i] = new Pawn("white", this.size, this.marginX, this.marginY);
        }
    }

    private void setLocationsOfFigures() {
        for (int i = 0; i < 8; i++){
            whiteTeam[i].setLocationOnBoard(i, 0);
        }
    }

private void drawFigures(Graphics g) {
    for (int i = 0; i < 8; i++){
        g.drawImage(whiteTeam[i].getImage(),
                whiteTeam[i].getCX(),
                whiteTeam[i].getCY(),
                this.size, this.size, this);
    }
}

    private void drawBoard(Graphics g) {
        int x = 0, y = 0, col = 0;
        for (int i = 0; i < 64; i++) {
            Color rectColor;
            if (col%2 == 0){
                rectColor = new Color(0.5f, 0.3f, 0.1f, 0.5f);;
            }
            else {
                rectColor = Color.WHITE;
            }
            g.setColor(rectColor);
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

    // obsluga myszy

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        //get mouse x and y coords
        this.mX = e.getX();
        this.mY = e.getY();

        for(Figure f: whiteTeam){
            if (f.getCX() < this.mX &&
                    f.getRX() > this.mX &&
                    f.getCY() < this.mY &&
                    f.getBY() > this.mY){
                f.setMoveTrue();
                this.isMouseDrag = true;
                break;
            }
        }
/*
        for (int i = 0; i < whiteTeam.length; i++) {
            if (whiteTeam[i].getCX() < this.mX &&
                    whiteTeam[i].getRX() > this.mX &&
                    whiteTeam[i].getY() < this.mY &&
                    whiteTeam[i].getBY() > this.mY){
                whiteTeam[i].setMoveTrue();
                index = i;
                this.isMouseDrag = true;
                break;
            }

        }
        */
        e.consume();
    }

    public void mouseReleased(MouseEvent e) {
    if (isMouseDrag){
        for(Figure f : whiteTeam){
            if(f.isInMove()){
                f.convertCordToLoc();
                f.checkIfInBoard();
                f.convertLocToCord();
                f.setMoveFalse();
                break;
            }
        }
/*
        if (whiteTeam[index].isInMove() && index != -1){
            whiteTeam[index].convertCordToLoc();
            whiteTeam[index].checkIfInBoard();
            whiteTeam[index].convertLocToCord();
            whiteTeam[index].setMoveFalse();
            index = -1;

        }
*/
        repaint();
    }
        isMouseDrag = false;
        e.consume();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
// && whiteTeam[index].isInMove()
        if (isMouseDrag)
        {
            int currentMX = e.getX();
            int currentMY = e.getY();

            for(Figure f : whiteTeam) {
                if (f.isInMove()) {
                    f.setCoords(currentMX, currentMY);
                    break;
                }
            }

//                    whiteTeam[index].setCoords(currentMX, currentMY);
            mX = currentMX;
            mY = currentMY;
            repaint();
            e.consume();
        }
    }

    public void mouseMoved(MouseEvent e) { }
}
