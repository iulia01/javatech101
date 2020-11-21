package com.company;

public class Lucky {
    static int x = 0;
    static int count = 0;

    static class LuckyThread implements Runnable {
        CommonResource res;

        LuckyThread(CommonResource res) {
            this.res = res;
        }

        @Override
        public void run() {
            res.increment();
        }
    }

    static class CommonResource {
        synchronized void increment() {
            while (x < 999999) {
                x++;
                if ((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000)
                        % 10 + (x / 10000) % 10 + (x / 100000) % 10) {
                    System.out.println(x);
                    count++;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CommonResource commonResource = new CommonResource();
        Thread t1 = new Thread(new LuckyThread(commonResource));
        Thread t2 = new Thread(new LuckyThread(commonResource));
        Thread t3 = new Thread(new LuckyThread(commonResource));
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count);
    }
}
