package ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main01 {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\GREEN\\Documents\\Spring_Study\\file\\djtjdhrh.jpg");
		
		InputStream is = new FileInputStream(file);
		OutputStream os = new FileOutputStream("C:\\Users\\GREEN\\Documents\\Spring_Study\\file\\djtjdhrh_02.jpg");
		
		byte[] readByte = new byte[100]; //100칸의 바이트 배열
		
		int readChkNum;
		
		String image = "";
		while ((readChkNum = is.read(readByte)) != -1) { //읽어온 값이 없을땐 -1
			image += new String(readByte);
			os.write(readByte); //해당 위치에 기록
		}
		
		System.out.println(image);
		
		os.flush();
		
		is.close();
		os.close();
		
		System.out.println("파일 복사가 완료 되었습니다.");
		
		Scanner scan = new Scanner(System.in);
	}
}