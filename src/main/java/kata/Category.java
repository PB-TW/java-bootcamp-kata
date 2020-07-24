package kata;

public enum Category {
  ENTERTAINMENT("entertainment"),
  GROCERIES("groceries"),
  RESTAURANT("restaurant"),
  TRAVEL("travel");

  public final String description;

  Category(String description){
    this.description = description;
  }
}
