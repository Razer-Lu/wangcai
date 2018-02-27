package model;

import algorithm.signal.Trend;

public class EMAUnit {

	private Double score;
	private Trend trend;

	public EMAUnit(double score, Trend trend) {
		this.score = score;
		this.trend = trend;
	}

	public Double getScore() {
		return this.score;
	}

	public Trend getTrend() {
		return this.trend;
	}
}
