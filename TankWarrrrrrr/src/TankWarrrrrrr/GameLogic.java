package TankWarrrrrrr;

public class GameLogic{
    private static GameLogic instance;		//创建唯一实例
    private GamePanel gamePanel;    	//创建游戏面板


    public GameLogic(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    //得到唯一实例
    public static GameLogic getInstance(){
        return instance;
    }

    //设置唯一实例
    public static void setInstance(GameLogic gameLogic){
        instance = gameLogic;
    }

    //判断矩形1是否碰撞到矩形2
    public boolean rectCollideRect(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
    	// 判断原理（1、矩形1的四个顶点是否落在矩形2里；2、矩形2的四个顶点是否落在矩形1里。若高度或宽阔相等时特殊判断）
        //挨着不算碰撞
    	// 判断矩形1的四个顶点是否落在矩形2里
        if((x1 > x2 && x1 < x2 + w2 && y1 > y2 && y1 < y2 + h2)
                || (x1 + w1 > x2 && x1 + w1 < x2 + w2 && y1 > y2 && y1 < y2 + h2)
                || (x1 > x2 && x1 < x2 + w2 && y1 + h1 > y2 && y1 + h1 < y2 + h2)
                || (x1 + w1 > x2 && x1 + w1 < x2 + w2 && y1 + h1 > y2 && y1 + h1 < y2 + h2))
        {return true;}

        // 判断矩形2的四个顶点是否落在矩形1里
        if((x2 > x1 && x2 < x1 + w1 && y2 > y1 && y2 < y1 + h1)
                || (x2 + w2 > x1 && x2 + w2 < x1 + w1 && y2 > y1 && y2 < y1 + h1)
                || (x2 > x1 && x2 < x1 + w1 && y2 + h2 > y1 && y2 + h2 < y1 + h1)
                || (x2 + w2 > x1 && x2 + w2 < x1 + w1 && y2 + h2 > y1 && y2 + h2 < y1 + h1))
        {return true;}

        // 特殊情况处理
        // 若2个矩形的宽度相等时
        if(w1 == w2){if(x1 == x2 && x1 + w1 == x2 + w2 && ((y1 > y2 && y1 < y2 + h2) || (y1 + h1 > y2 && y1 + h1 < y2 + h2))){return true;}}
        // 若2个矩形的高度相等时
        if(h1 == h2){if(y1 == y2 && y1 + h1 == y2 + h2 && ((x1 > x2 && x1 < x2 + w2) || (x1 + w1 > x2 && x1 + w1 < x2 + w2))){return true;}}
        // 2个矩形完全重合
        if(x1 == x2 && y1 == y2 && w1 == w2 && h1 == h2){return true;}
        return false;
    }

    //判断坦克移动时的下一个位置是否碰撞到任何对象
    public boolean tankMoveCollide(Tank tank){
        int newX = tank.getX();
        int newY = tank.getY();

        // 得到移动后的新坐标
        switch (tank.getDirection()){
            case Tank.TANK_DIRECTION_UP:
                newY = tank.getY() - tank.getSpeed();
                break;
            case Tank.TANK_DIRECTION_RIGHT:
                newX = tank.getX() + tank.getSpeed();
                break;
            case Tank.TANK_DIRECTION_DOWN:
                newY = tank.getY() + tank.getSpeed();
                break;
            case Tank.TANK_DIRECTION_LEFT:
                newX = tank.getX() - tank.getSpeed();
                break;
        }

        // 判断是否碰撞到边界
        switch (tank.getDirection()) {
            case Tank.TANK_DIRECTION_UP:
                if(newY < 0){return true;}
                break;
            case Tank.TANK_DIRECTION_RIGHT:
                if(newX + Tank.TANK_WIDTH > GamePanel.GAME_ACTION_WIDTH){return true;}
                break;
            case Tank.TANK_DIRECTION_DOWN:
                if(newY + Tank.TANK_HEIGHT > GamePanel.GAME_ACTION_HEIGHT){return true;}
                break;
            case Tank.TANK_DIRECTION_LEFT:
                if(newX < 0){return true;}
                break;
        }

        // 判断是否碰撞到敌方坦克
        for (int i = 0; i < gamePanel.getEnemyPool().size(); i++){
            Enemy enemy = gamePanel.getEnemyPool().get(i);
            if(enemy.getState() == Tank.TANK_STATE_RUNNING && tank != enemy)
            {
                if(rectCollideRect(newX,newY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT,enemy.getX(),enemy.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT)){return true;}
            }
        }

        // 判断是否碰撞到我方坦克
        if(tank.getTankKind() == Tank.TANK_KIND_ENEMY) {
            if(rectCollideRect(newX,newY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT,gamePanel.getHero().getX(),gamePanel.getHero().getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT)){return true;}
        }

        // 判断是否碰撞到块
        for (Block block : gamePanel.getBlockList()){
            if(rectCollideRect(newX,newY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT,block.getX(),block.getY(),Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT)){return true;}
        }

        // 判断是否碰撞基地
        if(gamePanel.getCamp().getState() == Camp.CAMP_STATE_RUNNING){
            if(rectCollideRect(newX,newY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT,gamePanel.getCamp().getX(),gamePanel.getCamp().getY(),gamePanel.getCamp().getWidth(),gamePanel.getCamp().getHeight())){return true;}
        }

        return false;
    }

    //判断坦克是否碰撞到坦克
    public boolean tankCollideTank(Tank tank1,Tank tank2){
        return rectCollideRect(tank1.getX(),tank1.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT,tank2.getX(),tank2.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
    }

    //判断子弹是否碰撞到坦克
    public boolean bulletCollideTank(Bullet bullet,Tank tank){
        return rectCollideRect(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight(),tank.getX(),tank.getY(),Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
    }

    //判断子弹是否碰撞到块
    public boolean bulletCollideBlock(Bullet bullet,Block block){
        return rectCollideRect(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight(),block.getX(),block.getY(),Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT);
    }

    //判断子弹是否碰撞到基地
    public boolean bulletCollideCamp(Bullet bullet,Camp camp){
        return rectCollideRect(bullet.getX(),bullet.getY(),bullet.getWidth(),bullet.getHeight(),camp.getX(),camp.getY(),camp.getWidth(),camp.getHeight());
    }
    
    //判断子弹是否碰撞到子弹
    public boolean bulletCollideBullet(Bullet bullet1,Bullet bullet2) {
    	return rectCollideRect(bullet1.getX(),bullet1.getY(),bullet1.getWidth(),bullet1.getHeight(),bullet2.getX(),bullet2.getY(),bullet2.getWidth(),bullet2.getHeight());
    }

    //得到某范围内的随机整数
    public static int getRandomInt(int min,int max) {
        return (int)(min + Math.random() * (max - min + 1));
    }

}
