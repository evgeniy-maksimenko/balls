package gontzov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Ball extends JPanel implements MouseListener {

    private static int count;
    public final int id=count++;
    int dx = 0;
    int dy = 0;
    Boolean flag = false;
    Ball tmpBall;

    Color color;

    public Ball(int x, int y, int dx, int dy, int dm, Color color) {
        setBounds(x, y, dm, dm);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        addMouseListener(this);
    }

    public Ball(int x, int y, int dx, int dy, int dm) {
        Random random = new Random();
        setBounds(x, y, dm, dm);
        this.dx = dx;
        this.dy = dy;
        color = new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255));
        addMouseListener(this);
    }

    public Ball(int x, int y) {
        Random random = new Random();
        setBounds(x-20,y-20,40,40);
        dx = random.nextInt(10);
        dy = random.nextInt(10);
        color = new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255));
        addMouseListener(this);
    }

    public void move(){
        JPanel parent = (JPanel) getParent();
        int x = getX();
        int y = getY();

        if(x <= 0 || x+40 >= parent.getWidth())
            dx = -dx;
        if(y <= 0 || y+40 >= parent.getHeight())
            dy = -dy;

        x = x + dx;
        y = y + dy;


        setLocation(x,y);
        repaint();

        // *************************************************************************************************************

        for(Component component : getParent().getComponents()) {
            if(! (component instanceof Ball))
                continue;
            Ball ball = (Ball) component;
            if(this != ball) {
                double distance = Math.sqrt(Math.pow(ball.getX()-this.getX(), 2)+Math.pow(ball.getY()-this.getY(), 2));
                if(distance <= ball.getHeight()){
                    parent.remove(this);
                    flag = true;
                    tmpBall = this;
                    parent.repaint();
                }
            }
        }

        if(flag) {
            parent.add(new Ball(tmpBall.getX(), tmpBall.getY(), -dy,  -dx, 40, tmpBall.color));
            flag = false;
            tmpBall = null;
            parent.repaint();
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.fillOval(0,0,getWidth(),getHeight());
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3){
            JPanel parent = (JPanel) getParent();
            parent.remove(this);
            parent.repaint();
        } else if(e.getButton() == MouseEvent.BUTTON1) {
            JPanel parent = (JPanel) getParent();
            parent.remove(this);
            int x = getX();
            int y = getY();
            int dm = getHeight() / 2;
            parent.add(new Ball(x-20,    y, -dx,   0, dm));
            parent.add(new Ball(x+20,    y,  dx,   0, dm));
            parent.add(new Ball(   x, y-20,   0, -dy, dm));
            parent.add(new Ball(   x, y+20,   0,  dy, dm));
            parent.add(new Ball(x-20, y-20, -dx, -dy, dm));
            parent.add(new Ball(x+20, y+20,  dx,  dy, dm));
            parent.add(new Ball(x+20, y-20,  dx, -dy, dm));
            parent.add(new Ball(x-20, y+20, -dx,  dy, dm));

            parent.repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
