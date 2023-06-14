package TankWarrrrrrr;


import java.awt.*;

public class Camp{
    //基地状态常量
    public final static int CAMP_STATE_FREE = 0;                // 空闲中
    public final static int CAMP_STATE_RUNNING = 1;             // 运行中


    private int x;    // 基地左上角X坐标
    private int y;    //营地左上角Y坐标

    private int width = Tank.TANK_WIDTH;    //基地宽度
    private int height = Tank.TANK_HEIGHT;	//基地高度
    private int state = CAMP_STATE_FREE;	//基地状态，默认空闲中

    private int elementWidth = 2;	//元素宽度
    private int elementHeight = 2;	//元素高度

    private static byte[][] campMap =	//绘制基地所用的坐标点位
        {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0},
            {1,1,1,1,0,0,0,1,1,1,1,0,0,0,0,0,1,1,1,1},
            {0,2,1,1,1,0,0,0,1,0,0,1,1,0,0,1,1,1,2,0},
            {1,1,2,0,1,0,0,0,1,1,1,1,0,0,0,1,0,2,1,1},
            {0,0,1,2,1,1,0,0,1,1,1,1,0,0,1,1,2,1,0,0},
            {0,1,1,1,2,1,0,0,1,1,1,1,0,0,1,2,1,1,1,0},
            {0,0,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,0,0},
            {0,0,0,1,1,1,2,1,1,1,1,1,1,2,1,1,1,0,0,0},
            {0,0,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,0,0},
            {0,0,0,1,1,1,1,1,1,2,1,2,1,1,1,1,1,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,1,1,0,1,0,1,0,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

    public Camp(int x, int y){
        this.x = x;
        this.y = y;
    }

    //画基地
    public void draw(Graphics g){
        int row = campMap.length;
        int column = campMap[0].length;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                int mapValue = campMap[i][j];
                if(mapValue == 1){
                    g.setColor(Color.WHITE);
                    drawElement(g,this.x + elementWidth * j,this.y + elementHeight * i);
                }
                else if(mapValue == 2){
                    g.setColor(Color.LIGHT_GRAY);
                    drawElement(g,this.x + elementWidth * j,this.y + elementHeight * i);
                }
            }
        }
    }

    //画像素块
    private void drawElement(Graphics g,int x,int y){
        g.fillRect(x,y,elementWidth,elementHeight);
        g.setColor(Color.WHITE);
        g.draw3DRect(x,y,elementWidth,elementHeight,true);
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public int getWidth(){return width;}

    public int getHeight(){return height;}

    public int getState(){return state;}

    public void setState(int state){this.state = state;}

}
