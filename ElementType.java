public enum ElementType{
  CARBON("Carbon", 2, 0),
  HYDROGEN("Hydrogen", 6, 0),
  OXYGEN("Oxygen", 9, 0),
  NITROGEN("Nitrogen", 7, 0),
  HELIUM("Helium", 5, 0),
  SODIUM("Sodium", 4, 1),
  ALUMINUM("Aluminum", 0, 0),
  POTASSIUM("Potassium", 0, 1),
  CHLORINE("Chlorine", 4, 0),
  WATER("Water", 7, 1),
  CARBONWATER("Carbonated Water", 3, 0),
  AMMONIA("Ammonia", 1, 0),
  SALT("Salt", 1, 1),
  TONIC("Tonic", 6, 1),
  ORGANIC("Organic", 8, 0),
  SUGAR("Sugar", 5, 1),
  SILICON("Silicon", 2, 1),
  SODA("Soda", 3, 1);

  private final String name;
  private final int spriteX;
  private final int spriteY;

  ElementType(String name, int x, int y){
    this.name = name;
    this.spriteX = x;
    this.spriteY = y;
  }

  public String getName(){
    return name;
  }


  public int getY(){
    return spriteY*128;
  }

  public int getX(){
    return spriteX*128;
  }

}
