package graphlib;

public class MyGraphVertex {
	private int id;
	private String label;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public MyGraphVertex(int id, String label)
	{
		setId(id);
		setLabel(label);		
	}
	
	@Override
	public String toString() {
		return label;		
	}
	
}
