import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Board extends JPanel {

    private AvlTree<Integer> avlTree;
    private int fistX;
    private int fistY;

    Board(int fromX, int fromY) {
        avlTree = new AvlTree<>();
        this.fistX = fromX;
        this.fistY = fromY;
    }

    public void insertNode(int data) {
        avlTree.add(data);
        this.repaint();
    }

    public void clearBoard() {
        avlTree.clear();
        repaint();
    }

    public void removeNode(int data) {
        avlTree.remove(data);
        repaint();
    }

    private void patinGeneralPath(int x, int y, Graphics2D gr2d, String data, int height) {
        Ellipse2D ellipse2D = new Ellipse2D.Double(x, y, 35.0, 35.0);
        gr2d.setColor(Color.BLACK);
        gr2d.draw(ellipse2D);
        gr2d.setColor(new Color(249, 175, 57));
        gr2d.fill(ellipse2D);
        gr2d.setColor(Color.BLACK);
        gr2d.drawString(data, x + 10, y + 20);
        gr2d.drawString(String.valueOf(height + 1), x + 40, (int) (y + 17.5));
    }

    private Graphics2D graphics2D;
    private int count = 0;

    private void paintOver(AVLNode<Integer> current, int x, int y, boolean isLeft) {
        Graphics2D g2 = graphics2D;
        int currentX;
        int currentY;
        if (current == null) {
            return;
        }
        if (current == avlTree.getRoot()) {
            currentX = x;
            currentY = y;
            patinGeneralPath(x, y, g2, String.valueOf(current.data), current.height);
        } else if (isLeft) {
            currentX = x - 150 + count;
            currentY = y + 60 + count;
            patinGeneralPath(currentX, currentY, g2, String.valueOf(current.data), current.height);
            GeneralPath generalPath = new GeneralPath();
            generalPath.moveTo(x + 17.5, y + 35);
            generalPath.lineTo(currentX + 17.5, currentY);
            generalPath.closePath();
            g2.draw(generalPath);
        } else {
            currentX = x + 150 - count;
            currentY = y + 60 + count;
            patinGeneralPath(currentX, currentY, g2, String.valueOf(current.data), current.height);
            GeneralPath generalPath = new GeneralPath();
            generalPath.moveTo(x + 17.5, y + 35);
            generalPath.lineTo(currentX + 17.5, currentY);
            generalPath.closePath();
            g2.draw(generalPath);
        }
        count += 30;
        paintOver(current.left, currentX, currentY, true);
        paintOver(current.right, currentX, currentY, false);
        count -= 30;

    }

    @Override
    protected void paintComponent(Graphics g) {
        if (avlTree.getRoot() == null) return;
        graphics2D = (Graphics2D) g;
        paintOver(avlTree.getRoot(), fistX, fistY, true);
    }

    @Override
    public String toString() {
        return avlTree.toString();
    }
}
