
public class Item {
	
	private String itemname;
	private double amount;
	private String unit;
	
	public Item() {
		setItemname(null);
		setAmount(0);
		setUnit(null);
	}
	
	public Item(String itemname, double amount, String unit) {
		setItemname(itemname);
		setAmount(amount);
		setUnit(unit);
	}
	
	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

}
