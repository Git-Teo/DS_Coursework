class HashFunction {
  //Hash function values
  private int a = 13;
  private int b = 1207;
  private int p = 40487;

  //size of hash table
  private int m = 0;

  public HashFunction(int n) {
    m = n;
  }

  public int getHashValue(int k) {
    return ((a*k + b) % p ) % m;
  }

  public void newHashFunction() {
    a++;
    a = a % (p-1);
    if (a == 0) {
      a++;
    }
    b+=177;
    b = b % (p-1);
  }

  public void printParams(String io) {
    System.out.println("MODIFIED "+io+" HASH FUNCTION PARAMETERS: a="+a+"; b="+b+"; p="+p);
  }
}
