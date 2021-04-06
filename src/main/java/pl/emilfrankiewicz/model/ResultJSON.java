package pl.emilfrankiewicz.model;

public class ResultJSON {

	private String volume;

	private String symbol;

	private String high;

	private String preMarket;

	private String low;

	private String from;

	private String afterHours;

	private String close;

	private String open;

	private String status;

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getPreMarket() {
		return preMarket;
	}

	public void setPreMarket(String preMarket) {
		this.preMarket = preMarket;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAfterHours() {
		return afterHours;
	}

	public void setAfterHours(String afterHours) {
		this.afterHours = afterHours;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ResultJSON [volume=" + volume + ", symbol=" + symbol + ", high=" + high + ", preMarket=" + preMarket
				+ ", low=" + low + ", from=" + from + ", afterHours=" + afterHours + ", close=" + close + ", open="
				+ open + ", status=" + status + "]";
	}
}
