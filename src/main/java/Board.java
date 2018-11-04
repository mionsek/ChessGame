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
        whiteTeam = new Figure[16];
        for (int i = 0; i < 8; i++){
            whiteTeam[i] = new Pawn("white");
        }
        whiteTeam[8] = new Rock("white");
        whiteTeam[9] = new Rock("white");
        whiteTeam[10] = new Queen("white");
        whiteTeam[11] = new King("white");
        whiteTeam[12] = new Bishop("white");
        whiteTeam[13] = new Bishop("white");
        whiteTeam[14] = new Knight("white");
        whiteTeam[15] = new Knight("white");

        for (Figure f: whiteTeam) {
            f.init(this.size, this.marginX, this.marginY);
        }
    }

    private void setLocationsOfFigures() {
        for (int i = 0; i < 8; i++){
            whiteTeam[i].setLocationOnBoard(i, 1);
        }
        whiteTeam[8].setLocationOnBoard(0, 0);
        whiteTeam[9].setLocationOnBoard(7, 0);
        whiteTeam[10].setLocationOnBoard(4, 0);
        whiteTeam[11].setLocationOnBoard(3, 0);
        whiteTeam[12].setLocationOnBoard(2, 0);
        whiteTeam[13].setLocationOnBoard(5, 0);
        whiteTeam[14].setLocationOnBoard(1, 0);
        whiteTeam[15].setLocationOnBoard(6, 0);
    }

private void drawFigures(Graphics g) {
    for (int i = 0; i < whiteTeam.length; i++){
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

        for (int i = 0; i < whiteTeam.length; i++) {
            if (whiteTeam[i].getCX() < this.mX &&
                    whiteTeam[i].getRX() > this.mX &&
                    whiteTeam[i].getCY() < this.mY &&
                    whiteTeam[i].getBY() > this.mY){
                whiteTeam[i].setMoveTrue();
                index = i;
                this.isMouseDrag = true;
                break;
            }
        }
        e.consume();
    }

    public void mouseReleased(MouseEvent e) {
    if (isMouseDrag){

        if (whiteTeam[index].isInMove() && index != -1){
            whiteTeam[index].convertCordToLoc();

            if (!whiteTeam[index].checkIfInBoard() || !whiteTeam[index].checkIfCanMove()){
                whiteTeam[index].restoreXY();
            }

            for (int i = 0; i < whiteTeam.length; i++){
                if (!whiteTeam[index].checkIfFieldEmpty(whiteTeam[i].getX(), whiteTeam[i].getY()) && i != index){
                    whiteTeam[index].restoreXY();
                    break;
                }
            }

            whiteTeam[index].checkIfInBoard();
            whiteTeam[index].convertLocToCord();
            whiteTeam[index].setMoveFalse();
            index = -1;

        }
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
        if (isMouseDrag && whiteTeam[index].isInMove())
        {
            int currentMX = e.getX();
            int currentMY = e.getY();
            whiteTeam[index].setCoords(currentMX, currentMY);
            mX = currentMX;
            mY = currentMY;
            repaint();
            e.consume();
        }
    }

    public void mouseMoved(MouseEvent e) { }
}
