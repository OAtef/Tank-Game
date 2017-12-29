package tank.game;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Road implements Runnable{
    
    public Point pos = new Point();
    public String imagePath;
    public int speed;
    
    public Road(int x, int y, int S, String Path){
        speed = S;
        pos.x = x;
        pos.y = y;
        imagePath = Path;
    }
    
    public void Run(){
        pos.y += speed;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            if (i > 100) {
                i = 0;
                pos.y = -200;
            }
            i++;
            Run();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Road.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
