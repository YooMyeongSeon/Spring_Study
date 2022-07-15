//네트워크 프로그래밍 구현 평가
//시험일 : 2022년 07월 14일
//제한시간 : 180분
//훈련생명 : 유명선

package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		ServerSocket svs = null;
		Socket sk = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		int pn = 5702;

		try {
			svs = new ServerSocket(pn);
			System.out.println("[연결 대기중...]");
			
			sk = svs.accept();
			System.out.println("[연결되었습니다.]");
			
			boolean on = true;
			
			in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));

			while(on) {
				String im = in.readLine(); 
				
				if (im.equalsIgnoreCase("end")) {
					System.out.println("[클라이언트가 나갔습니다.]");
					on = false;
					break;
				}
				
				System.out.println("[클라이언트] : " + im);
				
				System.out.println("[보내기] : ");
				String om = scn.nextLine();
				
				out.write(om + "\n");
				out.flush();
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				scn.close();
				out.close();
				in.close();
				sk.close();
				svs.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}