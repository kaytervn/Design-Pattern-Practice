package viblo.asia.demo;

import viblo.asia.builder.StudentBuilder;
import viblo.asia.concretebuilder.StudentConcreteBuilder;

public class Demo {

	public static void main(String[] args) {

		StudentBuilder studentBuilder = new StudentConcreteBuilder().setFirstName("Tran").setLastName("Quang Huy");

		System.out.println(studentBuilder.build());
	}
}