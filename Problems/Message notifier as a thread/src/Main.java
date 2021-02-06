class MessageNotifier extends Thread {

    final String msg;
    final int repeats;

    public MessageNotifier(String msg, int repeats) {
        this.msg = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 1; i <= repeats; i++) {
            System.out.println(msg);
        }
    }
}