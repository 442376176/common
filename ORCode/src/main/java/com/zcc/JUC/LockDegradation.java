package com.zcc.JUC;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ProjectName: ORCode
 * @ClassName: com.zcc.JUC
 * @author: zcc
 * @date: 2022/3/7 10:17
 * @version:
 * @Describe: 锁降级
 */
public class LockDegradation {

    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock(); // 读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock(); // 写锁

        // 锁降级
        // 获取写锁
        writeLock.lock();
        System.out.println("写锁");

        // 获取读锁
        readLock.lock();
        System.out.println("读锁");

        // 释放写锁
        writeLock.unlock();

        // 释放读锁
        readLock.unlock();

    }
}
