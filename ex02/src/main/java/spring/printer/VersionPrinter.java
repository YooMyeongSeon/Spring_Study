package spring.printer;

public class VersionPrinter {

	private int majorVersion;
	private int miborVersion;
	
	public VersionPrinter() {}
	
	public VersionPrinter(int majorVersion, int miborVersion) {
		this.majorVersion = majorVersion;
		this.miborVersion = miborVersion;
	}
	
	public void print() {
		System.out.printf("이 프로그램 버전은 %d.%d입니다.\n", majorVersion, miborVersion);
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	public void setMiborVersion(int miborVersion) {
		this.miborVersion = miborVersion;
	}
}