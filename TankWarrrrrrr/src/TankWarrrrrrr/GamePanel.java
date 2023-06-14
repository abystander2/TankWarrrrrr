package TankWarrrrrrr;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//初始化各种变量
    private final static int GAME_STATE_READY = 0;       // 游戏未开始
    private final static int GAME_STATE_RUNNING = 1;     // 游戏运行中
    private final static int GAME_STATE_OVER = 9;        // 游戏已结束

    public final static int GAME_ACTION_WIDTH = Block.BLOCK_WIDTH * 26;     // 游戏运行场景宽度
    public final static int GAME_ACTION_HEIGHT = Block.BLOCK_HEIGHT * 26;    // 游戏运行场景宽度

    public final static int GAME_PANEL_WIDTH = GAME_ACTION_WIDTH + 150;     // 游戏面板宽度
    public final static int GAME_PANEL_HEIGHT = GAME_ACTION_HEIGHT;      
    // 游戏面板高度

    private BufferedImage bufferedImage = new BufferedImage(GAME_ACTION_WIDTH,GAME_ACTION_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
    //利用双缓冲机制防止画面闪烁，创建一张与面板画面一样大小的图片，所有的元素先绘制到该图片上，再将该图片一次性绘制到面板画面上
    
    private int repaintInterval = 20;      // 游戏每秒刷新（1000/repaintInterval）次

    private int gameState = GAME_STATE_READY; //游戏状态

    private Hero hero;//我方坦克

    private Hero heroModel = new Hero(GAME_ACTION_WIDTH + 30,200,Tank.TANK_DIRECTION_RIGHT);    //我方坦克模型，显示在计分榜上

    private Vector<Enemy> enemyPool = new Vector<>();   //敌方坦克池，为了线程安全用Vector，不用ArrayList

    private int enemyPoolMaxNum = 0;		//限制敌方坦克池最大数量

    private int enemyDeadNum = 0;		    //敌方坦克死亡数量

    private Enemy enemyModel = new Enemy(GAME_ACTION_WIDTH + 30,20,Tank.TANK_DIRECTION_RIGHT);	   //敌方坦克模型，显示在计分榜上

    private boolean createEnemyTankThreadRunning = false;	    //标识地方坦克线程是否运行

    private long lastCreateEnemyTankTime = System.currentTimeMillis();	    //记下上次投放时间，用来设置投放坦克最小时间间隔

    private List<Block> blockList = new ArrayList<>();	//块列表

    private List<Bomb> bombList = new ArrayList<>();	//爆炸列表

    private int grade = 1;		//游戏关卡

    private byte[][] gameGradeMap;		    //游戏某关地图

    private Camp camp = new Camp(12 * Block.BLOCK_WIDTH,24 * Block.BLOCK_HEIGHT);		//我方基地


    public GamePanel(){
        this.setSize(GAME_PANEL_WIDTH,GAME_PANEL_HEIGHT);        //设置面板大小

        //监听键盘事件
        this.setFocusable(true);           //让面板得到焦点
        this.addKeyListener(this);      //将监听键盘事件附加到面板上

        GameLogic.setInstance(new GameLogic(this));        //创建游戏逻辑实例
    }


    public void newGame(){		//开始新游戏方法

        gameReset();        //先调用游戏重置方法

        this.gameState = GAME_STATE_RUNNING;        //设置游戏状态为运行中

        loadGameMap();        //加载游戏地图方法

        camp.setState(Camp.CAMP_STATE_RUNNING);        //设置我方基地

        //创建我方坦克
        int heroX = 8 * Block.BLOCK_WIDTH;
        int heroY = (this.gameGradeMap.length - 2) * Block.BLOCK_HEIGHT;
        hero = new Hero(heroX,heroY,Tank.TANK_DIRECTION_UP);
        hero.work();

        //创建敌方坦克方法
        createEnemyTank();

        //启动线程来定时刷新画面
        refresh();

    }


    private void loadGameMap(){  		//加载游戏地图方法

        this.gameGradeMap = GameMap.getGameMap(this.grade);        // 得到本关地图

        int row = this.gameGradeMap.length;   	// 得到地图位置（几行几列）
        int column = this.gameGradeMap[0].length;

        // 开始循环
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                int mapValue = this.gameGradeMap[i][j];
                switch (mapValue){
                    case Block.BLOCK_KIND_BRICK:
                        Brick brick = new Brick(Block.BLOCK_WIDTH * j,Block.BLOCK_HEIGHT * i);
                        this.blockList.add(brick);
                        break;
                    case Block.BLOCK_KIND_IRON:
                        Iron iron = new Iron(Block.BLOCK_WIDTH * j,Block.BLOCK_HEIGHT * i);
                        this.blockList.add(iron);
                        break;
                }
            }
        }

        this.enemyPoolMaxNum = GameMap.getEnemyTankNum(this.grade);     // 本关敌方坦克数量
    }

    private void gameReset(){		//游戏重置方法
        // 停止上局正在运行的线程
        if(this.gameState == GAME_STATE_RUNNING){this.gameState = GAME_STATE_READY;}
        while (true){
            if(this.createEnemyTankThreadRunning){
                try{
                    Thread.sleep(10);
                }
                catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            else{
                break;
            }
        }

        // 清空敌方坦克池中的所有坦克及其子弹
        for (int i = 0; i < enemyPool.size(); i++) {       
        	Enemy enemy = enemyPool.get(i);
            enemy.reset();
            for (int j = 0; j < enemy.getBulletPool().size(); j++){
                Bullet bullet = enemy.getBulletPool().get(j);
                bullet.reset();
            }
            enemy.getBulletPool().clear();
        }
        enemyPool.clear();
        enemyDeadNum = 0;

		// 清空我方坦克及其子弹
        if(hero != null) {
            hero.reset();
            for (int i = 0; i < hero.getBulletPool().size(); i++)
            {
                Bullet bullet = hero.getBulletPool().get(i);
                bullet.reset();
            }
            hero.getBulletPool().clear();
        }

        // 清空块池中的所有块
        blockList.clear();

    }

    //创建地方坦克
    private void createEnemyTank(){
    	
        this.createEnemyTankThreadRunning = true;   // 线程运行中
        new Thread(()->{		
            // 记录已投放的坦克数量
            int createEnemyTankNum = 0;
            try{
                while (this.gameState == GAME_STATE_RUNNING){
                    // 休眠一会
                    try{
                        Thread.sleep(repaintInterval);      // 休眠时间短主要是考虑可以迅速退出本线程
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    // 控制连续投放间隔
                    long curCreateEnemyTankTime = System.currentTimeMillis();
                    if((curCreateEnemyTankTime - this.lastCreateEnemyTankTime) >= 3000) {  // 3秒投放
                        this.lastCreateEnemyTankTime = curCreateEnemyTankTime;
                    }
                    else{
                        continue;
                    }
                    // 投放敌方坦克到达最大值后退出本线程
                    if(createEnemyTankNum >= enemyPoolMaxNum){break;}
                    // 在敌方坦克池中寻找空闲的坦克随机显示在左上角或右上角
                    Enemy enemy = null;
                    for (int i = 0; i < enemyPool.size(); i++) {
                        Enemy tmpEnemy = enemyPool.get(i);
                        if(tmpEnemy.getState() == Tank.TANK_STATE_FREE)
                        {
                            enemy = tmpEnemy;
                            break;
                        }
                    }
                    // 没有就增加一个
                    if(enemy == null) {
                        enemy = new Enemy(-100,-100,Tank.TANK_DIRECTION_DOWN);
                        enemyPool.add(enemy);
                    }
                    // 开始投放（随机投放到左上角与右上角，坦克不能重叠）
                    int enemyX;     // 坦克X坐标
                    int enemyDirection = Tank.TANK_DIRECTION_DOWN;  // 坦克方向默认向下
                    int randomPos = GameLogic.getRandomInt(0,1);    // 随机生成位置（0-左上角，1-右上角）
                    if(randomPos == 0){		// 左上角
                    	enemyX = 0;
                        if(GameLogic.getRandomInt(0,1) == 1){enemyDirection = Tank.TANK_DIRECTION_RIGHT;}
                    }
                    else {   // 右上角
                        enemyX = GAME_ACTION_WIDTH - Tank.TANK_WIDTH;
                        if(GameLogic.getRandomInt(0,1) == 1){enemyDirection = Tank.TANK_DIRECTION_LEFT;}
                    }
                    enemy.setX(enemyX);
                    enemy.setY(0);
                    enemy.setDirection(enemyDirection);
                    // 判断该范围内是否可以投放运行
                    boolean workFlag = true;
                    for (int i = 0; i < enemyPool.size(); i++){
                        Enemy tmpEnemy = enemyPool.get(i);
                        if(tmpEnemy.getState() == Tank.TANK_STATE_RUNNING && enemy != tmpEnemy) {
                            if(GameLogic.getInstance().tankCollideTank(enemy,tmpEnemy)) {  // 敌方坦克占位，不能投放

                                workFlag = false;
                                continue;
                            }
                        }
                    }
                    if(GameLogic.getInstance().tankCollideTank(enemy,hero)) {     // 我方坦克占位，不能投放
                        workFlag = false;
                    }
                    // 不能投放准备下一次
                    if(!workFlag){continue;}
                    // 可以投放了
                    enemy.work();   // 线程，启动！
                    createEnemyTankNum++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally{
                this.createEnemyTankThreadRunning = false;      // 线程结束
            }
        }).start();

    }

    //游戏结束方法
    private void gameOver(){

        // 设置游戏状态
        this.gameState = GAME_STATE_OVER;

        // 清空重置
        gameReset();

    }

    //y游戏胜利方法
    private void gameWin() {
        if(enemyPoolMaxNum - enemyDeadNum <= 0){
            this.grade++;
            newGame();
        }
    }

    //碰撞处理
    private void collide(){
        // 判断我方子弹碰撞
        for (int i = 0; i < hero.getBulletPool().size(); i++){
            Bullet bullet = hero.getBulletPool().get(i);
            if(bullet.getState() == Bullet.BULLET_STATE_RUNNING) { 
                // 判断是否与敌方坦克碰撞
                for (int j = 0; j < enemyPool.size(); j++){	//子弹
                    Enemy enemy = enemyPool.get(j);
                    //在这里判断子弹是否与敌方子弹碰撞
                    for (int k = 0; k < enemy.getBulletPool().size();k++) {
                    	Bullet bullet2 = enemy.getBulletPool().get(k);
                    	if(GameLogic.getInstance().bulletCollideBullet(bullet, bullet2)) {	//如果碰上了
                    		//记录子弹位置
                    		int bulletX = bullet.getX();
                    		int bulletY = bullet.getY();
                    		//子弹销毁
                    		bullet.reset();
                    		bullet2.reset();
                    		addBomb(bulletX,bulletY,bullet.getWidth(),bullet.getHeight());
                    	}
                    }
                    if(enemy.getState() == Tank.TANK_STATE_RUNNING) {     // 敌方坦克
                        if(GameLogic.getInstance().bulletCollideTank(bullet,enemy)) {   // 如果碰上了
                            // 记录坦克坐标，因为坦克销毁时会改变其坐标
                            int bombX = enemy.getX();
                            int bombY = enemy.getY();
                            // 子弹销毁，坦克受到伤害，判断坦克是否死亡
                            bullet.reset();
                            enemy.hurt(bullet);
                            if(enemy.isDead()){
                                // 坦克销毁，爆炸，每消灭一个敌人判断一次是否胜利
                                enemy.reset();
                                enemyDeadNum++;
                                addBomb(bombX,bombY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
                                gameWin();
                            }
                        }
                    }
                }
                // 判断是否与块碰撞
                for (int m = blockList.size() - 1; m >= 0; m--) {
                    Block block = blockList.get(m);
                    if(GameLogic.getInstance().bulletCollideBlock(bullet,block)) {   //如果碰上了
                        // 记录块坐标
                        int bombX = block.getX();
                        int bombY = block.getY();
                        // 子弹销毁
                        bullet.reset();
                        if(block.getBlockKind() == Block.BLOCK_KIND_BRICK){
                            // 砖块销毁，爆炸
                            blockList.remove(m);
                            addBomb(bombX,bombY,Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT);
                        }
                        else{
                            addBomb(bombX,bombY,Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT);
                        }
                    }
                }
                // 判断是否与基地碰撞
                if(camp.getState() == Camp.CAMP_STATE_RUNNING){
                    if(GameLogic.getInstance().bulletCollideCamp(bullet,camp)) {   //基地GG
                        //子弹销毁，基地销毁，基地爆炸，游戏结束
                    	bullet.reset(); 
                        camp.setState(Camp.CAMP_STATE_FREE);
                        addBomb(camp.getX(),camp.getY(),camp.getWidth(),camp.getHeight()); 
                        gameOver(); 	//这样就可以自毁基地了
                    }
                }
            }
        }

        // 判断敌方子弹是否与我方坦克或块或大本营碰撞（不需要判断敌方坦克状态，因为即使坦克销毁子弹可能仍然在飞）
        for (int i = 0; i < enemyPool.size(); i++) {
            // 判断是否与我方坦克碰撞
            Enemy enemy = enemyPool.get(i);
            for (int j = 0; j < enemy.getBulletPool().size(); j++){
                Bullet bullet = enemy.getBulletPool().get(j);
                if(bullet.getState() == Bullet.BULLET_STATE_RUNNING) {
                    // 判断子弹是否与坦克碰撞
                    if(GameLogic.getInstance().bulletCollideTank(bullet,hero)) {   //如果碰上了
                        // 记录坦克坐标，因为坦克销毁时会改变其坐标
                        int bombX = hero.getX();
                        int bombY = hero.getY();
                        //子弹销毁、坦克受伤、判断是否死亡
                        bullet.reset();
                        hero.hurt(bullet);
                        if(hero.isDead()){
                            // 坦克销毁并爆炸，游戏结束
                            hero.reset();
                            addBomb(bombX,bombY,Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
                            gameOver();
                        }
                    }
                    //判断子弹是否与块碰撞
                    for (int m = blockList.size() - 1; m >= 0; m--) {
                        Block block = blockList.get(m);
                        // 开始判断
                        if(GameLogic.getInstance().bulletCollideBlock(bullet,block)) {  // 如果碰上了
                            // 记录块坐标
                            int bombX = block.getX();
                            int bombY = block.getY();
                            bullet.reset();
                            if(block.getBlockKind() == Block.BLOCK_KIND_BRICK){
                                // 砖块销毁并爆炸
                                blockList.remove(m);
                                addBomb(bombX,bombY,Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT);
                            }
                            else{
                                addBomb(bombX,bombY,Block.BLOCK_WIDTH,Block.BLOCK_HEIGHT);
                            }
                        }
                    }
                    // 判断子弹是否与基地碰撞
                    if(camp.getState() == Camp.CAMP_STATE_RUNNING){
                        if(GameLogic.getInstance().bulletCollideCamp(bullet,camp)) {   // 如果碰上
                            // 子弹销毁，基地销毁，基地爆炸，游戏结束
                            bullet.reset();
                            camp.setState(Camp.CAMP_STATE_FREE);
                            addBomb(camp.getX(),camp.getY(),camp.getWidth(),camp.getHeight());
                            gameOver();
                        }
                    }
                }
            }
        }

    }

    //添加爆炸效果
    private void addBomb(int x,int y,int w,int h){
        // 查找空闲的爆炸
        Bomb bomb = null;
        for (int i = 0; i < bombList.size(); i++) {
            Bomb tmpBomb = bombList.get(i);
            if(tmpBomb.getState() == Bomb.BOMB_STATE_FREE) {
                bomb = tmpBomb;
                break;
            }
        }
        // 没有就增加一个
        if(bomb == null){
            bomb = new Bomb(-100,-100);
            bombList.add(bomb);
        }
        bomb.setX(x);
        bomb.setY(y);
        bomb.setWidth(w);
        bomb.setHeight(h);
        bomb.work();
    }

    //字符被输入
    @Override
    public void keyTyped(KeyEvent e){}

    //某键被按下
    @Override
    public void keyPressed(KeyEvent e){
        // 游戏未开始或结束后禁止按键
        if(this.gameState != GAME_STATE_RUNNING){return;}

        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:	//两个case一个break
                hero.move(Tank.TANK_DIRECTION_UP);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                hero.move(Tank.TANK_DIRECTION_RIGHT);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                hero.move(Tank.TANK_DIRECTION_DOWN);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                hero.move(Tank.TANK_DIRECTION_LEFT);
                break;
            case KeyEvent.VK_SPACE:
                hero.shoot();
                break;
        }
    }

    //某键被释放
    @Override
    public void keyReleased(KeyEvent e){}

    //利用线程来定时刷新重绘游戏画面
    private void refresh(){
        new Thread(() ->{
            while (this.gameState == GAME_STATE_RUNNING) {
                collide();       // 碰撞处理
                repaint();       // 开始刷新

                try {		     // 延时睡眠
                    Thread.sleep(repaintInterval);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //绘制游戏画面，利用双缓冲机制防止画面闪烁
    @Override
    public void paint(Graphics g){
        // 完成初始化工作
        super.paint(g);
        // 开始游戏时
        if(this.gameState == GAME_STATE_READY){
            // 清背景
            g.setColor(Color.GRAY);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            // 显示
            g.setColor(Color.ORANGE);
            g.setFont(new Font("微软雅黑",Font.BOLD,60));
            g.drawString("坦  克  大  战", 180,160);
            g.setColor(Color.BLUE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("2023.6.15", 220,250);
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体",Font.PLAIN,20));
            g.drawString("面向对象程序设计答辩", 220,420);
            return;
        }

        // 游戏结束时
        if(this.gameState == GAME_STATE_OVER){
            g.setColor(Color.BLACK);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("宋体",Font.BOLD,60));
            g.drawString("游戏结束\n", 180,170);
            g.setColor(Color.GREEN);
            g.drawString("感谢游玩", 180,280);
            g.setColor(Color.WHITE);
            g.setFont(new Font("宋体",Font.PLAIN,20));
            g.drawString("面向对象程序设计答辩", 200,420);
        }

        // 游戏进行中
        if(this.gameState == GAME_STATE_RUNNING){
            // 设置游戏面板区域
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0,0,this.getWidth(),this.getWidth());
            g.setColor(Color.WHITE);
            g.fill3DRect(GAME_ACTION_WIDTH,0,2,GAME_ACTION_HEIGHT,true);
            // 绘制分数
            // 敌方坦克信息
            enemyModel.draw(g);
            g.setColor(Tank.TANK_COLOR_ENEMY);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("总数：" + enemyPoolMaxNum,enemyModel.getX() ,enemyModel.getY() + 70);
            g.drawString("死亡：" + enemyDeadNum,enemyModel.getX() ,enemyModel.getY() + 100);
            g.drawString("剩余：" + (enemyPoolMaxNum - enemyDeadNum),enemyModel.getX() ,enemyModel.getY() + 130);
            // 我方坦克信息
            heroModel.draw(g);
            g.setColor(Tank.TANK_COLOR_HERO);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("歼敌："+enemyDeadNum ,heroModel.getX() - 1 ,heroModel.getY() + 70);
            g.drawString("生命：" + hero.getHp() ,heroModel.getX() ,heroModel.getY() + 100);
            // 关口信息
            g.setColor(Color.BLACK);
            int gradeModelX = heroModel.getX();
            int gradeModelY = heroModel.getY() + 150;
            g.fillRect(gradeModelX,gradeModelY,3,40);
            Polygon triangle = new Polygon();
            triangle.addPoint(gradeModelX + 3, gradeModelY);
            triangle.addPoint(gradeModelX + 3, gradeModelY + 20);
            triangle.addPoint(gradeModelX  + 40, gradeModelY + 20);
            g.setColor(Color.RED);
            g.fillPolygon(triangle);
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("关数：" + this.grade ,gradeModelX ,gradeModelY + 70);

            //双缓冲机制来重绘画面,先把所有的元素绘制到缓冲图片上，再将该图片一次性绘制到画面上
            Graphics ig = bufferedImage.getGraphics();      //得到缓冲图片的画笔

            // 设置游戏面板区域
            ig.setColor(Color.BLACK);
            ig.fillRect(0,0,GAME_ACTION_WIDTH,GAME_ACTION_HEIGHT);

            // 绘制地图
            for (Block block : this.blockList){block.draw(ig);}

            // 绘制基地
            if(camp.getState() == Camp.CAMP_STATE_RUNNING){camp.draw(ig);}

            // 绘制我方坦克
            hero.draw(ig);

            // 绘制我方子弹
            for (Bullet bullet : hero.getBulletPool()){if(bullet.getState() == Bullet.BULLET_STATE_RUNNING){bullet.draw(ig);}}

            // 绘制敌方坦克、子弹
            for (int i = 0; i < enemyPool.size(); i++) {
                Enemy enemy = enemyPool.get(i);
                // 绘制坦克
                if(enemy.getState() == Tank.TANK_STATE_RUNNING){enemy.draw(ig);}
                // 绘制子弹
                for (Bullet bullet : enemy.getBulletPool()){if(bullet.getState() == Bullet.BULLET_STATE_RUNNING){bullet.draw(ig);}}
            }

            // 绘制爆炸
            for (Bomb bomb : bombList){if(bomb.getState() == Bomb.BOMB_STATE_RUNNING){bomb.draw(ig);}}

            // 将缓冲图片一次性显示到画面上
            g.drawImage(bufferedImage,0,0,null);
        }

    }

    public Hero getHero(){return hero;}

    public Vector<Enemy> getEnemyPool(){return enemyPool;}

    public List<Block> getBlockList(){return blockList;}

    public Camp getCamp(){return camp;}

    public void setGrade(int grade){this.grade = grade;}

}
