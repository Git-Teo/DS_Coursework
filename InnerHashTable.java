import java.util.*;

class InnerHashTable {

  private int[] data;
  private int m;
  private HashFunction Hash;

  public InnerHashTable(int n) {
    Hash = new HashFunction(n);
    data = new int[n];
    m = n;
    for (int i = 0; i < n; i++) {
      data[i] = -1;
    }
  }

  public int[] getData() {
    return data;
  }

  public int getSize() {
    return m;
  }

  public void printKeys() {
    for (int i = 0; i < data.length; i++) {
      System.out.print(data[i]+" ");
    }
  }

  public boolean hasCollisions(ArrayList<Integer> keys) {
    ArrayList<Integer> hv = new ArrayList<Integer>();
    int v = 0;
    if (m > 1) {
      for (int i=0; i<keys.size(); i++) {
        v = Hash.getHashValue(keys.get(i));
        if(hv.contains(v)) {
          return true;
        }
        hv.add(v);
      }
    }
    return false;
  }

  public int insert(int n) {
    int ip = Hash.getHashValue(n);
    if (data[ip] == n) {
      return -1;
    }
    data[ip] = n;
    return ip;
  }

  public int locate(int n) {
    int ip = Hash.getHashValue(n);
    if (data[ip] == n) {
      return ip;
    }
    return -1;
  }

  public int delete(int n) {
    int ip = Hash.getHashValue(n);
    if (data[ip] == n) {
      data[ip] = -1;
      return ip;
    }
    return -1;
  }


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
