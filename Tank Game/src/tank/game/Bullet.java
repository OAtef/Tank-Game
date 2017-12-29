package tank.game;

import java.awt.Color;
import java.awt.Point;

public class Bullet implements Runnable{
    
    public Point pos = new Point();
    public Color CurrentColor;
    public int Speed;
    public String imgPath;
    public boolean running = true;
    
    public Bullet()
    {
        Speed=8;
    }
    
    public  void  move()
    {
        pos.y-=Speed;
    }

    @Override
    public synchronized void run() {
        running = true;
        for (int i=0;i<70;i++)
        {
            if(running == false)
            {
                break;
            }
            move();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                
            }
        }
        running = true;
    }
}
