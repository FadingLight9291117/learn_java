package bio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Socket socket=new Socket(InetAddress.getByName("127.0.0.1"), 8001);//需要服务端先开启
			
			//同一个通道，服务 端的输出流就是客户端的输入流；服务器端的输入流就是客户端的输出流
			InputStream inputStream=socket.getInputStream();//开启通道的输入流
			BufferedReader brNet=new BufferedReader(new InputStreamReader(inputStream));
			
			OutputStream outputStream=socket.getOutputStream();//开启通道的输出流
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			
			BufferedReader brKey = new BufferedReader(new InputStreamReader(System.in));
			while(true) 
			{
				String strWord = brKey.readLine();
				if(strWord.equalsIgnoreCase("exit"))
				{
					break;
				}
				else
				{
					System.out.println("I want to send: "+ strWord);
					dataOutputStream.write((strWord+System.getProperty("line.separator")).getBytes());

					System.out.println("Server said: "+ brNet.readLine());
				}
	
			}
			
			dataOutputStream.close();
			brNet.close();
			brKey.close();
			socket.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
