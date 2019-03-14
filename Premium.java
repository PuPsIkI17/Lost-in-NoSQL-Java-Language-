//Pislari Vadim 323CB

public class Premium extends Basic {
	private int points_premium;
	private String name;

	public Premium(String name, int basic, int points) {
		super(name, basic);
		this.name = name;
		this.points_premium = points;
	}

	public String get_ps() {
		return name;
	}

	public int get_pp() {
		return points_premium;
	}

	public void decrement_pp() {
		points_premium--;
	}

}
