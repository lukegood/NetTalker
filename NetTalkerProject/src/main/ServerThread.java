package main;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	Socket socket=null; //保存与本线程相关的Socket对象，也就是对接客户端的socket对象
	String host;
	InetAddress address ;//记录客户端的地址
	int clientnum; //保存本进程的客户计数
	private String aimAddress;
	private int aimPort;
	public ServerThread(Socket socket,int num) { //构造函数
	     this.socket=socket; //初始化socket变量
		 clientnum=num+1; //初始化clientnum变量
		 
         //通过socket可以得知远端计算机信息，也就是客户端的计算机
         address = socket.getInetAddress();
         //获取远程计算机IP，也就是客户端的计算机
         host = address.getHostAddress();
	}
	public void run() { //线程主体

        PrintWriter pw=null;
		try{
			String line;
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			//由系统标准输入设备构造BufferedReader对象
			pw = new PrintWriter(os,true);
			addOut(pw);
		
			sendMessageToAll(host+"编号"+clientnum+"上线了");
			
			while((line = is.readLine())!=null){//如果该字符串为 "bye"，则停止循环
				sendMessageToAll(host+"编号"+clientnum+"说:"+line);}
		}catch(Exception e){
			System.out.println("Error:"+e);//出错，打印出错信息
		}finally{
            /*
             * 当该客户端与服务端断开连接时，应当将该客
             * 户端的输出流从共享集合删除。
             */
            removeOut(pw);
             
            //广播该用户下线
            sendMessageToAll(host+"编号"+clientnum+"下线了");
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	private synchronized void addOut(PrintWriter pw){
        MultiTalkServer.allOutput.add(pw);
    }
    private synchronized void removeOut(PrintWriter pw){
    	MultiTalkServer.allOutput.remove(pw);
    }
    private synchronized void sendMessageToAll(String m){
		for( Socket pw : MultiTalkServer.allSockets){
			  try {
				new PrintWriter (pw.getOutputStream(),true).println(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

}
