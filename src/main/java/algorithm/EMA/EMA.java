package algorithm.EMA;

import algorithm.signal.Trend;
import model.EMAUnit;

import java.util.ArrayList;
import java.util.List;

public class EMA {
    private List<EMAUnit> emaLine;

    private EMA(List<EMAUnit> emaLine) {
        this.emaLine = emaLine;
    }

    public EMA(final List<Double> list, final int number){
        calculate(list, number);
    }

    public EMA() {
        this.emaLine = new ArrayList<EMAUnit>();
    }

    /**
     * Calculate EMA,
     *
     * @param list :Price list to calculate，the first at head, the last at tail.
     * @return
     */
    private final void calculate(final List<Double> list, final int number) {
        this.emaLine = new ArrayList<EMAUnit>(list.size());
        // 开始计算EMA值，
        Double k = 2.0 / (number + 1.0);// 计算出序数

        Double emaPre = list.get(0);
        Double emaCur = list.get(0);// 第一天ema等于当天收盘价
        emaLine.add(new EMAUnit(emaCur, Trend.NoChange));

        for (int i = 1; i < list.size(); i++) {
            emaPre = emaCur;
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            emaCur = list.get(i) * k + emaCur * (1 - k);

            Trend trend = emaPre == emaCur ? Trend.NoChange : (emaPre > emaCur) ? Trend.Down : Trend.Up;
            emaLine.add(new EMAUnit(emaCur, trend));
        }
    }

    public List<EMAUnit> getEmaLine(){
        return this.emaLine;
    }

    public List<EMAUnit> getLastNEMA(int n){
        if(n> emaLine.size()){
            return emaLine;
        }
        return this.emaLine.subList(this.emaLine.size() - n, this.emaLine.size() -1 );
    }

}
