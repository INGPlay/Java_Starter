package ch18.sec11;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesExample {

	public static void main(String[] args) {
		try {
			String data = """
					id: winter
					email: winter@mycompany.com
					tel: 010-123-1234
					""";
			
			// Path 개체 생성
			Path path = Paths.get("src/ch18/temp/user.txt");
			
			// 파일 생성 및 데이터 저장
			Files.writeString(path, data, Charset.forName("UTF-8"));
			
			// 파일 정보 얻기
			System.out.println("파일 유형: " + Files.probeContentType(path));
			System.out.println("파일 크기: " + Files.size(path) + " bytes");
			
			// 파일 읽기
			String content = Files.readString(path, Charset.forName("UTF-8"));
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
