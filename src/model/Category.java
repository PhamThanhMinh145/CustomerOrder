package model;

public class Category implements Comparable<Category>{
    private String categoryID;
    private String cateName;

    public Category(String categoryID, String cateName) {
        this.categoryID = categoryID;
        this.cateName = cateName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public void showCategory(){
        String msg;
        msg = String.format("|%-6s|%-15s|", categoryID, cateName);
        System.out.printf(msg);
    }
    public void notFound(){
        System.out.println("Not Found");
        
    }

    @Override
    public int compareTo(Category o) {
        return  this.categoryID.compareTo(o.categoryID);
    }
}
