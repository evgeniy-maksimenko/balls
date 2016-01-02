package gontzov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class WorkedPanel extends JPanel implements MouseListener {

    JTextField txt;

    public WorkedPanel() {
        setLayout(null);
        txt = new JTextField();
        txt.setBounds(10,10,50,20);
        add(txt);
        addMouseListener(this);

        Timer timer = new Timer(20, new MoveAction());
        timer.start();
    }

    class MoveAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            move();
        }
    }

    public void move(){
        int count = getComponentCount() - 1;
        txt.setText("" + count);

        for(Component component : getComponents()) {
            if(! (component instanceof Ball))
                continue;
            Ball ball = (Ball) component;
            ball.move();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){
            add(new Ball(mouseEvent.getX(), mouseEvent.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
