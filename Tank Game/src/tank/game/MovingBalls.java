package tank.game;

import static tank.game.Main.rand;
import static tank.game.Main.RedScore;
import static tank.game.Main.BlueScore;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MovingBalls extends JPanel implements Runnable{
    
    public Tank BlueTank = new Tank("BlueTankTV2.png");
    public Tank RedTank = new Tank("RedTankTV3.png");
    public Road road;
    
    public ArrayList<Ball>Balls=new ArrayList<>(5);
    
    public MovingBalls()
    {
        road = new Road(350,-200,2,"YellowRoadTV4.png");
        Thread thread = new Thread(road);
        thread.start();
        setSize(800, 700);
        BlueTank.pos.x = 683;
        BlueTank.pos.y = 450;
        BlueTank.Rocket.pos.x = getWidth()-50;
        BlueTank.Rocket.pos.y = 0;
        RedTank.pos.x = 0;
        RedTank.pos.y = 450;
        addKeyListener(new Redkeys());
        addKeyListener(new Bluekeys());
        addKeyListener(new Otherkeys());
    }
    
    private class Redkeys implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_A) {
                RedTank.movel();
            }
            if (e.getKeyCode()==KeyEvent.VK_D) {
                RedTank.mover();
            }
            if (e.getKeyCode()==KeyEvent.VK_CONTROL) {
                RedTank.fireBullet();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
    }
    
    private class Bluekeys implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                BlueTank.mover();
            }
            if (e.getKeyCode()==KeyEvent.VK_LEFT){
                BlueTank.movel();
            }
            if (e.getKeyCode()==KeyEvent.VK_SPACE){
                BlueTank.fireBullet();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
    }
    
    private class Otherkeys implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_R)
                {
                    RedScore = 0;
                    BlueScore = 0;
                    BlueTank.Rocket.running = false;
                    RedTank.Rocket.running = false;
                    Balls.clear();
                    Balls.add(new Ball(rand.nextInt(650)+50,100,20,Color.ORANGE));
                    Balls.add(new Ball(rand.nextInt(650)+50,120,20,Color.red));
                    Balls.add(new Ball(rand.nextInt(650)+50,140,20,Color.CYAN));
                    Balls.add(new Ball(rand.nextInt(650)+50,160,20,Color.white));
                    Balls.add(new Ball(rand.nextInt(650)+50,180,20,Color.magenta));
                    Balls.add(new Ball(rand.nextInt(650)+50,200,20,Color.PINK));
                    Balls.add(new Ball(rand.nextInt(650)+50,220,20,Color.GREEN));
                    BlueTank.pos.x = 683;
                    BlueTank.pos.y = 450;
                    BlueTank.Rocket.pos.x = getWidth()-50;
                    BlueTank.Rocket.pos.y = 0;
                    RedTank.pos.x = 0;
                    RedTank.pos.y = 450;
                    RedTank.Rocket.pos.x = 0;
                    RedTank.Rocket.pos.y = 0;
                }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
        
    }
    
    @Override
    public void  paintComponent(Graphics g)
    {
        //g.clearRect(0, 0, 800, 700);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 700);
        
        try
        {
            BufferedImage roadimg = ImageIO.read(new File(road.imagePath));
            BufferedImage imgtank = ImageIO.read(new File(BlueTank.ImagePath));
            BufferedImage imgtank2 = ImageIO.read(new File(RedTank.ImagePath));
            BufferedImage imgrocket = ImageIO.read(new File(BlueTank.Rocket.imgPath));
            BufferedImage imgrocket2 = ImageIO.read(new File(RedTank.Rocket.imgPath));
            g.drawImage(roadimg, road.pos.x, road.pos.y, null);
            g.drawImage(imgtank, BlueTank.pos.x, BlueTank.pos.y, null);
            g.drawImage(imgtank2, RedTank.pos.x, RedTank.pos.y, null);
            g.drawImage(imgrocket, BlueTank.Rocket.pos.x, BlueTank.Rocket.pos.y, null);
            g.drawImage(imgrocket2, RedTank.Rocket.pos.x, RedTank.Rocket.pos.y, null);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        
        for (Ball OneBall: Balls)
        
        {
            g.setColor(OneBall.CurrentColor);
            g.fillOval(OneBall.x,OneBall.y, 50, 50);
            if (BlueTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                BlueScore++;
                Balls.remove(OneBall);
                break;
                
            }
            if (RedTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                RedScore++;
                Balls.remove(OneBall);
                break;
                
            }   
        }
    }
    @Override
    public void run() {
    try
    {
    while(true)
    {
        for (Ball OneBall: Balls)
        {
            OneBall.move(this.getWidth());
        }    
        
        Thread.sleep(50);
        repaint();
    }
    }
    catch (InterruptedException e)
    {}
    }
    
}
