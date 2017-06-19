package cn.practice;

public class OverrideHashCode {

	private Integer id;
	private String name;
	
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = result * prime + id;
		result = result * prime + ((name == null)?0:name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof OverrideHashCode))
			return false;
		OverrideHashCode other = (OverrideHashCode)obj;
		if(id == null){
			if(other.id != null)
				return false;
		}else if(!(id == other.id))
			return false;
		if(name == null){
			if(other.name != null)
				return false;
		}else if(!(name.equals(other.name)))
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
}
