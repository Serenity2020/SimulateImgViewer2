package code;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import code.action.Action;

public class ViewerAction extends AbstractAction {
	private String actionName = "";
	private ViewerFrame frame = null;
	
	//这个工具栏的AbstractAction所对应的org.crazyit.viewer.action包的某个Action实全
	private Action action = null;

	/**
	 * 构造器
	 * 
	 */
	public ViewerAction() {
		// 调用父构造器
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param icon
	 *            ImageIcon 图标
	 * @param name
	 *            String
	 */
	public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
		// 调用父构造器
		super("", icon);
		this.actionName = actionName;
		this.frame = frame;
	}

	/**
	 * 重写void actionPerformed( ActionEvent e )方法
	 * 
	 * @param e
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		ViewerService service = ViewerService.getInstance();
		Action action = getAction(this.actionName);
		//调用Action的execute方法
		if(action != null){
		action.execute(service, frame);
		}
	}
	
	/**
	 * 通过actionName得到该类的实例
	 * @param actionName
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private Action getAction(String actionName) {
			try{
			if (this.action == null) {
				//创建Action实例
				Action action = (Action)Class.forName(actionName).newInstance();
				this.action = action;
			}
			return this.action;
			}catch(Exception e){
				return null;
			}
			
			
	}
	
	
}