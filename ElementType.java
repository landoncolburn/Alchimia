// public enum ElementType{
//   CARBON("Carbon", 2, 0),
//   HYDROGEN("Hydrogen", 6, 0),
//   OXYGEN("Oxygen", 9, 0),
//   NITROGEN("Nitrogen", 7, 0),
//   HELIUM("Helium", 5, 0),
//   SODIUM("Sodium", 4, 1),
//   ALUMINUM("Aluminum", 0, 0),
//   POTASSIUM("Potassium", 0, 1),
//   CHLORINE("Chlorine", 4, 0),
//   WATER("Water", 7, 1),
//   CARBONWATER("Carbonated Water", 3, 0),
//   AMMONIA("Ammonia", 1, 0),
//   SALT("Salt", 1, 1),
//   TONIC("Tonic", 6, 1),
//   ORGANIC("Organic", 8, 0),
//   SUGAR("Sugar", 5, 1),
//   SILICON("Silicon", 2, 1),
//   SODA("Soda", 3, 1);

public enum ElementType{
  CARBON("Carbon", "resources/carbon.png"),
  HYDROGEN("Hydrogen", "resources/hydro.png"),
  OXYGEN("Oxygen", "resources/oxygen.png"),
  NITROGEN("Nitrogen", "resources/nitrogen.png"),
  HELIUM("Helium", "resources/helium.png"),
  SODIUM("Sodium", "resources/sodium.png"),
  ALUMINUM("Aluminum", "resources/aluminum.png"),
  POTASSIUM("Potassium", "resources/potassium.png"),
  CHLORINE("Chlorine", "resources/chlorine.png"),
  WATER("Water", "resources/water.png"),
  CARBONWATER("Carbonated Water", "resources/carbonwater.png"),
  AMMONIA("Ammonia", "resources/ammonia.png"),
  SALT("Salt", "resources/salt.png"),
  TONIC("Tonic", "resources/tonic.png"),
  ORGANIC("Organic", "resources/organic.png"),
  SUGAR("Sugar", "resources/sugar.png"),
  SODA("Soda", "resources/soda.png");

  private final String name;
  // private final int spriteX;
  // private final int spriteY;
  private final String link;

  // ElementType(String name, int x, int y){
  //   this.name = name;
  //   this.spriteX = x;
  //   this.spriteY = y;
  // }
  ElementType(String name, String link){
    this.name = name;
    this.link = link;
  }

  public String getName(){
    return name;
  }

  public String getLink(){
    return link;
  }

  // public int getY(){
  //   return spriteY;
  // }
  //
  // public int getX(){
  //   return spriteX;
  // }

}
