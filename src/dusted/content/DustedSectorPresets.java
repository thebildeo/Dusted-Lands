package dusted.content;

import mindustry.type.*;

public class DustedSectorPresets {
    public static SectorPreset outbreak, taintedValley, magmaticPassage;

    public static void load() {
        outbreak = new SectorPreset("outbreak", DustedPlanets.krakai, 269) {{
            alwaysUnlocked = true;
            captureWave = 5;
            difficulty = 1;
        }};

        taintedValley = new SectorPreset("tainted-valley", DustedPlanets.krakai, 37) {{
            captureWave = 10;
            difficulty = 2;
        }};

        magmaticPassage = new SectorPreset("magmatic-passage", DustedPlanets.krakai, 95) {{
            captureWave = 10;
            difficulty = 3;
        }};
    }
}
