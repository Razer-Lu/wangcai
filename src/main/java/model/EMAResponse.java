package model;

import java.util.List;

public class EMAResponse extends GeneralResponse {
	public EMAResponse(Stock stock, List<EMAUnit> emas) {
		this.emas = emas;
		this.stock = stock;
	}

	List<EMAUnit> emas;

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<EMAUnit> getEmas() {
		return emas;
	}

	public void setEmas(List<EMAUnit> emas) {
		this.emas = emas;
	}

}
