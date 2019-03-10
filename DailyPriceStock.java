import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DailyPriceStock {

	public static void main(String[] args) {
		System.out.println("Insert prices of items going from beginning of the day to the end.");
		PriceChecker boo = new PriceChecker();
		boo.add(new PriceNode(90.23,1));
		boo.add(new PriceNode(10.52,2));
		boo.add(new PriceNode(60,3));
		boo.add(new PriceNode(30,4));
		boo.add(new PriceNode(100.82,5));
		boo.add(new PriceNode(0,6));
		boo.add(new PriceNode(30,7));
		boo.add(new PriceNode(30,8));
		boo.add(new PriceNode(30,9));
		boo.add(new PriceNode(70,10));
		boo.sort();
		boo.process();

	}

}

class PriceChecker{
	PriceNode holdlow;
	PriceNode currentlow;
	PriceNode currenthigh;
	double Difference = 0;
	List<PriceNode> list;

	
	public PriceChecker() {
		list = new ArrayList<PriceNode>();
	}
	
	public void add(PriceNode x) {
		list.add(x);
	}
	
	public void sort() {
		Collections.sort(list,  new Comparator<PriceNode>() {
			public int compare(PriceNode x, PriceNode y) {
				return x.compareTo(y);
			}
		});
	}
	
	public void print() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).price + " and " + list.get(i).time);
		}
	}
	
	public void process() {
		for (int i = 0; i < list.size(); i++) {
			if (i==0) 
			{
				holdlow = list.get(i);
				currentlow = list.get(i);
			} else if (i==1) 
			{
				if (holdlow.price >= list.get(i).price) {
					holdlow = list.get(i);
					currentlow = list.get(i);
				} else {
					currenthigh = list.get(i);
					Difference = currenthigh.price-currentlow.price;
				}
			} else {
				if ((list.get(i).price-currentlow.price)<=0) {
					currentlow = list.get(i);
				} else {
					if ((list.get(i).price-currentlow.price)>Difference) {
						Difference = list.get(i).price-currentlow.price;
						holdlow = currentlow;
						currenthigh = list.get(i);
					}
				}
			}
		}
		
		System.out.println("Buy at " + holdlow.time + "time for $" + holdlow.price  + " and sell at " + currenthigh.time + " time for $" + currenthigh.price + ".\nIt will resut in a net"
				+ "profit of $" + (currenthigh.price-holdlow.price) + "!");
	}
}

class PriceNode implements Comparable<PriceNode>{
	double price;
	double time;
	public PriceNode(double x, double y) {
		price = x;
		time  = y;
	}
	@Override
	public int compareTo(PriceNode o) {
		if (this.time > o.time) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}
