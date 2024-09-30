package org.team6.Database;

public class TestDatabase {
     // enable this to make pretty printing a bit more compact
   private static final boolean COMPACT_OBJECTS = false;

   // This class creates a portal connection and runs a few operation

   public static void main(String[] args) {
    try{
        DatabaseConnection c = new DatabaseConnection();
        // TO RESET THE DATABASE
        // Write your tests here. Add/remove calls to pause() as desired. 
        // Use println instead of prettyPrint to get more compact output (if your raw JSON is already readable)

        /* 
        try{
            DatabaseConnection c = new DatabaseConnection();
            c.registerProduct("test");
            c.registerProduct("test2");
            c.registerProduct("test3");
            c.registerProduct("test4");
            c.registerProduct("ikea lampa");
            c.registerProduct("ikea lampa2");

            String products = c.getProducts();
            System.out.println("Products in database: " + products);
            
            // To reset the database run the method once
            //c.resetDatabase();

            c.closeConnection();
    
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR!\nYou do not have the H2 JDBC driver (e.g. h2-2.3.232.jar) in your runtime classpath!");
        } catch (Exception e) {
            e.printStackTrace();
        }   
            */   

        pause();

        // Close the connection
        c.closeConnection();

    } catch (ClassNotFoundException e) {
        System.err.println("ERROR!\nYou do not have the H2 JDBC driver (e.g. h2-2.3.232.jar) in your runtime classpath!");
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
   
   public static void pause() throws Exception{
     System.out.println("PRESS ENTER");
     while(System.in.read() != '\n');
   }
   
   // This is a truly horrible and bug-riddled hack for printing JSON. 
   // It is used only to avoid relying on additional libraries.
   // If you are a student, please avert your eyes.
   public static void prettyPrint(String json){
      System.out.print("Raw JSON:");
      System.out.println(json);
      System.out.println("Pretty-printed (possibly broken):");
      
      int indent = 0;
      json = json.replaceAll("\\r?\\n", " ");
      json = json.replaceAll(" +", " "); // This might change JSON string values :(
      json = json.replaceAll(" *, *", ","); // So can this
      
      for(char c : json.toCharArray()){
        if (c == '}' || c == ']') {
          indent -= 2;
          breakline(indent); // This will break string values with } and ]
        }
        
        System.out.print(c);
        
        if (c == '[' || c == '{') {
          indent += 2;
          breakline(indent);
        } else if (c == ',' && !COMPACT_OBJECTS) 
           breakline(indent);
      }
      
      System.out.println();
   }
   
   public static void breakline(int indent){
     System.out.println();
     for(int i = 0; i < indent; i++)
       System.out.print(" ");
   }   
}