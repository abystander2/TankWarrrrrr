package TankWarrrrrrr;

import java.awt.*;

public class Bullet{
    //子弹状态常量
    public final static int BULLET_STATE_FREE = 0;      // 空闲中
    public final static int BULLET_STATE_RUNNING = 1;   // 运行中

    //子弹攻击力常量
    public final static int BULLET_MAX_ATTACK = 100;    // 最大攻击力
    public final static int BULLET_MIN_ATTACK = 20;     // 最小攻击力

    private int x;    //子弹左上角X坐标
    private int y;    //子弹左上角Y坐标

    private int width = 10;    //子弹宽度
    private int height = 10;    //子弹高度

    private int direction;    //子弹方向
    private int speed = 16;    //子弹速度
    
    private int state = BULLET_STATE_FREE;    //子弹状态
    private Tank tank;    //所属坦克
    private int atk;    //子弹攻击力


    public Bullet(Tank tank){
        this.tank = tank;
        this.atk = GameLogic.getRandomInt(BULLET_MIN_ATTACK,BULLET_MAX_ATTACK);//子弹伤害随机
    }

    //设置子弹位置
    public void setPosition(int x,int y,int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    //功能：子弹重置
    public void reset() {
        this.x = -100;
        this.y = -100;
        this.atk = GameLogic.getRandomInt(BULLET_MIN_ATTACK,BULLET_MAX_ATTACK);     // 重新随机生成攻击力
        this.state = BULLET_STATE_FREE;
    }

    //子弹飞行
    public void fly(){
        // 设置子弹状态为运行中
        this.state = BULLET_STATE_RUNNING;
        
        //启动子弹线程
        new Thread(()->{
            while (this.state == BULLET_STATE_RUNNING) {  //tank.getState() == Tank.TANK_STATE_RUNNING &&
                try{
                    Thread.sleep(50);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                switch (direction){
                    case Tank.TANK_DIRECTION_UP:
                        if(this.y - this.speed >= 0){
                            this.y -= this.speed;
                        }
                        else{
                            this.reset();
                        }
                        break;
                    case Tank.TANK_DIRECTION_RIGHT:
                        if(this.x + this.width + this.speed <= GamePanel.GAME_ACTION_WIDTH){
                            this.x += this.speed;
                        }
                        else{
                            this.reset();
                        }
                        break;
                    case Tank.TANK_DIRECTION_DOWN:
                        if(this.y + this.height + this.speed <= GamePanel.GAME_ACTION_HEIGHT){
                            this.y += this.speed;
                        }
                        else{
                            this.reset();
                        }
                        break;
                    case Tank.TANK_DIRECTION_LEFT:
                        if(this.x >= this.speed) {
                            this.x -= this.speed;
                        }
                        else{
                            this.reset();
                        }
                        break;
                }
            }
        }).start();
    }

    // 画子弹
    public void draw(Graphics g){
        if(tank.getTankKind() == Tank.TANK_KIND_HERO) {    // 我方子弹
            g.setColor(Tank.TANK_COLOR_HERO);
        }
        else {    // 敌方子弹
            g.setColor(Tank.TANK_COLOR_ENEMY);
        }
        g.fillOval(this.x,this.y,this.width,this.height);
        // 根据攻击力画加强子弹
        g.setColor(Color.RED);
        if(this.atk == 100){
            g.fillOval(this.x,this.y,this.width,this.height);
        }
        else if(this.atk >= 90) {
            g.fillOval(this.x + 1,this.y + 1,this.width - 2,this.height - 2);
        }
        else if(this.atk >= 70){
            g.fillOval(this.x + 2,this.y + 2,this.width - 4,this.height - 4);
        }
        else if(this.atk >= 50){
            g.fillOval(this.x + 3,this.y + 3,this.width - 6,this.height - 6);
        }
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public int getDirection(){return direction;}

    public int getSpeed(){return speed;}

    public int getState(){return state;}

    public int getAtk(){return atk;}

}
