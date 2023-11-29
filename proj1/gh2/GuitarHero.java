package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static int keyLength = keyboard.length();

    public static void main(String[] args) {
        GuitarString[] gs = new GuitarString[keyLength];

        for (int i = 0; i < keyLength; i += 1) {
            double freq = 440 * Math.pow(2.0, (i - 24) / 12.0);
            gs[i] = new GuitarString(freq);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIdx = keyboard.indexOf(key);
                if (keyIdx >= 0) {
                    gs[keyIdx].pluck();
                }

                double sample = 0.0;
                for (GuitarString s : gs) {
                    sample += s.sample();
                }
                StdAudio.play(sample);

                for (GuitarString s : gs) {
                    s.tic();
                }
            }
        }
    }
}
