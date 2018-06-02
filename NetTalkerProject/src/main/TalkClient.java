package main;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class TalkClient {
	private static Socket socket;
	private Scanner sin;
	private static Scanner choice;
	public static char ch;
	public TalkClient(){
		System.out.println("正在尝试连接目标服务端...");
		//向本机的6666端口发出客户请求
		try {
			socket = new Socket("127.0.0.1",6666);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("与目标服务端连接成功!");
	}
	public void start() {

		try{
			GetServerMessage GSM = new GetServerMessage();
	        new Thread(GSM).start();
			sin = new Scanner(System.in);
	        //	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//由Socket对象得到输出流，并构造PrintWriter对象
			PrintWriter os=new PrintWriter(socket.getOutputStream(),true);
			String readline = null;
			while(true){//若从标准输入读入的字符串为 "bye"则停止循环
				//将从系统标准输入读入的字符串输出到Server
			//	readline = sin.readLine();
				readline=sin.nextLine(); //从系统标准输入读入一字符串
				os.println(readline);
				os.flush();//刷新输出流，使Server马上收到该字符串
				//在系统标准输出上打印读入的字符串
				//从Server读入一字符串，并打印到标准输出上		
				
			} //继续循环
		}catch(Exception e) {
			System.out.println("Error"+e); //出错，则打印出错信息
		}
	}
	public static void main(String args[]) {
        TalkClient tc = new TalkClient();
      /*  System.out.println("Choose the operation that you want:\n"
        		+ "1.Send message to All the Clients\n"
        		+ "2.Send message to the appointed Client\n"
        		+ "3.Send Files to the appointed Client\n");
        
     /*   choice = new Scanner(System.in);
        ch = choice.nextLine().charAt(0);
        switch (ch) {
        case '1':
        	tc.start();
        	break;
        case '2':
        	
        	break;
        case '3':
        	break;
        }*/
        tc.start();
	   
	}
    private class GetServerMessage implements Runnable{
        public void run() {
            try {
                 /*通过这个内部类启动一个线程
                  * 不断读取服务器发来的消息
                  */
            	  BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  String message = null;
                  while((message = is.readLine()) != null){
                    System.out.println(message);
                  }
               } catch (Exception e) {
                  e.printStackTrace();
           }
       }

}
}