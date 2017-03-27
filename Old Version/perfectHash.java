import java.util.*;

class perfectHash {

  //class to keep track of keys and their calculated hash value
  private static KeyValuesTable hv_keys = new KeyValuesTable();

  //HashTable
  private static OuterHashTable HashTable;

  public static void main (String[] args) {

    /// Variables that will be utilized a multitude of times ///
    // across the execution of the program for example in for loops ///
    //size of outer hash table
    //also number of keys
    int m = 0;
    //key
    int k;
    //hash value
    int hv;
    //collisions
    int cs = -1;
    //hash functions tested count
    int hftc = 1;
    //hash function modified?
    boolean moded = false;
    //operand location
    int[] loc;
    //operation
    String op;
    //operand
    int opand;
    //input reader
    Scanner r = new Scanner(System.in);
    //temporary values holder
    ArrayList<Integer> values;

    //ArrayLists to store input
    ArrayList<Integer> keys = new ArrayList<Integer>();
    ArrayList<String> operation = new ArrayList<String>();
    ArrayList<Integer> operand = new ArrayList<Integer>();


    //Get Number of Keys
    while (m > 1000 || m <= 0) {
      //System.out.println(r.next());
      m = r.nextInt();
    }

    //Get and Store Key inputs
    for (int i = 0; i < m; i++) {
      k = -1;
      while (k < 0) {
        k = r.nextInt();
      }
      keys.add(k);
    }


    //Get and Store Operation-Operand inputs
    while (r.hasNext()) {
      operation.add(r.next());
      operand.add(r.nextInt());
    }

    HashTable = new OuterHashTable(m);
    System.out.println("SETTING HASH TABLE SIZE: "+m);
    System.out.print("READ SET OF KEYS: ");

    //Output received keys
    for (int i = 0; i < keys.size(); i++) {
      System.out.print(keys.get(i)+" ");
    }

    System.out.println();

    //Print Hash Function variables
    HashTable.printParams("OUTER");
    System.out.print("HASHED TO OUTER HASH TABLE AT: ");

    //Calculate and Store Hash values as key and Keys as values
    //Creating Group Table
    for (int i = 0; i < keys.size(); i++) {
      hv = HashTable.getHashValue(keys.get(i));
      hv_keys.addKeyAndValue(hv, keys.get(i));
      System.out.print(hv+" ");
    }

    System.out.println();
    System.out.println();

    //Calculate and print total number of collisions.
    cs = hv_keys.totalCombination();
    System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: "+cs);

    //Create and Test new Hash Function everytime the number of total collisions
    //is greater than the number of keys, Calculate Hash Values,
    //Store and Calculate total number of collisions.
    while (m < cs) {
      hftc++;
      moded = true;
      HashTable.newHashFunction();
      hv_keys.reset();
      for (int i = 0; i < keys.size(); i++) {
        hv = HashTable.getHashValue(keys.get(i));
        hv_keys.addKeyAndValue(hv, keys.get(i));
      }
      cs = hv_keys.totalCombination();
      System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: "+cs);
    }

    //Print number of Hash Functions tested
    String s = hftc > 1 ? "S" : "";
    System.out.println(hftc+" OUTER HASH FUNCTION"+s+" TESTED");

    //Output new Hash Function and Hash Table settings if modification was required
    if (moded) {
      HashTable.printParams("OUTER");
      System.out.println("MODIFIED HASHED TO OUTER HASH TABLE AT: ");
      //Print Hash values
      for (int i = 0; i < keys.size(); i++) {
        hv = HashTable.getHashValue(keys.get(i));
        System.out.print(hv+" ");
      }
      System.out.println();
    }

    System.out.println();
    System.out.println("KEYS GROUPED ONTO SLOTS:");
    for (int i=0; i< keys.size(); i++) {
      System.out.print("grouping slot "+i+":"+" ");
      values = hv_keys.getValues(i);
      //System.out.println(Arrays.toString(values.toArray()));
      for (int j=0; j< values.size(); j++) {
        System.out.print(values.get(j)+" ");
      }
      System.out.println();
    }

    HashTable.generateHashTable(hv_keys);
    System.out.println(Arrays.toString(operation.toArray()));

    //Perform Operations
    for (int i=0; i < operation.size(); i++) {
      op = operation.get(i);
      opand = operand.get(i);
      switch(op) {
        case "insert":
          loc = HashTable.insert(opand);
          System.out.println("HASH KEY TO OUTER SLOT: "+loc[0]+", INNER SLOT: "+loc[1]);
          HashTable.printTable();
          break;
        case "locate":
          loc = HashTable.locate(opand);
          System.out.println("LOCATED KEY = "+opand+" at: "+loc[0]+", "+loc[1]);
          HashTable.printTable();
          break;
        case "delete":
          loc = HashTable.delete(opand);
          System.out.println("LOCATED KEY = "+opand+" at: "+loc[0]+", "+loc[1]);
          HashTable.printTable();
          break;
        default:
          System.out.println("COULD NOT RECOGNISE OPERATION");
      }
    }


  }
}



//Test Code
//System.out.println(Arrays.toString(operation.toArray()));
//System.out.println(Arrays.toString(operand.toArray()));
