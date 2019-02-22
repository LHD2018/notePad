package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

class User implements Serializable{
	private static final long serialVersionUID = 1L;
		private String name;
		private String passWord;
		public User(String name, String passWord) {
			this.name = name;
			this.passWord = passWord;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassWord() {
			return passWord;
		}
		public void setPassWord(String passWord) {
			this.passWord = passWord;
		}
		@Override
		public String toString() {
			return "name:"+this.name+"passWord:"+this.passWord;
		}
	}
class libraryServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(9090);
			Socket socket=serverSocket.accept();
			BufferedReader socketReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStreamWriter socketWriter=new OutputStreamWriter(socket.getOutputStream());
			String line=null;
			while((line=socketReader.readLine())!=null){
				if(line.equals("www.library.com")){
					socketWriter.write("*****»¶Ó­½øÈëÍ¼Êé¹Ý*****\nµÇÂ¼£¨E£©      ×¢²á£¨S£©\r\n");
				}else if(line.endsWith("#s")){
					line=socketReader.readLine();
					String[] str=line.split("#s");
					line=sign(str[0]);
					socketWriter.write(line+"\r\n");
					
				}else if(line.endsWith("#e")){
					line=socketReader.readLine();
					String[] str=line.split("#s");
					line=enter(str[0]);
					socketWriter.write(line+"\r\n");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		
	}
	public static String sign(String info) throws Exception{
		File file=new File("E:\\java\\users.txt");
		FileOutputStream fileOutputStream=new FileOutputStream(file);
		ObjectOutputStream objectOutputSream=new ObjectOutputStream(fileOutputStream);
		String[] infos=info.split(" ");
		String name=infos[0];
		String passWord=infos[1];
		User user=new User(name,passWord);
		objectOutputSream.writeObject(user);
		return name+"×¢²á³É¹¦";
	}
	public static String enter(String info) throws Exception, IOException{
		File file=new File("E:\\java\\users.txt");
		ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
		String[] infos=info.split(" ");
		String name=infos[0];
		String passWord=infos[1];
		User user=null;
		boolean flag=true;
		while((user=(User)objectInputStream.readObject())!=null){
			if(name.equals(user.getName())&&passWord.equals(user.getPassWord())){
				flag=false;
				info= name+"µÇÂ¼³É¹¦";
			}
		}
		if(flag){
			info= "µÇÂ¼Ê§°Ü";
		}
		return info;
		
	}
}
