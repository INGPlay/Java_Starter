package ch07.sec06.package2;

import ch07.sec06.package1.A;

public class D extends A {
	// 생성자 선언
	public D() {
		// A() 생성자 호출
		super();
	}
	
	// 메소드 선언
	public void method1() {
		// A 필드 변경
		this.field = "value";
		// A 메소드 호출
		this.Method();
	}
	
	public void method2() {
		// 직접 객체를 생성해서 사용하는 것은 안됨
//		A a = new A();
//		a.field = "value";
//		a.method();
	}
}
