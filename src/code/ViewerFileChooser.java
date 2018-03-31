package code;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ViewerFileChooser extends JFileChooser{
	public ViewerFileChooser(){
		super();
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}
	
	//使用自定义的路径创建一个FileChooser
	public ViewerFileChooser(String currentDirectoryPath){
		super(currentDirectoryPath);
		setAcceptAllFileFilterUsed(false);
		addFilter();
	}
	
	private void addFilter(){
		this.addChoosableFileFilter(new MyFileFilter(new String[]{".BMP"},"BMP (*.BMP)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[]{".JPG",".JPEG",".JPE",".JFIF"}, 
				"JPEG (*.JPG; *.JPEG; *.JPE; *.JFIF"));
		this.addChoosableFileFilter(new MyFileFilter(new String[]{".GIF"},"GIF (*.GIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[]{".TIF"},"TIF (*.TIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
				"PNG (*.PNG)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
				"ICO (*.ICO)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
				".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
				".PNG", ".ICO" }, "所有图形文件"));
	}
	
	class MyFileFilter extends FileFilter{
		//后缀名数组
		String[] suffarr;
		
		String description;
		
		public MyFileFilter(){
			super();
		}
		
		public MyFileFilter(String[] suffarr,String description){
			super();
			this.suffarr = suffarr;
			this.description = description;
		}
		
		public boolean accept(File f){
			for(String s : suffarr){
				if(f.getName().toUpperCase().endsWith(s)){
					return true;
				}
			}
			return f.isDirectory();
		}
		
		public String getDescription(){
			return this.description;
		}
	}
}
