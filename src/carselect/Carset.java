package carselect;

public class Carset {

	private String brend;
	private String[] models;
	
	protected Carset(String bd, String[] m) {
		brend = bd;
		models = m;
	}
	
	protected String getBrend() {
		return brend;
	}
	
	protected String[] getModelList() {
		return models; 
	}

}
