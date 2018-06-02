package main;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class MultiTalkServer{
	static int clientnum=0; //��̬��Ա��������¼��ǰ�ͻ��ĸ���
    public static List<PrintWriter> allOutput;
    public static List<Socket> allSockets;
	private ServerSocket serversocket;
	private Socket stemp;
	public void start() //throws IOException {
		{boolean listening=true;
		try{
			//����һ��ServerSocket�ڶ˿�4700�����ͻ�����
			serversocket=new ServerSocket(6666);
			allOutput = new ArrayList<PrintWriter>();
			allSockets = new ArrayList<Socket>();
		}catch(IOException e) {
			System.out.println("Could not listen on port:6666.");
			//������ӡ������Ϣ
			System.exit(-1); //�˳�
		}
		while(listening){ //ѭ������
		  //�������ͻ����󣬸��ݵõ���Socket����Ϳͻ��������������̣߳�������֮
			try {
				stemp = serversocket.accept();//����һ��socket�׽�����ͻ���������
				allSockets.add(stemp);//���뵽socket������ȥ
				new ServerThread(stemp,clientnum).start();//����һ�������߳�
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    clientnum++; //���ӿͻ�����
		}
		//serversocket.close(); //�ر�ServerSocket
	}  
	//public static void main(String[] args) throws IOException {
		//MultiTalkServer MTS = new MultiTalkServer();
		//MTS.start();
	//}
}
