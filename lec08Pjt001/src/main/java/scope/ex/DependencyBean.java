package scope.ex;

public class DependencyBean {

	private InjectionBean injectionBean;
	
	// 주입객체 만들고 이걸 이용해서 의존객체 만드는중 
	public DependencyBean(InjectionBean injectionBean) {
		System.out.println("DependencyBean : constructor");
		this.injectionBean = injectionBean;
	}
	
	public void setInjectionBean(InjectionBean injectionBean) {
		System.out.println("DependencyBean : setter");
		this.injectionBean = injectionBean;
	}
	
}