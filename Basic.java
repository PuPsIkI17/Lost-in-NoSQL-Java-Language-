//Pislari Vadim 323CB

public class Basic extends Free {
	private int points_basic;

	public Basic(String name, int basic) {
		super();
		this.points_basic = basic;
	}

	public int get_pb() {
		return points_basic;
	}

	public void decrement_pb() {
		points_basic--;
	}
}
