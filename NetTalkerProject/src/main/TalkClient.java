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
		System.out.println("���ڳ�������Ŀ������...");
		//�򱾻���6666�˿ڷ����ͻ�����
		try {
			socket = new Socket("127.0.0.1",6666);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("��Ŀ���������ӳɹ�!");
	}
	public void start() {

		try{
			GetServerMessage GSM = new GetServerMessage();
	        new Thread(GSM).start();
			sin = new Scanner(System.in);
	        //	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//��Socket����õ��������������PrintWriter����
			PrintWriter os=new PrintWriter(socket.getOutputStream(),true);
			String readline = null;
			while(true){//���ӱ�׼���������ַ���Ϊ "bye"��ֹͣѭ��
				//����ϵͳ��׼���������ַ��������Server
			//	readline = sin.readLine();
				readline=sin.nextLine(); //��ϵͳ��׼�������һ�ַ���
				os.println(readline);
				os.flush();//ˢ���������ʹServer�����յ����ַ���
				//��ϵͳ��׼����ϴ�ӡ������ַ���
				//��Server����һ�ַ���������ӡ����׼�����		
				
			} //����ѭ��
		}catch(Exception e) {
			System.out.println("Error"+e); //�������ӡ������Ϣ
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
                 /*ͨ������ڲ�������һ���߳�
                  * ���϶�ȡ��������������Ϣ
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