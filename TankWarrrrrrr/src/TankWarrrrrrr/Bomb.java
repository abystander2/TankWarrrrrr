package TankWarrrrrrr;

import java.awt.*;

public class Bomb{
    //爆炸状态常量
    public final static int BOMB_STATE_FREE = 0;      // 空闲中
    public final static int BOMB_STATE_RUNNING = 1;   // 运行中

    private int x;    //爆炸左上角X坐标
    private int y;    //爆炸左上角Y坐标
    private int width = 20;    //爆炸宽度
    private int height = 20;    //爆炸高度

    private int state = BOMB_STATE_FREE;    //状态

    //爆炸进度，根据它来决定显示哪种爆炸形状，-1-不显示爆炸，0-准备显示，1-显示第一种爆炸形状，2-显示第二种爆炸形状，3-显示第三种爆炸形状
    public int bombProgress = -1;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void work(){
        this.state = BOMB_STATE_RUNNING;        // 设置爆炸状态为运行中
        bombProgress = 0;        // 爆炸进度，准备显示
    }

    //重置爆炸
    public void reset(){
        this.setX(-100);
        this.setY(-100);
        this.state = BOMB_STATE_FREE;
        this.bombProgress = -1;
    }

    // 画爆炸，每帧显示一种形状，共三种，即3帧结束爆炸
    public void draw(Graphics g) {
        // 进度走起
        this.bombProgress++;

        // 根据进度显示某种爆炸形状
        switch (this.bombProgress){
            case 1:         // 第一种形状
                g.setColor(Color.WHITE);
                g.fillRoundRect(this.x + 5,this.y + 5,20,20,10,10);
                g.fillOval(this.x + 10,this.y,10,30);
                g.setColor(Color.YELLOW);
                g.drawOval(this.x + 10,this.y,10,30);
                g.setColor(Color.WHITE);
                g.fillOval(this.x,this.y + 10,30,10);
                g.setColor(Color.YELLOW);
                g.drawOval(this.x,this.y + 10,30,10);
                break;
            case 2:         // 第二种形状
                g.setColor(Color.WHITE);
                g.fillOval(this.x + 12,this.y + 5,6,20);
                g.setColor(Color.YELLOW);
                g.drawOval(this.x + 12,this.y + 5,6,20);
                g.setColor(Color.WHITE);
                g.fillOval(this.x + 5,this.y + 12,20,6);
                g.setColor(Color.YELLOW);
                g.drawOval(this.x + 5,this.y + 12,20,6);
                break;
            case 3:         // 第三种形状
                g.setColor(Color.WHITE);
                g.fillOval(this.x + 10,this.y + 10,10,10);
                g.setColor(Color.YELLOW);
                g.drawOval(this.x + 10,this.y + 10,10,10);
                break;
            default:
                this.reset();
                break;
        }
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public int getWidth(){return width;}

    public void setWidth(int width){this.width = width;}

    public int getHeight(){return height;}

    public void setHeight(int height){this.height = height;}

    public int getState(){return state;}

}
