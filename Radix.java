import java.util.Arrays;
public class Radix{

  public static int nth(int n, int col){
    return (Math.abs(n) % (int) Math.pow(10, col + 1)) / (int) Math.pow(10, col);
  }

  public static int length(int n){
    if (n == 0) return 1;
    return (int) Math.log10(Math.abs(n)) + 1;
  }

  public static void merge(SortableLinkedList original, SortableLinkedList[] buckets){
    for (int i = 0; i < buckets.length; i++){
      original.extend(buckets[i]);
    }
  }

  private static SortableLinkedList[] makeArray(){
    SortableLinkedList[] a =  new SortableLinkedList[10];
    for (int i = 0; i < 10; i++){
      a[i] = new SortableLinkedList();
    }
    return a;
  }

  public static void radixSortSimple(SortableLinkedList data){
    SortableLinkedList[] buckets = makeArray();
    int longest = 0;
    while (data.size() > 0){
      if (length(data.get(0)) > longest) longest = length(data.get(0));
      buckets[nth(data.get(0), 0)].add(data.get(0));
      data.remove(0);
    }
    merge(data, buckets);
    for (int start = 1; start < longest; start++){
      buckets = makeArray();
      while (data.size() > 0){
        buckets[nth(data.get(0), start)].add(data.get(0));
        data.remove(0);
      }
      merge(data, buckets);
    }
  }

  public static void radixSort(SortableLinkedList data){
    SortableLinkedList[] buckets = makeArray();
    SortableLinkedList[] negativeBuckets = makeArray();
    int longest = 0;
    while (data.size() > 0){
      if (length(data.get(0)) > longest) longest = length(data.get(0));
      if (data.get(0) < 0) negativeBuckets[9 - nth(data.get(0), 0)].add(data.get(0));
      else buckets[nth(data.get(0), 0)].add(data.get(0));
      data.remove(0);
    }
    merge(data, negativeBuckets);
    merge(data, buckets);
    for (int start = 1; start < longest; start++){
      buckets = makeArray();
      negativeBuckets = makeArray();
      while (data.size() > 0){
        if (data.get(0) < 0) negativeBuckets[9 - nth(data.get(0), start)].add(data.get(0));
        else buckets[nth(data.get(0), start)].add(data.get(0));
        data.remove(0);
      }
      merge(data, negativeBuckets);
      merge(data, buckets);
    }
  }

}
