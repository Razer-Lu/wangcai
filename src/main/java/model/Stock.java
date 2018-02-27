package model;

import java.util.List;

public class Stock {
	public Stock(StockBuilder sb) {
		code = sb.code;
		tps = sb.tps;
		end = sb.end;
		start = sb.start;
	}

	String code;
	long start;
	long end;
	List<TimeAndPrice> tps;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public List<TimeAndPrice> getTps() {
		return tps;
	}

	public void setTps(List<TimeAndPrice> tps) {
		this.tps = tps;
	}

	public class TimeAndPrice {
		public long time;
		public double price;
	}

	public static class StockBuilder {
		private String code;
		private long start;
		private long end;
		List<TimeAndPrice> tps;

		public StockBuilder setTps(List<TimeAndPrice> tps) {
			this.tps = tps;
			return this;
		}

		public StockBuilder setCode(String code) {
			this.code = code;
			return this;
		}

		public StockBuilder setStart(long start) {
			this.start = start;
			return this;
		}

		public StockBuilder setEnd(long end) {
			this.end = end;
			return this;
		}

		public Stock build() {
			return new Stock(this);
		}

	}
}
