package engine.utils;

public final class EngineUtils {

    private EngineUtils() { }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
