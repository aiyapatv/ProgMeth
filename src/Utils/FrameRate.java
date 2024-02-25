package Utils;

public class FrameRate {
    private int frame;

    public FrameRate(int delay){
        frame = 1;
        loopFrame(delay);
    }
    public int getFrame() {
        return frame;
    }
    public void loopFrame(int delay) {
        Thread thread = new Thread(() -> {
            while (true) {
                frame = (frame == 1) ? 2 : 1;
                try {
                    Thread.sleep(delay); // Sleep for 1 millisecond
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true); // Set thread as daemon to automatically stop when the program exits
        thread.start();
    }
}
