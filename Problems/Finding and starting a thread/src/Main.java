class ThreadProcessor {
    public static void findAndStartThread(Thread... threads) throws InterruptedException {
        // implement this method
        Thread foundThread = null;
        for (Thread a : threads) {
            if (a.getState() == Thread.State.NEW) {
                foundThread = a;
                break;
            }
        }
        foundThread.start();
        foundThread.join();
    }
}