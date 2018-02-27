package server;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;

import com.google.gson.Gson;

import algorithm.EMA.EMA;
import dao.StockDAO;
import model.EMAResponse;

public class Server {
	
	public static void main(String[] args) {
		port(9999);
		get("/health", (request, response) -> "good");
		Gson gson = new Gson();
		StockDAO stockDao = new StockDAO();
		EMA ema = new EMA();
		// add stock to be measured
		post("/stock/:code/", "application/json", (request, response) -> {
			String code = request.params(":code");
			return new EMAResponse(stockDao.getStock(code), ema.getEmaLine());
		} , gson::toJson);
	}
}
