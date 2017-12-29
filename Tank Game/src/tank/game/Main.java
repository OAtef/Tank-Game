package tank.game;

import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
    
    public static int RedScore = 0;
    public static int BlueScore = 0;
    private static JFrame jf = new JFrame();
    private static JLabel red = new JLabel();
    private static JLabel blue = new JLabel();
    public static Random rand = new Random();
    
    public static void main(String[] args) {

        
        jf.setSize(800, 700);
        jf.setTitle("Tank Game V1.5");
        jf.setResizable(false);
        
        MovingBalls mb=new MovingBalls();
        
        calc x = new calc();
        Thread thread = new Thread(x);
        jf.add(x,BorderLayout.NORTH);
        thread.start();
        
        mb.Balls.add(new Ball(rand.nextInt(650)+50,100,20,Color.ORANGE));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,120,20,Color.red));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,140,20,Color.CYAN));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,160,20,Color.white));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,180,20,Color.magenta));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,200,20,Color.PINK));
        mb.Balls.add(new Ball(rand.nextInt(650)+50,220,20,Color.GREEN));
        
        
        /*mb.Balls.add(new Ball(100,100,20,Color.ORANGE));
        mb.Balls.add(new Ball(160,160,15,Color.red));
        mb.Balls.add(new Ball(180,180,10,Color.CYAN));
        mb.Balls.add(new Ball(30,90,25,Color.white));
        mb.Balls.add(new Ball(200,185,40,Color.magenta));
        mb.Balls.add(new Ball(350,70,35,Color.PINK));
        mb.Balls.add(new Ball(40,50,30,Color.GREEN));*/
        
        
        jf.add(mb,BorderLayout.CENTER);
        mb.setFocusable(true);
        Thread t1=new Thread(mb);
        t1.start();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
    
    private static class calc extends JPanel implements Runnable{
        
        @Override
        public void run()
        {
            add(red);
            add(blue);
            
            while (true)
            {
                red.setText(""+RedScore);
                blue.setText(""+BlueScore);
            }
            
        }
    }   
}