package common.controller;

//추상클래스
//Action interface를 상속받아서 execute() 추상메소드를 멤버로 갖는다.
abstract public class AbstractAction implements Action {

	private String viewPage; //viewPage 값을 가질 예정
	private boolean isRedirect; 
	//페이지 이동방식이 redirect면 true, forward는 false값을 가질 예정
	
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	//boolean type의 경우 변수명이 getter, 변수명에서 is를 없애고 set붙인것이 setter
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}