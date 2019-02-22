import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frameUtil.FrameTools;


public class NotePad {
	JFrame frame=new JFrame("���±�");
	JMenuBar bar=new JMenuBar();
	JMenu fileMenu=new JMenu("�ļ�");
	JMenu editMenu=new JMenu("�༭");
	JMenu swichMenu=new JMenu("����ļ�");
	JMenuItem openMenu=new JMenuItem("��");
	JMenuItem saveMenu=new JMenuItem("����");
	JMenuItem exitMenu=new JMenuItem("�˳�");
	JMenuItem encryptMenu=new JMenuItem("����");
	JMenuItem decodeMenu=new JMenuItem("����");
	
	JMenuItem fileMenu1=new JMenuItem("");
	JMenuItem fileMenu2=new JMenuItem("");
	JMenuItem fileMenu3=new JMenuItem("");
	
	TextArea area=new TextArea(20,10);
	
	public void initNotepad(){
		Font x=new Font("lhd", 1, 15);
		area.setFont(x);
		swichMenu.add(fileMenu1);
		swichMenu.add(fileMenu2);
		swichMenu.add(fileMenu3);
		fileMenu.add(swichMenu);
		fileMenu.add(openMenu);
		fileMenu.add(saveMenu);
		fileMenu.add(exitMenu);
		openMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					FileDialog fileDialog=new FileDialog(frame,"��",FileDialog.LOAD);
					fileDialog.setVisible(true);
					String path=fileDialog.getDirectory();
					String fileName=fileDialog.getFile();
					String sourcePath=path+"\\"+fileName;
					open(sourcePath);
					setSwichMenu(sourcePath);
			}
		});
		fileMenu1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open(fileMenu1.getText());
				
			}
		});
		fileMenu2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open(fileMenu2.getText());
				
			}
		});
		fileMenu3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open(fileMenu3.getText());
				
			}
		});
		
		saveMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileDialog fileDialog=new FileDialog(frame,"���Ϊ",FileDialog.SAVE);
					fileDialog.setVisible(true);
					String path=fileDialog.getDirectory();
					String fileName=fileDialog.getFile();
					FileOutputStream fileOutputStream=new FileOutputStream(new File(path,fileName));
					String content=area.getText();
					content=content.replaceAll("\n", "\r\n");
					fileOutputStream.write(content.getBytes());
					fileOutputStream.close();
				}  catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		exitMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//to be CONTINUED
			}
		});
		editMenu.add(encryptMenu);
		editMenu.add(decodeMenu);
		encryptMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				encrypt();
			}
		});
		decodeMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				encrypt();
				
			}
		});
		
		bar.add(fileMenu);
		bar.add(editMenu);
		frame.add(bar,BorderLayout.NORTH);
		frame.add(area);
		FrameTools.initFrame(frame, 500, 400);
	}
	public void encrypt(){
		char[] buf=area.getText().toCharArray();
		for(int i=0;i<buf.length;i++){
			int a=(int)buf[i];
			a=a^3;
			buf[i]=(char)a;
		}
		area.setText(String.valueOf(buf));
	}
	public void open(String sourcePath){
		try {
			area.setText(null);
			BufferedReader bufferedReader=new BufferedReader(new FileReader(new File(sourcePath)));
			String line=null;
			while((line=bufferedReader.readLine())!=null){
				area.setText(area.getText()+line+"\r\n");
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static boolean flag1=false;
	static boolean flag2=false;
	public void setSwichMenu(String sourcePath){
		if(fileMenu1.getText().equals("")||flag1){
			fileMenu1.setText(sourcePath);
			flag1=false;
			flag2=true;
		}else if(fileMenu2.getText().equals("")||flag2){
			fileMenu2.setText(sourcePath);
			flag2=false;
		}else{
			fileMenu3.setText(sourcePath);
			flag1=true;
		}
	}
	public static void main(String[] args) {
		new NotePad().initNotepad();
	}
	
	
	
}
