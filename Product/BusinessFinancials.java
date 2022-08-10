/*
This is the calculation class that obtains all
the financial information of a business that
involve net present value
*/
//package businessinvestments;

public class BusinessFinancials
{
     private double wacc;
     private double weightedEquity;
     private double weightedDebt;
     private double equityCost;
     private double profit;
     private double freeCashFlow;
     private double netPresentValue;
     private double workingCapital;

     public BusinessFinancials()
     {
        wacc = 0;
        weightedEquity = 0;
        weightedDebt = 0;
        equityCost = 0;
        profit = 0;
        freeCashFlow = 0;
        netPresentValue = 0;
        workingCapital = 0;
     }
     public BusinessFinancials(double pWacc, double pWeightedEquity, double pWeightedDebt, double pEquityCost,
             double pProfit, double pFreeCashFlow, double pNetPresentValue, double pWorkingCapital)
     {
        wacc = pWacc;
        weightedEquity = pWeightedEquity;
        weightedDebt = pWeightedDebt;
        equityCost = pEquityCost;
        profit = pProfit;
        freeCashFlow = pFreeCashFlow;
        netPresentValue = pNetPresentValue;
        workingCapital = pWorkingCapital;
     }
     //Sets info
     public void setWacc(double pWacc)
     {
         this.wacc = pWacc;
     }
     //Sets info
     public void setWeightedEquity(double pWeightedEquity)
     {
         this.weightedEquity = pWeightedEquity;
     }
     //Sets info
     public void setWeightedDebt(double pWeightedDebt)
     {
         this.weightedDebt = pWeightedDebt;
     }
     //Sets info
     public void setEquityCost(double pEquityCost)
     {
         this.equityCost = pEquityCost;
     }
     //Sets info
     public void setProfit(double pProfit)
     {
         this.profit = pProfit;
     }
     //Sets info
     public void setFreeCashFlow(double pFreeCashFlow)
     {
         this.freeCashFlow = pFreeCashFlow;
     }
     //Sets info
     public void setNetPresentValue(double pNetPresentValue)
     {
         this.netPresentValue = pNetPresentValue;
     }
     //Sets info
     public void setWorkingCapital(double pWorkingCapital)
     {
         this.workingCapital = pWorkingCapital;
     }
     //gets info
     public double getWacc()
     {
         return this.wacc;
     }
     //gets info
     public double getWeightedEquity()
     {
         return this.weightedEquity;
     }
     //gets info
     public double getWeightedDebt()
     {
         return this.weightedDebt;
     }
     //gets info
     public double getEquityCost()
     {
         return this.equityCost;
     }
     //gets info
     public double getProfit()
     {
         return this.profit;
     }
     //gets info
     public double getFreeCashFlow()
     {
         return this.freeCashFlow;
     }
     //gets info
     public double getNetPresentValue()
     {
         return this.netPresentValue;
     }
     //gets info
     public double getWorkingCapital()
     {
         return this.workingCapital;
     }
     //calculates for wacc
     public double waccCalculation(double weightedEquity, double weightedDebt,
             double equityCost, double debtCost)
     {
         wacc = weightedDebt * debtCost + weightedEquity * equityCost;

         return wacc;
     }
     //Calculates for debt weight
     public double weightedDebtCalculation(double actualEquity, double actualDebt)
     {
         weightedDebt = actualDebt/(actualDebt + actualEquity);

         return weightedDebt;
     }
     //calculates for equity weight
     public double weightedEquityCalculation(double actualEquity, double actualDebt)
     {
         weightedEquity = actualEquity/(actualEquity + actualDebt);

         return weightedEquity;
     }
     //calculates for equity cost
     public double equityCostCalculation(int riskFactor, double debtCost)
     {
         equityCost = debtCost + riskFactor;

         return equityCost;
     }
     //calculates for profit
     public double profitCalculation(double revenue, double costs)
     {
         profit = revenue - costs;

         return profit;
     }
     //calculates for woking capital
     public double calculateWorkingCapital(double currentAssets, double currentLiabilities)
     {
         workingCapital = currentAssets - currentLiabilities;

         return workingCapital;
     }
     //calculates for free cash flow
     public double cashFlowCalculation(double profit, double workingCapital, double previousWorkingCapital, double capitalExpenditure, double depreciation)
     {
        double changeWorkingCapital = previousWorkingCapital - workingCapital;

        freeCashFlow = profit + depreciation - capitalExpenditure + changeWorkingCapital;

        return freeCashFlow;
     }
     //calculates for net present value
     public double presentValueCalculation(int time, double freeCashFlow, double wacc)
     {
         int maxTime = 5;

         //Checks if specified time is on year 5 or not
         if (time<maxTime)
         {
            double divisor = 1;

            for (int i=0; i<time; i++)
            {
                divisor = divisor * (1+wacc/100);
            }
            netPresentValue = netPresentValue + freeCashFlow * 1/divisor;
         }
         else
         {
            double GROWTH_RATE = 5;

            System.out.println("------------------------");
            System.out.println(freeCashFlow);
            System.out.println(wacc/100);
            System.out.println(GROWTH_RATE/100);
            System.out.println("------------------------");

            netPresentValue = netPresentValue + (freeCashFlow * (1 + GROWTH_RATE/100))/(wacc/100-GROWTH_RATE/100);
         }

         System.out.println(netPresentValue);

         return netPresentValue;
     }

     /*Returns string with the variables Wacc, WeightedEquity, WeightedDebt, EquityCost,
              Profit, FreeCashFlow, NetPresentValue, WorkingCapital*/
     @Override
     public String toString()
     {
        return "BusinessFinancials{" + "wacc=" + wacc + ", weightedEquity=" +
                weightedEquity + ", weightedDebt=" + weightedDebt +
                "equityCost" + equityCost + "profit=" +
                profit + "freeCashFlow=" + freeCashFlow + "netPresentValue=" +
                netPresentValue + "workingCapital" + workingCapital + '}';
     }

     public static void main(String[] args)
     {
        //Opens the log in frame
        BusinessFinancials bObj = new BusinessFinancials();

        bObj.presentValueCalculation(1, 59 ,18.4);
        bObj.presentValueCalculation(2, 69 ,18.4);
        bObj.presentValueCalculation(3, 88 ,18.4);
        bObj.presentValueCalculation(4, 100 ,18.4);
        bObj.presentValueCalculation(5, 90 ,18.4);
     }
}
