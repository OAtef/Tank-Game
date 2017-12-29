package tank.game;

import java.awt.Point;

public class Tank {
    
    public Point pos = new Point();
    public String ImagePath;
    public Bullet Rocket = new Bullet();
    public int speed;
    
    public Tank (String ImagePath)
    {
        speed=7;
        this.ImagePath=ImagePath;
        Rocket.imgPath="rocket.gif";
    }

    void mover() {
        this.pos.x+=speed;
    }
      void movel() {
        this.pos.x-=speed;
    }

    public void fireBullet() {
        
        Rocket.pos.x=this.pos.x+42;
        Rocket.pos.y=this.pos.y;
        Thread t1=new Thread (Rocket);
        if (!t1.isAlive())
        {
            t1.start();   
        }
    }
}
