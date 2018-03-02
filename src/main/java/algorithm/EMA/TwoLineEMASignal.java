package algorithm.EMA;

import algorithm.signal.Decision;
import algorithm.signal.Signal;
import model.EMAUnit;

import java.util.List;

public class TwoLineEMASignal extends Signal {
    private EMA shortEMA = null;
    private EMA longEMA = null;
    private int size = 0;
    private static final int CROSS_WAIT_WINDOW = 3;

    public TwoLineEMASignal(final List<Double> list, final int shortPeriod, final int longPeriod) {
        this.longEMA = new EMA(list, longPeriod);
        this.shortEMA = new EMA(list, shortPeriod);
        this.size = list.size();
        calculate();
    }

    public TwoLineEMASignal(Decision decision, Double confidence, String comments) {
        super(decision, confidence, comments);
        this.longEMA = new EMA();
        this.shortEMA = new EMA();
    }

    public void calculate() {
        if (this.size < 2) {
            super.decision = Decision.NoSignal;
            super.confidence = 1d;
            super.comments = "list size less than 2";
            return;
        }

        List<EMAUnit> shortNDays = shortEMA.getLastNEMA(CROSS_WAIT_WINDOW);
        List<EMAUnit> longNDays = longEMA.getLastNEMA(CROSS_WAIT_WINDOW);

        boolean strongBuySignal = true;
        boolean strongSellSignal = true;
        boolean keepSignal = false;
        boolean notBuySignal = false;

        if (CROSS_WAIT_WINDOW < 2) {
            return;
        }

        for (int i = 0; i < CROSS_WAIT_WINDOW; i++) {
            EMAUnit shortCur = shortNDays.get(i);
            EMAUnit longCur = longNDays.get(i);

            strongBuySignal &= checkStrongBuySignal(shortCur, longCur, i);
            strongSellSignal &= checkStrongSellSignal(shortCur, longCur, i);
            keepSignal &= checkKeepSignal(shortCur, longCur);
            notBuySignal &= checkNotBuySignal(shortCur, longCur);
        }

        if (strongBuySignal) {
            super.decision = Decision.Buy;
        } else if (strongSellSignal) {
            super.decision = Decision.Sell;
        } else if (keepSignal) {
            super.decision = Decision.Keep;
        } else if (notBuySignal) {
            super.decision = Decision.NotBuy;
        } else {
            super.decision = Decision.NoSignal;
        }

    }

    private boolean checkStrongBuySignal(EMAUnit shortCur, EMAUnit longCur, int idx) {
        if (idx == 0) return shortCur.getScore() == longCur.getScore();

        return shortCur.getScore() > longCur.getScore();
    }

    private boolean checkStrongSellSignal(EMAUnit shortCur, EMAUnit longCur, int idx) {
        if (idx == 0) return shortCur.getScore() == longCur.getScore();

        return shortCur.getScore() < longCur.getScore();
    }

    private boolean checkKeepSignal(EMAUnit shortCur, EMAUnit longCur) {
        return shortCur.getScore() > longCur.getScore();
    }

    private boolean checkNotBuySignal(EMAUnit shortCur, EMAUnit longCur) {
        return shortCur.getScore() < longCur.getScore();
    }

}
