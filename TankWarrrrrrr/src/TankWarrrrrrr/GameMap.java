package TankWarrrrrrr;


public class GameMap{
    //各关地图[26行26列]（0-空地，1-砖块，2-铁块）
    private static byte[][][] gradeMap ={
    		{	//第一关地图
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,1,1,0,0,2,2,2,0,2,0,0,2,0,0,2,0,2,0,2,0,1,1,0,0},
    			{0,0,1,1,0,0,0,2,0,2,0,2,0,2,2,0,2,0,2,2,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,2,0,2,2,2,0,2,0,2,2,0,2,2,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,2,0,2,0,2,0,2,0,0,2,0,2,0,2,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
    			{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0}
    		},
    		//第二关地图
    		{
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,1,1,1,1,1,1,1,0,0,2,2,2,0,0,1,1,1,1,1,1,1,1,0,0},
    			{0,0,1,1,1,1,1,1,1,0,0,2,2,2,0,0,1,1,1,1,1,1,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0},
    			{0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
    			{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
    			{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,1,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
    			{2,2,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2,2},
    			{2,2,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2,2}
    		},
    		//第三关地图
    		{
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
    			{2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,0,2,2,0,0,2,2,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
    			{2,2,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2,2},
    			{2,2,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,2,2}
    		},
    		//第四关地图
    		{
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0},
    			{0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       			{0,0,1,1,2,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,2,1,1,0,0},
    			{0,0,1,1,2,2,2,2,2,2,2,1,1,1,1,2,2,2,2,2,2,2,1,1,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
    			{2,2,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,2,2},
    			{0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,0,2,1,0,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{2,2,2,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,2,2,2},
    			{1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,1},
    			{1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1,1}
    		},
    		//第五关地图
    		{
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
     			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,0,0,0,0,0,0,0,0},
    			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,0,0,0,0,0,0,0,0},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    			{0,0,0,0,0,0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,2,2,2,2},
    			{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,0,2,2,2,2},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
    			{0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
       			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    		},
    		// 第六关地图
    		{
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,2,2,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,2,2,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1},
                {2,2,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,2,2},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,2,2,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
            },
            // 第七关地图
            {
                {0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,2,2,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,2,2,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,2,2,0,0,0,0,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,2,2,1,1,0,0},
                {0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,1,2,2,1,1,0,0},
                {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,1,0,0,0,0,2,2,0,0,0,0,1,1,0,0,1,1,2,2},
                {0,0,0,0,0,0,1,1,0,0,0,0,2,2,0,0,0,0,1,1,0,0,1,1,2,2},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,2,2,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,2,2,0,0,0,0,0,0,0,0},
                {0,0,1,1,1,1,1,1,0,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,0},
                {0,0,1,1,1,1,1,1,0,0,0,0,0,0,2,2,0,0,0,0,0,0,1,1,0,0},
                {0,0,0,0,0,0,2,2,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {0,0,0,0,0,0,2,2,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0},
                {2,2,1,1,0,0,2,2,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,0,0},
                {2,2,1,1,0,0,2,2,0,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,2,2,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,2,2,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,0,0,0,0,0,0,2,2,0,0,0,0,1,1,0,0,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0},
                {0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0}
            }
        };

    private static int gradeCount = gradeMap.length;	//总关卡数

    private static int[] enemyTankNum = {5,10,15,20,25,25,25};	//各关敌方坦克数量
    
    //返回某关地图
    public static byte[][] getGameMap(int grade){
        // 由于数组是个对象，而原始地图是不允许被修改的，所以不能直接赋值（引用地址），得复制一个新的地图让游戏随便修改。
        byte[][] tempMap = null;
        if(grade > 0 && grade <= gradeCount){
            tempMap = gradeMap[grade - 1];
        }
        else{
            tempMap = gradeMap[0];
        }
        //开始复制
        int row = tempMap.length;
        int column = tempMap[0].length;
        byte[][] returnMap = new byte[row][column];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                returnMap[i][j] = tempMap[i][j];
            }
        }
 
        return returnMap;
    }

    //功能：返回总关卡数
    public static int getGradeCount(){return gradeCount;}

    //返回某关敌方坦克数量
    public static int getEnemyTankNum(int grade){
        if(grade < 1 || grade > gradeCount){grade = gradeCount;}
        return enemyTankNum[grade - 1];
    }

}
