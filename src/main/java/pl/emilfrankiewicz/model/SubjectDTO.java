package pl.emilfrankiewicz.model;

public class SubjectDTO {

	private String date;
	private String companyName;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Date: " + date + ", Company symbol: " + companyName;
	}
}
