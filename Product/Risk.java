
//package businessinvestments;

public class Risk
{
     private int employeeAmount;
     private int businessYears;
     private int countriesAmount;
     private int competitorsAmount;
     private int riskPercentage;

     public Risk()
     {
         employeeAmount = 0;
         businessYears = 0;
         countriesAmount = 0;
         competitorsAmount = 0;
         riskPercentage= 0;
     }
     public Risk(int pEmployeeAmount, int pBusinessYears,
             int pCountriesAmount, int pCompetitorsAmount, int pRiskPercentage)
     {
         employeeAmount = pEmployeeAmount;
         businessYears = pBusinessYears;
         countriesAmount = pCountriesAmount;
         competitorsAmount = pCompetitorsAmount;
         riskPercentage = pRiskPercentage;
     }
     public void setEmployeeAmount(int pEmployeeAmount)
     {
         this.employeeAmount = pEmployeeAmount;
     }
     public void setBusinessYears(int pBusinessYears)
     {
         this.businessYears = pBusinessYears;
     }
     public void setCountriesAmount(int pCountriesAmount)
     {
         this.countriesAmount = pCountriesAmount;
     }
     public void setCompetitorsAmount(int pCompetitorsAmount)
     {
         this.competitorsAmount = pCompetitorsAmount;
     }
     public void setRiskPercentage(int pRiskPercentage)
     {
         this.riskPercentage = pRiskPercentage;
     }
     public int getEmployeeAmount()
     {
         return this.employeeAmount;
     }
     public int getBusinessYears()
     {
         return this.businessYears;
     }
     public int getCountriesAmount()
     {
         return this.countriesAmount;
     }
     public int getCompetitorsAmount()
     {
         return this.competitorsAmount;
     }
     public int getRiskPercentage()
     {
         return this.riskPercentage;
     }
     public int calculateRisk(int employeeAmount, int businessYears, int countriesAmount, int competitorsAmount)
     {
         setEmployeeAmount(employeeAmount);
         setBusinessYears(businessYears);
         setCountriesAmount(countriesAmount);
         setCompetitorsAmount(competitorsAmount);

         riskPercentage = 0;

         if (employeeAmount >= 1000)
         {
             riskPercentage = riskPercentage + 1;
         }
         else if (employeeAmount < 1000 && employeeAmount >= 100)
         {
            riskPercentage = riskPercentage + 2;
         }
         else if (employeeAmount < 100)
         {
             riskPercentage = riskPercentage + 3;
         }

         if (businessYears >= 15)
         {
             riskPercentage = riskPercentage + 1;
         }
         else if (businessYears >= 5 && businessYears < 15)
         {
             riskPercentage = riskPercentage + 2;
         }
         else if (businessYears < 5)
         {
             riskPercentage = riskPercentage + 3;
         }

         if (countriesAmount >= 11)
         {
             riskPercentage = riskPercentage + 1;
         }
         else if (countriesAmount >= 3 && businessYears < 11)
         {
             riskPercentage = riskPercentage + 2;
         }
         else if (countriesAmount < 3)
         {
             riskPercentage = riskPercentage + 3;
         }

         if (competitorsAmount >= 15)
         {
             riskPercentage = riskPercentage + 3;
         }
         else if (competitorsAmount < 15 && employeeAmount >= 6)
         {
            riskPercentage = riskPercentage + 2;
         }
         else if (competitorsAmount < 6)
         {
             riskPercentage = riskPercentage + 1;
         }

         return riskPercentage;
     }

     /*Returns string with the variables employeeAmount, businessYears,
        countriesAmount, competitorsAmount, and riskPercentage*/
     @Override
     public String toString()
     {
        return "Risk{" + "employeeAmount=" + employeeAmount + ", businessYears=" +
                businessYears + ", countriesAmount=" + countriesAmount +
                "competitorsAmount" + competitorsAmount + "riskPercentage=" +
                riskPercentage + '}';
     }

}
