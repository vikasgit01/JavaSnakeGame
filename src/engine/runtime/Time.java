package engine.runtime;

public final class Time {

    public static float deltaTime = 0f;
    public static float time = 0f;

    private static long lastFrameTime = System.nanoTime();

    private Time() { }

    public static void update() {
        long currentTime = System.nanoTime();

        deltaTime = (currentTime - lastFrameTime) / 1_000_000_000f;
        lastFrameTime = currentTime;
        time += deltaTime;
    }
}
