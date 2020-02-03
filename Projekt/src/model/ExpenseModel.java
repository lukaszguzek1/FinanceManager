package model;

public class ExpenseModel implements Comparable<ExpenseModel> {


    String name;
    String Type;
    double price;
    String Category;
    double entireLoan;

    public ExpenseModel(String name, String type, double price, double entireLoan) {
        Type = type;
        this.price = price;
        this.entireLoan = entireLoan;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getEntireLoan() {
        return entireLoan;
    }

    public void setEntireLoan(double entireLoan) {
        this.entireLoan = entireLoan;
    }

    public ExpenseModel(String name, String type, double price, String category) {
        Type = type;
        this.price = price;
        Category = category;
        this.name = name;
    }


    @Override
    public int compareTo(ExpenseModel o) {

return 0;
    }
}



