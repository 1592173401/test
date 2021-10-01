package com.bit;
import java.util.Arrays;

/**
 * Java语言的栈中中数组为引用类型，数组可以作为返回值类型！
 * 数组元素存放在堆中，而不是像C
 * 数组首地址存放在JVM栈中
 * 栈的地址是拿不到的，堆的地址可以打印但不是真实的（是被处理过的，但是也唯一）
 */
public class Array {
    //打印数组,但是可以用自带的方法
    public static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
    }


    //Java返回值类型可以是数组
    public static int[] add(int[] arr) {
        int tmp[] = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            tmp[i] = arr[i] * 2;
        }
        return tmp;
    }


    //自己写的方法：把数组转化为字符串。相当于Arrays.toString().
    public static String myTOString(int[] arr) {
        String ret = "[";
        for (int i = 0; i < arr.length; ++i) {
            ret += arr[i];
            if (i < arr.length - 1)
                ret += ",";
        }
        ret += "]";
        return ret;
    }


    public static void main1(String[] args) {
        int[] arr1 = {1, 2, 3};//其中arr1在JVM的栈中，存放着数据{1,2,3}中首个元素的地址。
        //数据元素则存放在堆中。
        int[] arr2 = null;    //和C语言不一样,null就是null，是一个对象。不是零地址

        int[] arr3 = new int[]{1, 2, 3};
        System.out.println(myTOString(arr1));//将数组以字符串的方式打印出来
        //System.out.println(arr2.length);
        //foreach()用法：foreach(元素类型 变量名称:数组名)
        /*for (int value:
             arr3) {
            System.out.print(value+" ");*/
        //printArr(arr1);
        int[] arr = add(arr3);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * Arrays工具类中的常用方法介绍：
     * toString()    binarySearch()   copyOf()   sort()....
     *
     */


    /**
     * 数组的拷贝：(这四种本质上都是浅拷贝)
     * 1.for()循环
     * 2.Arrays.copyOf(源数组名，拷贝的长度)
     * 3.System.arraycopy(源数组名，从源拷贝的起始位置，目标数组名，拷贝到目标数组的起始位置，新的长度)
     * 4.clone()  产生一个副本
     */


    public static void main2(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};

        //使用Arrays.copyOf()
        int[] ret1 = Arrays.copyOf(arr1, arr1.length);
        System.out.println(Arrays.toString(ret1));


        //使用System.arraycopy()，更快！
        int[] ret2 = new int[arr1.length];
        System.arraycopy(arr1, 1, ret2, 2, arr1.length - 2);
        System.out.println(Arrays.toString(ret2));


        //使用clone（）
        int[] ret3 = arr1.clone();
        System.out.println(Arrays.toString(ret3));


    }


    /**
     * 二分查找  时间复杂度O(logn)
     * Arrays.binarySearch()是库函数自带的二分查找
     */
    public static int binarySearch(int[] arr, int n) {
        int l = 0, r = arr.length - 1;
        int mid = r >> 1;
        while (l <= r) {
            if (arr[mid] > n)
                r = mid - 1;
            else if (arr[mid] < n)
                l = mid + 1;
            else
                return mid;
            mid = (l + r) >> 1;
        }
        return -1;
    }

    public static void main3(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        int n = binarySearch(a, 1);
        System.out.println(n);
    }


    /**
     * 冒泡排序   时间复杂度（最好情况为O(N),最坏情况为O（N^2））
     */
    public static void paopaoSort(int[] a) {
        for (int i = 0; i < a.length-1 ; ++i) {
            //每轮交换完成后都判断一下数组是否已经有序
            boolean flag = false;     //交换前为false

            for (int j = 0; j < a.length - 1 - i; ++j) {
                int tmp = 0;
                if (a[j] > a[j + 1]) {
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    flag = true;      //交换后为true
                }
            }
            if (flag == false)
                break;
        }
    }

    public static void main4(String[] args) {
        int[] a = {1};
        paopaoSort(a);
        System.out.println(Arrays.toString(a));
    }


    /**
     * 奇偶互换，奇数在前，偶数在后
     */
    public static void swap(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            //左边遇到奇数，往右走，遇到偶数停下来
            while (a[l] % 2 == 1 && l < r) {
                l++;
            }
            //右边遇到偶数往左走，遇到奇数停下来
            while (a[r] % 2 == 0 && l < r) {
                r--;

            }
            //交换奇偶数位置
            if (l < r) {
                int tmp = 0;
                tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
            }
        }
    }

    public static void main5(String[] args) {
        int[] a = {0, 1, 3, 4, 6, 8, 7};
        swap(a);
        System.out.println(Arrays.toString(a));


    }


    /**
     * 二维数组的打印方式
     * @param args
     */
    public static void main6(String[] args) {

        int[][] a1 = new int[][]{{1, 2, 3}, {4, 5, 6}};
        //for循环打印
        for (int i = 0; i < a1.length; ++i) {
            for (int j = 0; j < a1[i].length; ++j) {
                System.out.print(a1[i][j]+" ");
            }
            System.out.println();
        }

        //foreach循环打印
        for (int [] val:a1 ) {
            for (int val2:val) {
                System.out.print(val2+" ");
            }
            System.out.println();
        }

        //deepTOString()打印
        System.out.println(Arrays.deepToString(a1));


    }


    /**
     * 不规则的二维数组
     */


    public static void main7(String[] args) {
        int [][]arr=new int[2][];       //可以只写行，列可以推导出来
        arr[0]=new int[2];
        arr[1]=new int[3];
        System.out.println(Arrays.deepToString(arr));

    }
}
