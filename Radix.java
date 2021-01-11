public class Radix{

  public static int nth(int n, int col){
    return (Math.abs(n) % (int) Math.pow(10, col + 1)) / (int) Math.pow(10, col);
  }

  public static int length(int n){
    if (n == 0) return 1;
    return (int) Math.log10(Math.abs(n)) + 1;
  }

  public static void merge(MyLinkedList original, MyLinkedList[] buckets){
    for (int i = 0; i < buckets.length; i++){
      original.extend(buckets[i]);
    }
  }

}
