package demo.bean;

public class Lion {

	String lionName;
	String lionType;
	int lionFood;

	public String getLionName() {
		return lionName;
	}

	public void setLionName(String lionName) {
		this.lionName = lionName;
	}

	public String getLionType() {
		return lionType;
	}

	public void setLionType(String lionType) {
		this.lionType = lionType;
	}

	public int getLionFood() {
		return lionFood;
	}

	public void setLionFood(int lionFood) {
		this.lionFood = lionFood;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return this.lionName.equals(((Lion)arg0).getLionName());
	}

	
}
