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
	
	//定义放大和缩小时的比例
	private double range = 0.2;
	
	//目前的文件夹
	private File currentDirectory = null;
	
	//目前文件夹下面的所有图片
	private List<File> currentFiles = null;
	
	//目前打开的图片文件
	private File currentFile = null;
	
	/*
	 * 私有构造器*/
	private ViewerService(){
		
	}
	
	/*
	 * 获取单态实例*/
	public static ViewerService getInstance(){
		if(service == null){
			service = new ViewerService();
		}
		return service;
	}
	
	public void open(ViewerFrame frame){
		//如果选择打开
		if(fileChooser.showOpenDialog(frame) == ViewerFileChooser.APPROVE_OPTION){
			//赋值给目前打开的文件currentFile
			this.currentFile = fileChooser.getSelectedFile();
			
			//获取文件路径
			String name = this.currentFile.getPath();
			
			//获取目前文件的文件夹
			File cd = fileChooser.getCurrentDirectory();
			
			//如果文件夹有所改变
			if(this.currentDirectory == null || cd != this.currentDirectory){
				//获取fileChooser的所有FileFilter
				FileFilter[] fileFilters = fileChooser.getChoosableFileFilters();
				File[] files = cd.listFiles();//cd为改变后的文件夹地址，获取其中的所有文件
				this.currentFiles = new ArrayList<File>();
				
				for(File f:files){
					for(FileFilter filter : fileFilters){
						//如果是图片文件
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
		//获取放大或者缩小的乘比
		double EnlargeRange = isEnlarge ? (1 + range) : (1 - range);
		
		//获取目前的图片
		ImageIcon icon = (ImageIcon) frame.getLabel().getIcon();
		
		if(icon != null){
			int width = (int) (icon.getIconWidth() * EnlargeRange);
			
			//获取改变大小之后的图片
			ImageIcon newicon = new ImageIcon(icon.getImage().getScaledInstance(width, -1, Image.SCALE_DEFAULT));
			
			//设置改动后的新图片
			frame.getLabel().setIcon(newicon);
		}
	}
	
	public void last(ViewerFrame frame){
		//如果有打开包含图片的文件夹
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
			
			//打开下一个
			if(index + 1 < this.currentFiles.size()){
				File file = (File) this.currentFiles.get(index + 1);
				ImageIcon icon = new ImageIcon(file.getPath());
				frame.getLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}
	
	public void menuDo(ViewerFrame frame,String cmd){
		//打开
		if(cmd.equals("打开(O)")){
			open(frame);
		}
		if(cmd.equals("放大(M")){
			zoom(frame, true);
		}
		if(cmd.equals("缩小(S)")){
			zoom(frame, false);
		}
		if(cmd.equals("上一个(L)")){
			last(frame);
		}
		if(cmd.equals("下一个(P)")){
			next(frame);
		}
		
		if(cmd.equals("退出(X)")){
			System.exit(0);
		}
	}
}
