package ex03.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientEx {
	
	public static void main(String[] args) {
		Socket socket = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		Scanner scan = new Scanner(System.in);
		
		try {
			socket = new Socket("localhost", 9995);
			System.out.println("연결 성공");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				System.out.println("보내기 : ");
				String outMessage = scan.nextLine();

				out.write(outMessage+"\n");
				out.flush();
				
				if (outMessage.equalsIgnoreCase("나가기")) { //대소문자 구분을 하지않는 비교메서드
					System.out.println("채팅을 종료합니다.");
					break;
				}
				
				String inputMessage = in.readLine();
				
				if (inputMessage.equals("나가기")) {
					System.out.println("채팅을 종료합니다.");
					break;
				}
				
				System.out.println("[서버] : " + inputMessage);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}  finally {
			try {
				scan.close();
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}