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
        MediaTracker mt = new MediaTracker(this);

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
            whiteTeam[i] = new Pawn("white", this.size, this.marginX, this.marginY);
        }
        pawn = new Pawn("white", this.size, this.marginX, this.marginY);
//        setLocationsOfFigures();
    }

    private void setLocationsOfFigures() {
        for (int i = 0; i < 8; i++){
            whiteTeam[i].setLocationOnBoard(i, 0);
        }
        pawn.setLocationOnBoard(1, 2);
    }

/*
    private void drawFigures(Graphics g) {
        g.drawImage(pawn.getImage(), 100,100,50,50, this);
    }
*/
private void drawFigures(Graphics g) {
    for (int i = 0; i < 8; i++){
        whiteTeam[i].convertLocToCord();
        g.drawImage(whiteTeam[i].getImage(),
                whiteTeam[i].getCX(),
                whiteTeam[i].getCY(),
                this.size, this.size, this);
    }
//    pawn.convertLocToCord();
    g.drawImage(pawn.getImage(), pawn.getCX(), pawn.getCY(), this.size, this.size, this);
}

    private int Xconv(int x){
        return x * this.size + this.marginX;
    }

    private int Yconv(int y){
        return y * this.size + this.marginY;
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

        if (pawn.getCX() < mX && pawn.getRX() > mX && pawn.getY() < mY && pawn.getBY() > mY){
            this.isMouseDrag = true;
        }

//        if ((Xconv(pawn.getX()) < mX) && (Xconv(pawn.getX()) + this.size > mX) && (Yconv(pawn.getY()) < mY && (Yconv(pawn.getY()) + this.size > mY))){
//             isMouseDrag = true;
//        }
        //System.out.println("mX = " + mX + "pos X =  " + pawn.getX() + this.size  +" mY = " + mY + " pos X =  "+ pawn.getY() + this.size + " mouse drag = " + isMouseDrag);

        System.out.println("MX = " + mX + "  MY = " + mY);
        System.out.println("LX = " + Xconv(pawn.getX()) + "  LY = " + Yconv(pawn.getY()));
        System.out.println("RX = " + Xconv(pawn.getX()) + size + "  RY = " + Yconv(pawn.getY()) + size);
        System.out.println("Drag = " + isMouseDrag);
        e.consume();

    }

    public void mouseReleased(MouseEvent e) {
    if (isMouseDrag){
        int currentMX = e.getX();
        int currentMY = e.getY();

        pawn.convertCordToLoc();
        pawn.convertLocToCord();
//        pawn.setCoords(currentMX - this.size / 2 , currentMY - this.size / 2);        mX = currentMX;
        mX = currentMX;
        mY = currentMY;
        repaint();
        e.consume();

    }
        isMouseDrag = false;
        e.consume();
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        if (isMouseDrag)
        {
            int currentMX = e.getX();
            int currentMY = e.getY();
            pawn.setCoords(currentMX, currentMY);

            mX = currentMX;
            mY = currentMY;
            repaint();
            e.consume();
        }


    }

    public void mouseMoved(MouseEvent e) {

    }
}
