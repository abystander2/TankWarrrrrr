package TankWarrrrrrr;

import java.awt.*;

	//块和铁块父类
public class Block{
    public final static int BLOCK_KIND_BRICK = 1;     // 砖块	//块类别常量
    public final static int BLOCK_KIND_IRON = 2;      // 铁块

    public final static int BLOCK_WIDTH = Tank.TANK_WIDTH/2;	    //块尺寸常量，和坦克等大
    public final static int BLOCK_HEIGHT = Tank.TANK_HEIGHT/2 ;

    private int x;    //块左上角X坐标

    private int y;    //块左上角Y坐标

    private int blockKind = BLOCK_KIND_BRICK;    //块类别


    public Block(int blockKind,int x, int y){
        this.blockKind = blockKind;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){		//画块
        if(this.blockKind == BLOCK_KIND_BRICK) {   // 画砖块
            g.setColor(new Color(210, 105, 30));
            g.fillRect(this.x,this.y,BLOCK_WIDTH,BLOCK_HEIGHT);
            g.setColor(new Color(244, 164, 96));
            g.drawLine(this.x,this.y,this.x + BLOCK_WIDTH - 1,this.y);
            g.drawLine(this.x,this.y + BLOCK_HEIGHT / 2,this.x + BLOCK_WIDTH - 1,this.y + BLOCK_HEIGHT / 2);
            g.drawLine(this.x,this.y,this.x,this.y + BLOCK_HEIGHT / 2);
            g.drawLine(this.x + BLOCK_WIDTH / 2,this.y + BLOCK_HEIGHT / 2, this.x + BLOCK_WIDTH / 2, this.y + BLOCK_HEIGHT - 1);
        }
        else if(this.blockKind == BLOCK_KIND_IRON) {      // 画铁块
            g.setColor(new Color(190, 190, 190));
            g.fillRect(this.x,this.y,BLOCK_WIDTH,BLOCK_HEIGHT);
            g.setColor(Color.WHITE);
            g.fillRect(this.x + 3,this.y + 3,BLOCK_WIDTH - 6,BLOCK_HEIGHT - 6);
            g.draw3DRect(this.x + 3,this.y + 3,BLOCK_WIDTH - 6,BLOCK_HEIGHT - 6,true);
            g.drawLine(this.x + 1,this.y + 1,this.x + 3,this.y + 3);
            g.drawLine(this.x + BLOCK_WIDTH - 1,this.y + 1,this.x + BLOCK_WIDTH - 3,this.y + 3);
            g.drawLine(this.x + 1,this.y + BLOCK_HEIGHT - 1,this.x + 3,this.y + BLOCK_HEIGHT - 3);
            g.drawLine(this.x + BLOCK_WIDTH - 1,this.y + BLOCK_HEIGHT - 1,this.x + BLOCK_WIDTH - 3,this.y + BLOCK_HEIGHT - 3);
        }
    }

    public int getX(){return x;}

    public void setX(int x){this.x = x;}

    public int getY(){return y;}

    public void setY(int y){this.y = y;}

    public int getBlockKind(){return blockKind;}

}
