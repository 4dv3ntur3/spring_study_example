package scope.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// 주입객체 만들고 의존객체 만들고 있음
		InjectionBean injectionBean = 
				ctx.getBean("injectionBean", InjectionBean.class);
		
		// 같은 곳에 생성된 객체를 같이 가리키고 있음 
		// scope가 없어서 
		// scope 하면 두 번 호출됨 
		// 01 != 02라고 뜸
		// 전혀 다른 공간에 존재하는 다른 객체 
		DependencyBean dependencyBean01 = 
				ctx.getBean("dependencyBean", DependencyBean.class);
		
		DependencyBean dependencyBean02 = 
				ctx.getBean("dependencyBean", DependencyBean.class);
		
		if(dependencyBean01.equals(dependencyBean02)) {
			System.out.println("dependencyBean01 == dependencyBean02");
		} else {
			System.out.println("dependencyBean01 != dependencyBean02");
		}
		
		
		ctx.close();
		
	}
	
}
