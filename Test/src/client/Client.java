//네트워크 프로그래밍 구현 평가
//시험일 : 2022년 07월 14일
//제한시간 : 180분
//훈련생명 : 유명선

package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		Socket sk = null;
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		String hst = "localhost";
		int pn = 5702;
		
		try {
			sk = new Socket(hst, pn);
			
			boolean on = true;
			
			in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));

			while(on) {
				System.out.println("[보내기] : ");
				String om = scn.nextLine();

				out.write(om + "\n");
				out.flush();
				
				if (om.equalsIgnoreCase("end")) {
					System.out.println("[종료합니다.]");
					on = false;
					break;
				}
				
				String im = in.readLine();
				
				System.out.println("[서버] : " + im);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}  finally {
			try {
				scn.close();
				out.close();
				in.close();
				sk.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}