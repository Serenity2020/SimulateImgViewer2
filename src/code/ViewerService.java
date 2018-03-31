package code;

import java.awt.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;

public class ViewerService {
	private static ViewerService service = null;
	
	private ViewerFileChooser fileChooser = new ViewerFileChooser();
	
	//����Ŵ����Сʱ�ı���
	private double range = 0.2;
	
	//Ŀǰ���ļ���
	private File currentDirectory = null;
	
	//Ŀǰ�ļ������������ͼƬ
	private List<File> currentFiles = null;
	
	//Ŀǰ�򿪵�ͼƬ�ļ�
	private File currentFile = null;
	
	/*
	 * ˽�й�����*/
	private ViewerService(){
		
	}
	
	/*
	 * ��ȡ��̬ʵ��*/
	public static ViewerService getInstance(){
		if(service == null){
			service = new ViewerService();
		}
		return service;
	}
	
	public void open(ViewerFrame frame){
		//���ѡ���
		if(fileChooser.showOpenDialog(frame) == ViewerFileChooser.APPROVE_OPTION){
			//��ֵ��Ŀǰ�򿪵��ļ�currentFile
			this.currentFile = fileChooser.getSelectedFile();
			
			//��ȡ�ļ�·��
			String name = this.currentFile.getPath();
			
			//��ȡĿǰ�ļ����ļ���
			File cd = fileChooser.getCurrentDirectory();
			
			//����ļ��������ı�
			if(this.currentDirectory == null || cd != this.currentDirectory){
				//��ȡfileChooser������FileFilter
				FileFilter[] fileFilters = fileChooser.getChoosableFileFilters();
				File[] files = cd.listFiles();//cdΪ�ı����ļ��е�ַ����ȡ���е������ļ�
				this.currentFiles = new ArrayList<File>();
				
				for(File f:files){
					for(FileFilter filter : fileFilters){
						//�����ͼƬ�ļ�
						if(filter.accept(f)){
							this.currentFiles.add(f);
						}
					}
				}
			}
			ImageIcon icon = new ImageIcon(name);
			frame.getLabel().setIcon(icon);
		}
	}
	
	public void zoom(ViewerFrame frame,boolean isEnlarge){
		//��ȡ�Ŵ������С�ĳ˱�
		double EnlargeRange = isEnlarge ? (1 + range) : (1 - range);
		
		//��ȡĿǰ��ͼƬ
		ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
		
		if(icon != null){
			int width = (int) (icon.getIconWidth() * EnlargeRange);
			
			//��ȡ�ı��С֮���ͼƬ
			ImageIcon newicon = new ImageIcon(icon.getImage().getScaledInstance(width, -1, Image.SCALE_DEFAULT));
			
			//���øĶ������ͼƬ
			frame.getLabel().setIcon(newicon);
		}
	}
	
	public void last(ViewerFrame frame){
		//����д򿪰���ͼƬ���ļ���
		if(this.currentFiles != null && this.currentFiles.isEmpty()){
			int index = this.currentFiles.indexOf(currentFile);
			
			if(index > 0){
				File file = (File) this.currentFiles.get(index-1);
				ImageIcon icon = new ImageIcon(file.getPath());
				frame.getLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}
	
	public void next(ViewerFrame frame){
		if(this.currentFiles != null && !this.currentFiles.isEmpty()){
			int index = this.currentFiles.indexOf(this.currentFile) + 1;
			
			//����һ��
			if(index + 1 < this.currentFiles.size()){
				File file = (File) this.currentFiles.get(index + 1);
				ImageIcon icon = new ImageIcon(file.getPath());
				frame.getLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}
	
	public void menuDo(ViewerFrame frame,String cmd){
		//��
		if(cmd.equals("��(O)")){
			open(frame);
		}
		if(cmd.equals("�Ŵ�(M")){
			zoom(frame, true);
		}
		if(cmd.equals("��С(S)")){
			zoom(frame, false);
		}
		if(cmd.equals("��һ��(L)")){
			last(frame);
		}
		if(cmd.equals("��һ��(P)")){
			next(frame);
		}
		
		if(cmd.equals("�˳�(X)")){
			System.exit(0);
		}
	}
}
