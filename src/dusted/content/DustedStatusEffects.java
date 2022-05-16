package dusted.content;

import arc.graphics.*;
import dusted.type.*;
import mindustry.content.*;
import mindustry.type.*;

public class DustedStatusEffects {
    public static StatusEffect deteriorating;

    public static void load() {
        //sprite by sh1p :D
        deteriorating = new StatusEffect("deteriorating") {{
            color = Color.valueOf("6d726b");
            damage = 0.56f;
            transitionDamage = 28f;

            init(() -> {
                affinity(StatusEffects.burning, (unit, result, time) -> {
                    unit.damagePierce(transitionDamage);
                });
            });
        }};
    }
}
