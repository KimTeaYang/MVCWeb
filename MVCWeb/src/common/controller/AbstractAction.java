package common.controller;

//�߻�Ŭ����
//Action interface�� ��ӹ޾Ƽ� execute() �߻�޼ҵ带 ����� ���´�.
abstract public class AbstractAction implements Action {

	private String viewPage; //viewPage ���� ���� ����
	private boolean isRedirect; 
	//������ �̵������ redirect�� true, forward�� false���� ���� ����
	
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	//boolean type�� ��� �������� getter, �������� is�� ���ְ� set���ΰ��� setter
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}