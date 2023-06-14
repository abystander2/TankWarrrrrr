package TankWarrrrrrr;


public class Enemy extends Tank{
    public Enemy(int x, int y, int direction){
        super(TANK_KIND_ENEMY, x, y, direction);
    }

    @Override
    public void work(){
        super.work();
        // 启动线程让坦克跑
        new Thread(()->{
            while (this.getState() == TANK_STATE_RUNNING){
                try{
                    Thread.sleep(200);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                // 随机确认是原地不动还是移动（0-不动，其它-移动）
                if(GameLogic.getRandomInt(0,8) != 0) { 
                    // 随机确认是改变方向还是向前移动（0-改变方向，其它-向前移动）
                    if(GameLogic.getRandomInt(0,12) == 0) { 
                        int newDirection = GameLogic.getRandomInt(TANK_DIRECTION_UP, TANK_DIRECTION_LEFT + 3);
                        if(newDirection > TANK_DIRECTION_LEFT){newDirection = TANK_DIRECTION_DOWN;}    // 向下概率设大点
                        this.setDirection(newDirection);
                    }
                    else {    // 向前移动
                        // 如果到边界了就换方向
                        boolean toBorder = false;     // 默认未到边界
                        switch (this.getDirection()){
                            case TANK_DIRECTION_UP:
                                if((this.getY() - this.getSpeed() < 0)){
                                    toBorder = true;
                                }
                                break;
                            case TANK_DIRECTION_RIGHT:
                                if((this.getX() + TANK_WIDTH + this.getSpeed() > GamePanel.GAME_ACTION_WIDTH)){
                                    toBorder = true;
                                }
                                break;
                            case TANK_DIRECTION_DOWN:
                                if((this.getY() + TANK_HEIGHT + this.getSpeed() > GamePanel.GAME_ACTION_HEIGHT)){
                                    toBorder = true;
                                }
                                break;
                            case TANK_DIRECTION_LEFT:
                                if((this.getX() - this.getSpeed() < 0)){
                                    toBorder = true;
                                }
                                break;
                        }
                        if(toBorder) {  // 到边界了换方向
                            int newDirection = GameLogic.getRandomInt(TANK_DIRECTION_UP, TANK_DIRECTION_LEFT);
                            this.setDirection(newDirection);
                        }
                        else {   // 继续前进
                            this.move(this.getDirection());
                        }
                    }
                }
                // 随机确认是发射子弹还是省子弹（1-发射，其它-不发射）
                if(GameLogic.getRandomInt(0,8) == 1){
                    this.shoot();
                }
            }
        }).start();
    }

}
