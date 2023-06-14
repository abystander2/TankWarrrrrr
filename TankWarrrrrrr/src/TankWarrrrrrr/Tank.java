package TankWarrrrrrr;
import java.awt.*;
import java.util.Vector;

public class Tank{
    //坦克类别常量
    public final static int TANK_KIND_HERO = 0;                 // 我方坦克
    public final static int TANK_KIND_ENEMY = 1;                // 敌方坦克

    //坦克颜色常量
    public final static Color TANK_COLOR_HERO = Color.YELLOW;   // 我方坦克颜色
    public final static Color TANK_COLOR_ENEMY = Color.CYAN;    // 敌方坦克颜色

    //坦克方向常量
    public final static int TANK_DIRECTION_UP = 0;              // 向上
    public final static int TANK_DIRECTION_RIGHT = 1;           // 向右
    public final static int TANK_DIRECTION_DOWN = 2;            // 向下
    public final static int TANK_DIRECTION_LEFT = 3;            // 向左

    //坦克状态常量
    public final static int TANK_STATE_FREE = 0;                // 空闲中
    public final static int TANK_STATE_RUNNING = 1;             // 运行中

    //坦克最大血量值
    public final static int TANK_MAX_BLOOD = 100;

    //坦克尺寸常量
    public final static int TANK_WIDTH = 40;
    public final static int TANK_HEIGHT = 40;

    private int x;    //坦克左上角X坐标 
    private int y;    //坦克左上角Y坐标
    private int speed = 8;    //坦克速度
    private int hp = TANK_MAX_BLOOD;    //坦克血量
    private int direction = TANK_DIRECTION_UP;    //坦克方向
    private int tankKind = TANK_KIND_HERO;    //坦克类别
    private int state = TANK_STATE_FREE;    //坦克状态

    //子弹池（考虑线程安全用Vector，没用ArrayList）
    private Vector<Bullet> bulletPool = new Vector<>();

    //上次射击时间（用来设置2次射击最小时间间隔，禁止连续射击）
    private long lastShootTime = System.currentTimeMillis();


    public Tank(int tankKind, int x, int y, int direction){
        this.tankKind = tankKind;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void draw(Graphics g){
        // 绘制血条
        g.setColor(Color.YELLOW);
        g.fill3DRect(this.x,this.y,TANK_WIDTH,3,false);   // 底色
        g.setColor(Color.RED);
        g.fill3DRect(this.x,this.y,(hp * TANK_WIDTH)/TANK_MAX_BLOOD,3,false);   // 血量（计算宽度时由于都是int类型，因此除法是放到最后计算的，防止出现结果为0的问题【int取整了】）
        g.setColor(Color.WHITE);
        int tankToBloodHeight = 5;  // 坦克到血条的高度
        // 设置坦克颜色
        if(this.tankKind == TANK_KIND_HERO) {    // 我方坦克颜色
            g.setColor(TANK_COLOR_HERO);
        }
        else {    // 敌方坦克颜色
            g.setColor(TANK_COLOR_ENEMY);
        }

        // 根据方向开始绘制坦克
        switch (direction){
            case TANK_DIRECTION_UP:
                g.fill3DRect(this.x + tankToBloodHeight,this.y + tankToBloodHeight,7,30,false);            // 左边轮子
                g.fill3DRect(this.x + 23 + tankToBloodHeight,this.y + tankToBloodHeight,7,30,false);       // 右边轮子
                g.fill3DRect(this.x + 7 + tankToBloodHeight,this.y + 5 + tankToBloodHeight,16,20,false);   // 驾驶室
                g.fillOval(this.x + 9 + tankToBloodHeight,this.y + 9 + tankToBloodHeight,12,12);                  // 炮台
                g.fill3DRect(x + 14 + tankToBloodHeight,y + tankToBloodHeight,3,15,false);                 // 炮管
                break;
            case TANK_DIRECTION_RIGHT:
                g.fill3DRect(this.x + tankToBloodHeight,this.y + tankToBloodHeight,30,7,false);            // 上边轮子
                g.fill3DRect(this.x + tankToBloodHeight,this.y + 23 + tankToBloodHeight,30,7,false);       // 下边轮子
                g.fill3DRect(this.x + 5 + tankToBloodHeight,this.y + 7 + tankToBloodHeight,20,16,false);   // 驾驶室
                g.fillOval(this.x + 9 + tankToBloodHeight,this.y + 9 + tankToBloodHeight,12,12);                  // 炮台
                g.fill3DRect(x + 15 + tankToBloodHeight,y + 14 + tankToBloodHeight,15,3,false);            // 炮管
                break;
            case TANK_DIRECTION_DOWN:
                g.fill3DRect(this.x + tankToBloodHeight,this.y + tankToBloodHeight,7,30,false);            // 左边轮子
                g.fill3DRect(this.x + 23 + tankToBloodHeight,this.y + tankToBloodHeight,7,30,false);       // 右边轮子
                g.fill3DRect(this.x + 7 + tankToBloodHeight,this.y + 5 + tankToBloodHeight,16,20,false);   // 驾驶室
                g.fillOval(this.x + 9 + tankToBloodHeight,this.y + 9 + tankToBloodHeight,12,12);                  // 炮台
                g.fill3DRect(x + 14 + tankToBloodHeight,y + 15 + tankToBloodHeight,3,15,false);            // 炮管
                break;
            case TANK_DIRECTION_LEFT:
                g.fill3DRect(this.x + tankToBloodHeight,this.y + tankToBloodHeight,30,7,false);            // 上边轮子
                g.fill3DRect(this.x + tankToBloodHeight,this.y + 23 + tankToBloodHeight,30,7,false);       // 下边轮子
                g.fill3DRect(this.x + 5 + tankToBloodHeight,this.y + 7 + tankToBloodHeight,20,16,false);   // 驾驶室
                g.fillOval(this.x + 9 + tankToBloodHeight,this.y + 9 + tankToBloodHeight,12,12);                  // 炮台
                g.fill3DRect(x + tankToBloodHeight,y + 14 + tankToBloodHeight,15,3,false);                 // 炮管
                break;
        }

    }

    //坦克移动，direction为方向
    public void move(int direction){
        if(this.direction == direction) {     // 方向相同，加速前进（要判断边界及是否碰撞到别的对象）
            if(!GameLogic.getInstance().tankMoveCollide(this)) {   // 没有碰撞，可以移动
                switch (direction){
                    case TANK_DIRECTION_UP:
                        this.y -= this.speed;
                        break;
                    case TANK_DIRECTION_RIGHT:
                        this.x += this.speed;
                        break;
                    case TANK_DIRECTION_DOWN:
                        this.y += this.speed;
                        break;
                    case TANK_DIRECTION_LEFT:
                        this.x -= this.speed;
                        break;
                }
            }
        }
        else {    // 方向不同，仅调整方向不前进
            this.direction = direction;
        }
    }

    //坦克射击
    public void shoot(){
        // 禁止连续射击
        long curShootTime = System.currentTimeMillis();
        if((curShootTime - this.lastShootTime) >= 500) {  // 2发/秒
            this.lastShootTime = curShootTime;
        }
        else{
            return;
        }

        // 在子弹池中寻找空闲的子弹
        Bullet bullet = null;
        for (int i = 0; i < bulletPool.size(); i++){
            Bullet tmpBullet = bulletPool.get(i);
            if(tmpBullet.getState() == Bullet.BULLET_STATE_FREE) {
                bullet = tmpBullet;
                break;
            }
        }

        // 没有就增加一个
        if(bullet == null){
            bullet = new Bullet(this);
            bulletPool.add(bullet);
        }

        // 设置子弹位置
        switch (this.direction){
            case TANK_DIRECTION_UP:
                bullet.setPosition(this.x + TANK_WIDTH/2 - bullet.getWidth()/2,this.y - bullet.getHeight(),this.direction);
                break;
            case TANK_DIRECTION_RIGHT:
                bullet.setPosition(this.x + TANK_WIDTH,this.y + TANK_HEIGHT/2 - bullet.getHeight()/2,this.direction);
                break;
            case TANK_DIRECTION_DOWN:
                bullet.setPosition(this.x + TANK_WIDTH/2 - bullet.getWidth()/2,this.y + TANK_HEIGHT,this.direction);
                break;
            case TANK_DIRECTION_LEFT:
                bullet.setPosition(this.x - bullet.getWidth(),this.y + TANK_HEIGHT/2 - bullet.getHeight()/2,this.direction);
                break;
        }

        // 让子弹飞一会
        bullet.fly();
    }

    //坦克受到伤害
    public void hurt(Bullet bullet){
        this.hp -= bullet.getAtk();
        if(this.hp < 0){this.hp = 0;}
    }

    //坦克是否死亡
    public boolean isDead(){
        return this.hp <= 0;
    }

    //坦克开始工作
    public void work() {
        this.state = TANK_STATE_RUNNING;
    }

    //坦克重置
    public void reset(){
        this.setX(-100);
        this.setY(-100);
        this.setHp(Tank.TANK_MAX_BLOOD);
        this.setState(TANK_STATE_FREE);
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public int getSpeed(){return speed;}

    public void setSpeed(int speed){this.speed = speed;}

    public int getHp(){return hp;}

    public void setHp(int hp){this.hp = hp;}

    public int getDirection(){return direction;}

    public void setDirection(int direction){this.direction = direction;}

    public int getState(){return state;}

    public void setState(int state){this.state = state;}

    public int getTankKind(){return tankKind;}

    public Vector<Bullet> getBulletPool(){return bulletPool;}

}
