package fr.eni.geekoquizz.tools;

public class Random {
    /**
     * Get random number between 0 and max
     * @param max Max range of random number
     * @return The random number
     */
    public static int generate(int max) {
        return generate(0, max);
    }

    /**
     * Get random number between min and max
     * @param min Min of range
     * @param max Max of range
     * @return The random number
     */
    public static int generate(int min, int max) {
        java.util.Random r = new java.util.Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
