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
	
	//ʹ��JLabel����ͼƬ
	JLabel label = new JLabel();
	//ViewerService
	ViewerService service = ViewerService.getInstance();
	
	//���˵��ϼ��¼�������
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
		this.setTitle("д��������ͼƬ�����");
		
		this.setPreferredSize(new Dimension(width,height));
		
		//�����˵�
		createMenuBar();
		
		//����������
		JPanel toolBar = createToolPanel();
		
		//�ѹ������ӵ�JFrame���Ϸ�����ͼ���ӵ��м�
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
		
		JToolBar toolBar = new JToolBar("����");//���ù���������Ϊ�����ߡ�
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
		//����һ��JMenuBar���ò˵�
		JMenuBar menuBar = new JMenuBar();
		
		String[] menuArray = {"�ļ�(F)","����(T)","����(H)"};
		
		String[][] menuItemArray = {{"��(O)","-","�˳�(X)"},{"�Ŵ�(M)","��С(S)","-","��һ��(L)","��һ��(N)"},{"��������","����"}};
		
		for(int i = 0;i < menuArray.length;i++){
			//�½�һ��JMenu�˵�
			JMenu menu = new JMenu(menuArray[i]);
			for(int j = 0;j < menuItemArray[i].length;j++){
				if(menuItemArray[i][j].equals("-")){
					menu.addSeparator();
				}else{
					JMenuItem menuItem = new JMenuItem(menuItemArray[i][j]);
					
					//���˵���ÿ��menuItem���ϼ�����
					menuItem.addActionListener(menuListener);
					
					menu.add(menuItem);
				}
			}
			menuBar.add(menu);
		}
		this.setJMenuBar(menuBar);
	}
}
