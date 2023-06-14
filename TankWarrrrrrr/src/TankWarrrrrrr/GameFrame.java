package TankWarrrrrrr;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;    //游戏面板

      public GameFrame(){
        try{
            // 定制菜单
            JMenuBar main = new JMenuBar();      // 菜单栏
            JMenu game = new JMenu("游戏");     // 游戏菜单
            game.setFont(new Font("微软雅黑",Font.PLAIN,12));
            JMenuItem newgame = new JMenuItem("新游戏");    // 子菜单
            newgame.setFont(new Font("微软雅黑",Font.PLAIN,12));
            newgame.addActionListener(this);      // 为窗口增加新游戏菜单的事件监听
            newgame.setActionCommand("new");     //新游戏菜单的监听标识
            game.add(newgame);   				// 将新游戏子菜单附加到游戏菜单上
            JMenuItem select = new JMenuItem("选关卡");
            select.setFont(new Font("微软雅黑",Font.PLAIN,12));
            select.addActionListener(this);
            select.setActionCommand("grade");
            game.add(select);
            game.addSeparator();                  // 菜单分隔符
            JMenuItem exit = new JMenuItem("退出");
            exit.setFont(new Font("微软雅黑",Font.PLAIN,12));
            exit.addActionListener(this);
            exit.setActionCommand("exit");
            game.add(exit);
            main.add(game);
            this.setJMenuBar(main);
            JMenu vivo = new JMenu("充值");
            vivo.setFont(new Font("微软雅黑",Font.PLAIN,12));
            JMenuItem vip = new JMenuItem("充值让你变强");
            vip.setFont(new Font("微软雅黑",Font.PLAIN,12));
            vip.addActionListener(this);
            vip.setActionCommand("vip");
            vivo.add(vip);
            main.add(vivo);
            this.setJMenuBar(main);// 定制面板
            this.gamePanel = new GamePanel();
            this.add(this.gamePanel);

            // 定制窗口
            this.setTitle("坦克大战");                   // 标题
            this.setLayout(null);                       // 清空布局管理器
            this.setSize(this.gamePanel.getWidth() + 10,this.gamePanel.getHeight() + 60);   // 根据游戏面板大小设置游戏窗口大小
            this.setResizable(false);                   // 程序运行时禁止改变窗口大小尺寸
            this.setLocationRelativeTo(null);           // 窗口居中
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 点击窗口X按钮时默认关闭程序
            this.setVisible(true);                      // 显示窗口
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"程序出现异常错误，即将退出\r\n"+e.toString(),"提示",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {   //事件监听
        // 监听事件的标识
        String command = e.getActionCommand();
        switch (command) {
            case "new":     // 如果点了new，开始新游戏
                this.gamePanel.newGame();
                break;
            case "grade":  		//选关，一共七关
                int gradeCount = GameMap.getGradeCount();
                String[] options = new String[gradeCount];
                for (int i = 0; i < gradeCount; i++)
                    options[i] = i + 1 + "";
                String grade = (String)JOptionPane.showInputDialog(this, "请选择你要进行的关卡：","选关", JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                gamePanel.setGrade(Integer.parseInt(grade));
                gamePanel.newGame();
                break;
            case "exit":		
                System.exit(0);
                break;
            case "vip":
                JOptionPane.showMessageDialog(this,"富哥v我50","提示",JOptionPane.INFORMATION_MESSAGE);
                break;

        }
    }

}
