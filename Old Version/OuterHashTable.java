class OuterHashTable {
  //Size of Hash Table
  private int m;
  //outer and inner pointers
  private int op;
  private int ip;
  private InnerHashTable[] HashTables;
  private HashFunction Hash;

  public OuterHashTable(int n) {
    HashTables = new InnerHashTable[n];
    Hash = new HashFunction(n);
    m = n;
    for (int i = 0; i < n; i++) {
      HashTables[i] = new InnerHashTable(1);
    }
  }

  public void generateHashTable(KeyValuesTable kvt) {
    InnerHashTable iht;
    for (int i = 0; i < kvt.getKeys().size(); i++) {
      m = kvt.getValueCount(kvt.getKeys().get(i));
      iht = new InnerHashTable(m);
      if(iht.hasCollisions()) {
        iht.newHashFunction();
      }
      HashTables[kvt.getKeys().get(i)] = iht;
    }
  }

  public void printTable() {
    int[] ks;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print("operation slot "+i+":"+" ");
        if (i == keys[j]) {
          HashTables[j].printKeys();
          System.out.println();
        }
      }
    }
  }

  //Hash Table Operations
  public int[] insert(int n) {
    op = Hash.getHashValue(n);
    ip = HashTables[op].insert(n);
    return new int[]{op, ip};
  }



  public int[] locate(int n) {
    op = Hash.getHashValue(n);

    return new int[]{1,2};
  }

  public int[] delete(int n) {
    return new int[]{1,2};
  }

  //Hash Function functions
  public int getHashValue(int n) {
    return Hash.getHashValue(n);
  }

  public void newHashFunction() {
    Hash.newHashFunction();
  }

  public void printParams(String io) {
    Hash.printParams(io);
  }
}
