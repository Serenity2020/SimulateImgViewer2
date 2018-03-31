package code;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class ViewerFrame extends JFrame{
	private int width = 800;
	private int height = 600;
	
	//使用JLabel放置图片
	JLabel label = new JLabel();
	//ViewerService
	ViewerService service = ViewerService.getInstance();
	
	//给菜单上加事件监听器
	ActionListener menuListener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			service.menuDo(ViewerFrame.this, e.getActionCommand());
		}
		
	};
	
	public ViewerFrame(){
		super();
		init();
	}
	
	public void init(){
		this.setTitle("写给亮亮的图片浏览器");
		
		this.setPreferredSize(new Dimension(width,height));
		
		//创建菜单
		createMenuBar();
		
		//创建工具栏
		JPanel toolBar = createToolPanel();
		
		//把工具栏加到JFrame最上方，读图区加到中间
		this.add(toolBar,BorderLayout.NORTH);
		this.add(new JScrollPane(label), BorderLayout.CENTER);
		
		this.setVisible(true);
		this.pack();
	}
	
	public JLabel getLabel(){
		return this.label;
	}
	
	public JPanel createToolPanel(){
		JPanel panel = new JPanel();
		
		JToolBar toolBar = new JToolBar("工具");//设置工具栏名字为“工具”
		toolBar.setFloatable(false);
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		String[] toolArray = {"code.action.OpenAction",
							  "code.action.LastAction",
							  "code.action.NextAction",
							  "code.action.BigAction",
							  "code.action.SmallAction"};
		for(int i = 0;i<toolArray.length;i++){
			ImageIcon sicon = new ImageIcon("img/"+toolArray[i]+".gif");
			ViewerAction action = new ViewerAction(sicon,toolArray[i],this);
			JButton button = new JButton(action);
			
			toolBar.add(button);
		}
		panel.add(toolBar);
		return panel;
	}
	
	public void createMenuBar(){
		//创建一个JMenuBar放置菜单
		JMenuBar menuBar = new JMenuBar();
		
		String[] menuArray = {"文件(F)","工具(T)","帮助(H)"};
		
		String[][] menuItemArray = {{"打开(O)","-","退出(X)"},{"放大(M)","缩小(S)","-","上一个(L)","下一个(N)"},{"帮助主题","关于"}};
		
		for(int i = 0;i < menuArray.length;i++){
			//新建一个JMenu菜单
			JMenu menu = new JMenu(menuArray[i]);
			for(int j = 0;j < menuItemArray[i].length;j++){
				if(menuItemArray[i][j].equals("-")){
					menu.addSeparator();
				}else{
					JMenuItem menuItem = new JMenuItem(menuItemArray[i][j]);
					
					//给菜单中每个menuItem加上监听器
					menuItem.addActionListener(menuListener);
					
					menu.add(menuItem);
				}
			}
			menuBar.add(menu);
		}
		this.setJMenuBar(menuBar);
	}
}
