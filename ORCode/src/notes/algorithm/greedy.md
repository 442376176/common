#1贪心算法
#2基本定义
顾名思义，贪心算法或贪心思想采用贪心的策略，保证每次操作都是局部最优的，从而使最后得到的结果是全局最优的。

例1：分配问题
有一群孩子和一堆饼干，每个孩子有一个饥饿度，每个饼干都有一个大小，每个孩子只能吃最多一个饼干，且只有饼干的大小大于孩子的饥饿度时，这个孩子才能吃饱。
求解最多有多少孩子可以吃饱？
输入输出样例：
输入 1 2
    1 2 3
输出 2
 题解： 贪心策略：给剩余孩子里最小饥饿度的孩子分配最小的能饱腹的饼干。
 
 ```java
public class FindContentChildren{
    static int solution(int[] children,int[] cookies){
        Arrays.sort(children);
        Arrays.sort(cookies);
        int i = 0;
        int j = 0;
        while(i<children.length && j<cookies.length){
            if (children[i]<cookies[j]){
                i++,j++;
            }else {
                j++;
            }
        }
        return i;
    }
    public static void main(String[] args){
        int[] children = {1,2};
        int[] cookies = {1,2,3};
      System.out.println(solution(children,cookies));
    }
}
```
例2：区间问题
给定多个区间，计算让这些区间互不重叠所需要移除区间的最小个数。起止相连不算重叠。

输入输出样例：
输入是一个数组，数组由多个长度固定为2的数组组成，表示区间的开始和结尾。输出一个整数，表示需要移除的区间数量。
input ：[[1,2],[2,4],[1,3]]
output: 1
可以移除[1,3]区间，使得[1,2]和[2,4]不重叠
 ```java
public class RemoveOverlay{
    static int solution(int[][] arr){
       Arrays.sort(arr,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[a.length-1]-b[b.length-1];
            }
        });
    int count = 0;
    int temp = arr[0][1];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i][0]<temp){
            count++;
            continue;
        }
        temp = arr[i][1];
    }
    return count;
    }
    public static void main(String[] args){
      
        int[][] cookies = {{1,2},{2,4},{1,3}};
      System.out.println(solution(children,cookies));
    }
}
```
例3：买卖股票的最佳时机
设计一个算法计算你能获取的最大利润。你可以尽可能的完成更多的交易（多次买卖一只股票）
注意：你不能同时参与多笔交易
输入输出样例：
input: [7,1,5,3,6,4]
output：7
解释：4：在第二天买入，第三天卖出；
      3：第四天买入，第五天卖出。
题解：今天买 明天赚

 ```java
public class RemoveOverlay{
    static int solution(int[] arr){
      int profit = 0;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]<arr[i+1]){       
            profit += arr[i+1]-arr[i];
        }
        return profit;
    }
}
    public static void main(String[] args){
      int[] price = {7,1,5,3,6,4};
      System.out.println(solution(price));
    }
}
```