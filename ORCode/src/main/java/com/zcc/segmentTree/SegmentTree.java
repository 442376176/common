package com.zcc.segmentTree;

/**
 * @author zcc
 * @version 1.0
 * @date 2021/12/15 17:25
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for(int i = 0 ; i < arr.length ;i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        //在treeIndex的位置创建表示区间从[l...r]的线段树
        buildSegmentTree(0,0,data.length - 1);
    }

    /**
     * 构建树
     *
     * @param treeIndex 创建线段树所对应的根节点的索引
     * @param l 区间的左端点
     * @param r 区间的右端点
     */
    private void buildSegmentTree(int treeIndex,int l,int r) {
        if(l == r){
            tree[treeIndex] = data[l];
            return ;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l ) / 2 ;
        buildSegmentTree(leftTreeIndex , l , mid);
        buildSegmentTree(rightTreeIndex , mid + 1 , r);

        //综合两个子节点的信息得到父节点的信息,如求和操作
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 ||index >= data.length){
            throw new IllegalArgumentException("参数错误");
        }
        return data[index];
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    /**
     * @param queryL 查询左边界
     * @param queryR 查询右边界
     * @return
     */
    public E query(int queryL,int queryR){
        //确定边界
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("边界值异常");
        return query(0,0,data.length - 1 ,queryL ,queryR);
    }

    //在以根节点为treeIndex的线段树中[l...r]的范围里,搜索区间[queryL...queryR]的值
    private E query(int treeIndex , int l , int r , int queryL ,int queryR) {
        if(l == queryL && r == queryR)
            return tree[treeIndex];
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l ) / 2 ;
        //忽略左部分
        if(queryL >= mid + 1)
            return query(rightTreeIndex,mid + 1 , r,queryL,queryR);
        //忽略右部分
        if(queryR <= mid)
            return query(leftTreeIndex,l,mid,queryL,queryR);
        //并没有完全落在左节点或者右节点中,一部分落在左边,一部分落在右边
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult,rightResult);
    }
}