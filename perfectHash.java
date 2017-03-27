import java.util.*;

// Author: c1531722 //
// Program output matches all reference output //
// except for one line for all test inputs //
// might be usage: /usr/local/checkprog.d/reference/CM2303/perfectHash.exe full_key_set key_operations //
// test inputs data and operations loaded from a single file //
// examples can be found at submission "testdata1.txt" contains both data and operations //
// but in theory should work with first data file input followed by operations input file //
class perfectHash {

  //hashtable
  private static HashTable hashtable;

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

    hashtable = new HashTable(m);
    System.out.println("SETTING HASH TABLE SIZE: "+m);
    System.out.print("READ SET OF KEYS: ");

    //Output received keys
    for (int i = 0; i < keys.size(); i++) {
      System.out.print(keys.get(i)+" ");
    }

    System.out.println();

    //Print Hash Function variables
    hashtable.printParams("INITIAL OUTER");
    System.out.print("HASHED TO OUTER HASH TABLE AT: ");

    //Calculate and Store Hash values as key and Keys as values
    //Creating Group Table

    for (int i = 0; i < keys.size(); i++) {
      hv = hashtable.getHashValue(keys.get(i));
      hashtable.addKeyToSlot(hv, keys.get(i));
      System.out.print(hv+" ");
    }

    System.out.println();
    System.out.println();

    //Calculate and print total number of collisions.
    cs = hashtable.totalCombination();
    System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: "+cs);

    //Create and Test new Hash Function everytime the number of total collisions
    //is greater than the number of keys, Calculate Hash Values,
    //Store and Calculate total number of collisions.
    while (m < cs) {
      hftc++;
      moded = true;
      hashtable.newHashFunction();
      hashtable.reset();
      for (int i = 0; i < keys.size(); i++) {
        hv = hashtable.getHashValue(keys.get(i));
        hashtable.addKeyToSlot(hv, keys.get(i));
      }
      cs = hashtable.totalCombination();
      System.out.println("NUMBER OF PAIRS OF COLLISIONS IN OUTER HASH TABLE: "+cs);
    }

    //Print number of Hash Functions tested
    String s = hftc > 1 ? "S" : "";
    System.out.println(hftc+" OUTER HASH FUNCTION"+s+" TESTED");

    //Output new Hash Function and Hash Table settings if modification was required
    if (moded) {
      hashtable.printParams("MODIFIED OUTER");
      System.out.print("MODIFIED HASHED TO OUTER HASH TABLE AT: ");
      //Print Hash values
      for (int i = 0; i < keys.size(); i++) {
        hv = hashtable.getHashValue(keys.get(i));
        System.out.print(hv+" ");
      }
      System.out.println();
    }
    System.out.println();

    System.out.println("KEYS GROUPED ONTO SLOTS:");
    for (int i=0; i< keys.size(); i++) {
      System.out.print("grouping slot "+i+":"+" ");
      values = hashtable.getKeys(i);
      //System.out.println(Arrays.toString(values.toArray()));
      for (int j=0; j< values.size(); j++) {
        System.out.print(values.get(j)+" ");
      }
      System.out.println();
    }
    System.out.println();

    hashtable.generateHashTables();
    //System.out.println(Arrays.toString(operation.toArray()));

    //Perform Operations
    for (int i=0; i < operation.size(); i++) {
      op = operation.get(i);
      opand = operand.get(i);
      switch(op) {
        case "insert":
          loc = hashtable.insert(opand);
          if (loc[0]>=0 && loc[1]>=0){
            System.out.println("HASH KEY TO OUTER SLOT: "+loc[0]+", INNER SLOT: "+loc[1]);
            hashtable.printHashTable();
          } else if (loc[1]>=0) {
            System.out.println("ATTEMPT OPERATION: locate "+opand);
            System.out.println("ERROR: invalid key");
          } else {
            System.out.println("ERROR: cannot insert key = "+opand+"; already in hash table");
          }
          System.out.println();
          break;
        case "locate":
          loc = hashtable.locate(opand);
          if (loc[0]>=0 && loc[1]>=0){
            System.out.println("LOCATED KEY = "+opand+" at: "+loc[0]+", "+loc[1]);
          } else {
            System.out.println("ATTEMPT OPERATION: locate "+opand);
            System.out.println("ERROR: invalid key");
          }
          System.out.println();
          break;
        case "delete":
          loc = hashtable.delete(opand);
          if (loc[0]>=0 && loc[1]>=0){
            System.out.println("LOCATED KEY = "+opand+" at: "+loc[0]+", "+loc[1]);
            hashtable.printHashTable();
          } else if (loc[1]>=0) {
            System.out.println("ATTEMPT OPERATION: delete "+opand);
            System.out.println("ERROR: invalid key");
          } else {
            System.out.println("ERROR: cannot delete key = "+opand+"; not in hash table");
          }
          System.out.println();
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
