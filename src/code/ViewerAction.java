package code;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import code.action.Action;

public class ViewerAction extends AbstractAction {
	private String actionName = "";
	private ViewerFrame frame = null;
	
	//�����������AbstractAction����Ӧ��org.crazyit.viewer.action����ĳ��Actionʵȫ
	private Action action = null;

	/**
	 * ������
	 * 
	 */
	public ViewerAction() {
		// ���ø�������
		super();
	}

	/**
	 * ������
	 * 
	 * @param icon
	 *            ImageIcon ͼ��
	 * @param name
	 *            String
	 */
	public ViewerAction(ImageIcon icon, String actionName, ViewerFrame frame) {
		// ���ø�������
		super("", icon);
		this.actionName = actionName;
		this.frame = frame;
	}

	/**
	 * ��дvoid actionPerformed( ActionEvent e )����
	 * 
	 * @param e
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent e) {
		ViewerService service = ViewerService.getInstance();
		Action action = getAction(this.actionName);
		//����Action��execute����
		if(action != null){
		action.execute(service, frame);
		}
	}
	
	/**
	 * ͨ��actionName�õ������ʵ��
	 * @param actionName
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private Action getAction(String actionName) {
			try{
			if (this.action == null) {
				//����Actionʵ��
				Action action = (Action)Class.forName(actionName).newInstance();
				this.action = action;
			}
			return this.action;
			}catch(Exception e){
				return null;
			}
			
			
	}
	
	
}