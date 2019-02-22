package library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class libraryClient {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket(InetAddress.getLocalHost(),9090);
			OutputStream outSocket=socket.getOutputStream();
			OutputStreamWriter outWriter=new OutputStreamWriter(outSocket);
			BufferedReader keyReader=new BufferedReader(new InputStreamReader(System.in));
			BufferedReader socketReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line=null;
			while((line=keyReader.readLine())!=null){
				outWriter.write(line+"\r\n");
				outWriter.flush();
				line=socketReader.readLine();
				if(line.equals("*****欢迎进入图书馆*****\n登录（E）      注册（S）")){
					if(keyReader.readLine().equals("s")||keyReader.readLine().equals("S")){
						System.out.print("请输入用户名：");
						String name=keyReader.readLine();
						System.out.println("请输入密码：");
						String passWord=keyReader.readLine();
						line=name+" "+passWord+"#s";
						outWriter.write(line);
						outWriter.flush();
					}else if(keyReader.readLine().equals("e")||keyReader.readLine().equals("E")){
						System.out.print("用户名：");
						String name=keyReader.readLine();
						System.out.println("密码：");
						String passWord=keyReader.readLine();
						line=name+" "+passWord+"#e\r\n";
						outWriter.write(line);
						outWriter.flush();
					}else{
						System.out.println(keyReader.readLine());
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
