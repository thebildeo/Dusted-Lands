package dusted.entities.units;

import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import dusted.ai.types.*;
import dusted.content.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.graphics.MultiPacker.*;
import mindustry.type.*;

public class DustedUnitType extends UnitType {
    public float quakeDamage = 60f;
    public float quakeSpacing = 20f;
    public int quakeSteps = 1;
    public int quakes = 1;
    public float quakeRadius = 8f;
    public float quakeDelay = 10f;
    public float quakeCooldown = 40f;
    public Effect quakeEffect = Fx.explosion;
    public Sound quakeSound = Sounds.explosion;
    public DustedUnitCategory unitCategory = DustedUnitCategory.def;

    public Effect bounceEffect = DustedFx.smallBounce;
    public float bounceDistance = 120f;
    public float bounceCooldown = 30f;
    public float bounceDelay = 15f;
    public float bounceDamage = 10f;
    public int bounces = 1;
    public Sound bounceSound = Sounds.shotgun;
    public float minBouncePitch = 0.9f, maxBouncePitch = 1.1f;

    public DustedUnitType(String name) {
        super(name);
    }

    @Override
    public void init() {
        switch (unitCategory) {
            case quake:
                defaultController = QuakeAI::new;
                constructor = QuakeUnitEntity::new;
                break;
            case bounce:
                defaultController = BounceAI::new;
                constructor = BouncingUnitEntity::new;
                break;
        }
        super.init();
    }

    @Override
    public void createIcons(MultiPacker packer) {
        super.createIcons(packer);
        PixmapRegion base = new PixmapRegion(packer.get(name + "-outline").crop());

        //TODO broken
        //Pixmap cell = Core.atlas.getPixmap(name + "-cell").pixmap.copy();
        //cell.replace(in -> in == 0xffffffff ? 0xffa664ff : in == 0xdcc6c6ff || in == 0xdcc5c5ff ? 0xd06b53ff : 0);
        //base.pixmap.draw(cell, true);

        for (Weapon weapon : weapons) {
            if (!weapon.name.isEmpty()) {
                Pixmap over = base.crop();
                Pixmap weaponRegion = packer.get(weapon.name).crop();
                Pixmap weaponOutlineRegion = packer.get(weapon.name + "-outline").crop();
                base.pixmap.draw(weaponOutlineRegion,
                        (int) (weapon.x * 4 + base.width / 2f - weaponOutlineRegion.width / 2f),
                        (int) (-weapon.y * 4 + base.height / 2f - weaponOutlineRegion.height / 2f),
                        true);

                if (!weapon.top) {
                    base.pixmap.draw(over, true);
                    base.pixmap.draw(weaponRegion,
                            (int) (weapon.x * 4 + base.width / 2f - weaponRegion.width / 2f),
                            (int) (-weapon.y * 4 + base.height / 2f - weaponRegion.height / 2f),
                            true);
                }

                if (weapon.mirror) {
                    Pixmap overFlip = base.crop();
                    Pixmap flipRegion = weaponRegion.flipX();
                    Pixmap flipOutlineRegion = weaponOutlineRegion.flipX();
                    base.pixmap.draw(flipOutlineRegion,
                            (int) (-weapon.x * 4 + base.width / 2f - weaponOutlineRegion.width / 2f),
                            (int) (-weapon.y * 4 + base.height / 2f - weaponOutlineRegion.height / 2f),
                            true);
                    if (!weapon.top) {
                        base.pixmap.draw(overFlip, true);
                        base.pixmap.draw(flipRegion,
                                (int) (-weapon.x * 4 + base.width / 2f - weaponRegion.width / 2f),
                                (int) (-weapon.y * 4 + base.height / 2f - weaponRegion.height / 2f),
                                true);
                    }
                }
            }
        }

        packer.add(PageType.main, name + "-full", base);
    }

    public enum DustedUnitCategory {
        def,
        quake,
        bounce
    }
}
