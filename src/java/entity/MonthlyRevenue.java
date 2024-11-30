
package entity;


public class MonthlyRevenue {
    private int year;
    private int month;
    private double revenue;

    public MonthlyRevenue() {
    }

    public MonthlyRevenue(int year, int month, double revenue) {
        this.year = year;
        this.month = month;
        this.revenue = revenue;
    }
    

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "MonthlyRevenue{" + "year=" + year + ", month=" + month + ", revenue=" + revenue + '}';
    }
    
    
}
