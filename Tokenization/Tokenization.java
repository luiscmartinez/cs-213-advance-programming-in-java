import java.util.Scanner;
import java.io.*;
import java.util.ArrayList; 

class Main {
    public static void main(String[] args)throws IOException {
      File file = new File("../SalesData.txt");  
      Scanner FileScanner = new Scanner(file); 
      String line;
      int weekCount = 0;
      ArrayList<salesData> SalesData = new ArrayList<salesData>();
      while(FileScanner.hasNext()){
        weekCount++;
        line = FileScanner.nextLine();
        String[] token = line.split(",");
        double[] WeeklySales= new double[7];
        for (int i = 0 ;i< token.length;i++){
          WeeklySales[i]=Double.parseDouble(token[i]);
        }
        salesData sales = new salesData(WeeklySales);
        SalesData.add(sales);
        System.out.print("Weekly sales from week " + weekCount +" is $");
        System.out.printf("%.2f\n",sales.getTotalSales());
        System.out.print("Average for week " + weekCount +" is $");
        System.out.printf("%.2f\n",sales.getAvgSales());
        
      }
      double totalAllWeeks = 0, avgAllWeeks;
      for (int i = 0 ;i< weekCount ;i++){
           totalAllWeeks += SalesData.get(i).getTotalSales();
      }
      avgAllWeeks = totalAllWeeks/weekCount;
      System.out.print("Total sale of all weeks = ");
      System.out.printf("%.2f\n",totalAllWeeks);
      System.out.print("Average weekly sales = ");
      System.out.printf("%.2f\n",avgAllWeeks);
      
      double highest = SalesData.get(0).getTotalSales();
      int hightesWeek = 0;
      for(int i = 0 ;i < weekCount ;i++){
        if (SalesData.get(i).getTotalSales() > highest){
          highest = SalesData.get(i).getTotalSales();
          hightesWeek= i + 1;    
        }
      }
      double lowest = SalesData.get(0).getTotalSales();
      int lowestWeek = 0 ;
        for(int j = 0 ;j < weekCount ;j++){
          if (SalesData.get(j).getTotalSales() < lowest){
            lowest = SalesData.get(j).getTotalSales();
            lowestWeek = j+1;
          }
        }
        System.out.println("The week number with the highest amount of sales is: "+ hightesWeek + "\nThe week number with the lowest amount of sales is: " + lowestWeek);
      
    }
}
class salesData{
  double totalSales = 0, avgSales = 0;
  
  salesData(double[] WeeklySales){
    for(int i = 0; i<WeeklySales.length;i++){
      totalSales += WeeklySales[i];
      avgSales = totalSales/7;
    }
  }
  double getTotalSales(){
    return totalSales;
  }
  double getAvgSales(){
    return avgSales;
  }
}