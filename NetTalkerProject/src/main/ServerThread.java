package main;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	Socket socket=null; //�����뱾�߳���ص�Socket����Ҳ���ǶԽӿͻ��˵�socket����
	String host;
	InetAddress address ;//��¼�ͻ��˵ĵ�ַ
	int clientnum; //���汾���̵Ŀͻ�����
	private String aimAddress;
	private int aimPort;
	public ServerThread(Socket socket,int num) { //���캯��
	     this.socket=socket; //��ʼ��socket����
		 clientnum=num+1; //��ʼ��clientnum����
		 
         //ͨ��socket���Ե�֪Զ�˼������Ϣ��Ҳ���ǿͻ��˵ļ����
         address = socket.getInetAddress();
         //��ȡԶ�̼����IP��Ҳ���ǿͻ��˵ļ����
         host = address.getHostAddress();
	}
	public void run() { //�߳�����
		boolean flag = false;
		boolean flag1 =false;
        PrintWriter pw=null;
		try{
			String line;
			String judge;
			
			//��Socket����õ�����������������Ӧ��BufferedReader����
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//��Socket����õ��������������PrintWriter����
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			//��ϵͳ��׼�����豸����BufferedReader����
			pw = new PrintWriter(os,true);
			addOut(pw);
//		    while((judge = is.readLine())!=null) {
//		    	if(judge == "password:tiaf") {
//		    		flag =true;
//		    	}
//		    }
			while((line = is.readLine())!=null){//������ַ���Ϊ "bye"����ֹͣѭ��
				if(line.equals("password:tiaf")) {
		    		flag =true;
		    		continue;
		    	}
				if(!flag1) {
					sendMessageToAll("�û�"+clientnum+"������");
					flag1 = true;
				}
				if(!flag) {
					sendMessageToAll("�û�"+clientnum+"˵:"+line);
				}else {
					sendFileToAll(line);
				}
				}
		}catch(Exception e){
			System.out.println("Error:"+e);//������ӡ������Ϣ
		}finally{
            /*
             * ���ÿͻ��������˶Ͽ�����ʱ��Ӧ�����ÿ�
             * ���˵�������ӹ�����ɾ����
             */
            removeOut(pw);
             
            //�㲥���û�����
            if(!flag) {
            	sendMessageToAll("�û�"+clientnum+"�뿪��");
            }
            
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
    private synchronized void sendFileToAll(String m){
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
