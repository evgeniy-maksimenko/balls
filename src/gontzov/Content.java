package gontzov;

import javax.swing.*;

public class Content extends JFrame{
    public Content(){
        setTitle("Balls");
        setBounds(200,200,600,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new WorkedPanel());
        setVisible(true);
    }
}
