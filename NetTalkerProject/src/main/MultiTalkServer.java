package main;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MultiTalkServer{
	static int clientnum=0; //静态成员变量，记录当前客户的个数
    public static List<PrintWriter> allOutput;
    public static List<Socket> allSockets;
	private ServerSocket serversocket;
	private Socket stemp;
	public void start() //throws IOException {
		{boolean listening=true;
		try{
			//创建一个ServerSocket在端口4700监听客户请求
			serversocket=new ServerSocket(6666);
			allOutput = new ArrayList<PrintWriter>();
			allSockets = new ArrayList<Socket>();
		}catch(IOException e) {
			System.out.println("Could not listen on port:6666.");
			//出错，打印出错信息
			System.exit(-1); //退出
		}
		while(listening){ //循环监听
		  //监听到客户请求，根据得到的Socket对象和客户计数创建服务线程，并启动之
			try {
				stemp = serversocket.accept();//返回一个socket套接字与客户端相连接
				allSockets.add(stemp);//加入到socket数组里去
				new ServerThread(stemp,clientnum).start();//启动一个服务线程
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    clientnum++; //增加客户计数
		}
		//serversocket.close(); //关闭ServerSocket
	}  
	//public static void main(String[] args) throws IOException {
		//MultiTalkServer MTS = new MultiTalkServer();
		//MTS.start();
	//}
}
