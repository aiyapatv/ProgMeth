package Utils;

public class FrameRate {
    private int frame;

    public FrameRate(int delay , int num){
        frame = 1;
        loopFrame(delay , num);
    }

    public int getFrame() {
        return frame;
    }
    public void loopFrame(int delay , int num) {
        Thread thread = new Thread(() -> {
            while (true) {
                frame = (frame == num) ? 1 : frame + 1;
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
