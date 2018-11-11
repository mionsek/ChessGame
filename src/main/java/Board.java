import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;


public class Board extends JPanel implements MouseListener, MouseMotionListener {

    private final int white = 0;
    private final int black = 1;
    private int currentTeam;

    private int[][] field;
    private Figure[][] figures;
    private int size;
    private int marginY = 50, marginX = 100;
    private int mX, mY;
    private boolean isMouseDrag;
    private int index;

    Board(int size) {
        this.setOpaque(false); // do sprawdzenia
        this.size = size;
        init();
        gameInit();
    }

    private void init() {
        isMouseDrag = false;
        addMouseListener(this);
        addMouseMotionListener(this);
        field = new int[8][8];
        for(int i = 0; i < 8; i++){
            for (int t = 0; t < 8; t++)
                field[t][i] = -1;
        }
//        Arrays.fill(field, 1);
        index = -1;
        currentTeam = white;
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

    private void gameInit() {
        createTeams();
        setLocationsOfFigures();
    }

    private void createTeams() {
        figures = new Figure[16][2];
        String col = "white";
        for (int x = 0; x < 2; x++) {
            if (x == black)
                col = "black";
            for (int i = 0; i < 8; i++) {
                figures[i][x] = new Pawn(col);
            }
            figures[8][x] = new Rock(col);
            figures[9][x] = new Rock(col);
            figures[10][x] = new Queen(col);
            figures[11][x] = new King(col);
            figures[12][x] = new Bishop(col);
            figures[13][x] = new Bishop(col);
            figures[14][x] = new Knight(col);
            figures[15][x] = new Knight(col);
        }

        for (Figure[] f : figures) {
            f[white].init(this.size, this.marginX, this.marginY);
            f[black].init(this.size, this.marginX, this.marginY);
        }
    }

    private void setLocationsOfFigures() {
        int locY = 0;
        for (int x = 0; x < 2; x++) {
            if (x == 1)
                locY = 7;
            for (int i = 0; i < 8; i++) {
                if (x == 0)
                    figures[i][x].setLocationOnBoard(i, 1);
                else
                    figures[i][x].setLocationOnBoard(i, 6);
            }
            figures[8][x].setLocationOnBoard(0, locY);
            figures[9][x].setLocationOnBoard(7, locY);
            figures[10][x].setLocationOnBoard(4, locY);
            figures[11][x].setLocationOnBoard(3, locY);
            figures[12][x].setLocationOnBoard(2, locY);
            figures[13][x].setLocationOnBoard(5, locY);
            figures[14][x].setLocationOnBoard(1, locY);
            figures[15][x].setLocationOnBoard(6, locY);
        }

        for (Figure[] figure : figures) {
            for (int y = 0; y < figures[0].length; y++) {
                if (figure[y].getColor().equals("white"))
                    field[figure[y].getX()][figure[y].getY()] = white;
                if (figure[y].getColor().equals("black"))
                    field[figure[y].getX()][figure[y].getY()] = black;
            }
        }
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                System.out.print(field[y][x] + "  ");
            }
            System.out.println();
        }
    }
    private void drawFigures(Graphics g) {
        for (Figure[] aWhiteTeam : figures) {
            for (int f = 0; f < figures[1].length; f++) {
                g.drawImage(aWhiteTeam[f].getImage(),
                        aWhiteTeam[f].getCX(),
                        aWhiteTeam[f].getCY(),
                        this.size, this.size, this);
            }
        }
        for (int x = 0; x < figures[1].length; x++) {
            if (index != -1 && figures[index][x].isInMove()) {
                g.drawImage(figures[index][x].getImage(),
                        figures[index][x].getCX(),
                        figures[index][x].getCY(),
                        this.size, this.size, this);
                break;
            }
        }
    }

    private void drawBoard(Graphics g) {
        int x = 0, y = 0, col = 0;
        for (int i = 0; i < 64; i++) {
            Color rectColor;
            if (col % 2 == 0) {
                rectColor = new Color(0.5f, 0.3f, 0.1f, 0.5f);
                ;
            } else {
                rectColor = Color.WHITE;
            }
            g.setColor(rectColor);
            g.fillRect(this.size * x + this.marginX, this.size * y + this.marginY, this.size, this.size);

            x++;
            if (x % 8 == 0) {
                y++;
                x = 0;
                col--;
            }
            col++;
        }
        //board
        g.setColor(Color.DARK_GRAY);
        g.drawRect(marginX, marginY, size * 8, size * 8);
    }

    // obsluga myszy

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        //get mouse x and y coords
        this.mX = e.getX();
        this.mY = e.getY();

        for (int i = 0; i < figures.length; i++) {
            for (int x = 0; x < figures[1].length; x++) {
                if (figures[i][x].getCX() < this.mX &&
                        figures[i][x].getRX() > this.mX &&
                        figures[i][x].getCY() < this.mY &&
                        figures[i][x].getBY() > this.mY) {
                    figures[i][x].setMoveTrue();
                    index = i;
                    this.isMouseDrag = true;
                    break;
                }
            }
        }
        e.consume();
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDrag) {
            boolean moved = true;
            if (figures[index][currentTeam].isInMove() && index != -1) {
                figures[index][currentTeam].convertCordToLoc();

                // część pierwsza - sprawdzenie poprawnosci ruchu oraz tego czy jest w planszy
                if (!figures[index][currentTeam].checkIfInBoard() || !figures[index][currentTeam].checkIfCanMove()) {
                    figures[index][currentTeam].restoreXY();
                    moved = false;
                }

                //część druga - sprawdzenie czy na trasie nie została napotkana jakaś figura

                int prevX = figures[index][currentTeam].getPreviousX();
                int prevY = figures[index][currentTeam].getPreviousY();
                int currX = figures[index][currentTeam].getX();
                int currY = figures[index][currentTeam].getY();

                int dx = (int) Math.signum(currX - prevX);
                int dy = (int) Math.signum(currY - prevY);

                int tx = prevX + dx;
                int ty = prevY + dy;
                if (!(figures[index][currentTeam] instanceof Knight)) {
                    while (tx != currX || ty != currY) {
                        if (field[tx][ty] != -1) {
                            figures[index][currentTeam].restoreXY();
                            moved = false;
                            break;
                        }
                        tx += dx;
                        ty += dy;
                    }
                }

                for (int i = 0; i < figures.length; i++) {
                    if (!figures[index][currentTeam].checkIfFieldEmpty(figures[i][currentTeam].getX(), figures[i][currentTeam].getY()) && i != index) {
                        figures[index][currentTeam].restoreXY();
                        moved = false;
                        break;
                    }
                }

                figures[index][currentTeam].checkIfInBoard();
                figures[index][currentTeam].convertLocToCord();
                figures[index][currentTeam].setMoveFalse();

                if (moved) {
                    updateBoard();
                    currentTeam = nextTeam(currentTeam);
                }
                repaint();
                index = -1;
            }
        }
        isMouseDrag = false;
        e.consume();
    }

    private void updateBoard(){
        int prevX = figures[index][currentTeam].getPreviousX();
        int prevY = figures[index][currentTeam].getPreviousY();
        int currX = figures[index][currentTeam].getX();
        int currY = figures[index][currentTeam].getY();

        field[prevX][prevY] = -1;
        field[currX][currY] = currentTeam;

        System.out.println();
        for (int x = 0; x < 8; x++){
            for (int y = 0; y < 8; y++){
                if (field[y][x] == -1) {
                    System.out.print(field[y][x] + "  ");
                }
                else
                    System.out.print(field[y][x] + "   ");
            }
            System.out.println();
        }
    }

    private int nextTeam(int currentTeam) {
        if (currentTeam == 1) {
            return 0;
        } else return 1;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        if (isMouseDrag && figures[index][currentTeam].isInMove()) {
            int currentMX = e.getX();
            int currentMY = e.getY();
            figures[index][currentTeam].setCoords(currentMX, currentMY);
            mX = currentMX;
            mY = currentMY;
            repaint();
            e.consume();
        }
    }

    public void mouseMoved(MouseEvent e) {
    }
}
