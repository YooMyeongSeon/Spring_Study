package ex03.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerEx {
	
	public static void main(String[] args) {
		//자바 프로그램간의 네트워크 연결을 위해 사용하는 객체 : Socket
		ServerSocket server = null;
		Socket socket = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		Scanner scan = new Scanner(System.in);
		
		try {
			server = new ServerSocket(9995);
			System.out.println("연결 대기중..");
			
			socket = server.accept(); //응답받을 준비
			System.out.println("연결 되었습니다.");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine(); //클라이언트로부터 메세지 읽어들임
				
				if (inputMessage.equals("나가기")) {
					System.out.println("채팅을 종료합니다.");
					break;
				}
				
				System.out.println("[클라이언트] : " + inputMessage);
				
				System.out.println("보내기 : ");
				String outMessage = scan.nextLine(); //보낼 메세지 만들기
				
				out.write(outMessage+"\n"); //클라이언트에게 메세지 전송
				out.flush();
				
				if (outMessage.equals("나가기")) {
					System.out.println("채팅을 종료합니다.");
					break;
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				scan.close();
				out.close();
				in.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}