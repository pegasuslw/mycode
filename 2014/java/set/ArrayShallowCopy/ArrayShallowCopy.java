import java.util.Arrays;

import org.apache.commons.lang.builder.ToStringBuilder;

enum Color{
	Red, Orange, Yellow, Green, Indigo, Blue, Violet;
}

class Balloon{
	public Balloon(Color _color, int _id){
		color = _color;
		id    = id;
	}

	public String toString(){
		return new ToStringBuilder(this)
			.append("编号", id)
			.append("颜色", color)
			.toString();
	}

	public void setColor(Color _color){
		color = _color;
	}

	public Color getColor(){
		return color;
	}

	public void setId(int _id){
		id = _id;
	}

	public int getId(){
		return id;
	}


	private Color color;

	private int id;
}

public class ArrayShallowCopy{
	public static void main(String[] args){
		Balloon[] balloon1 = new Balloon[7];
		for(int i=0;i<balloon1.length;i++){
			balloon1[i] = new Balloon(Color.values()[i], i);
		}
		
		Balloon[] balloon2 = Arrays.copyOf(balloon1, 7);
		balloon2[6].setColor(Color.Blue);

		for(Balloon b1 : balloon2){
			System.out.println(b1);
		}

		System.out.println("----------------------------------------");
		for(Balloon b2 : balloon1){
			System.out.println(b2);
		}

	}
}


