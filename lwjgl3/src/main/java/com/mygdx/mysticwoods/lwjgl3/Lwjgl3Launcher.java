package com.mygdx.mysticwoods.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.mysticwoods.MysticWoods;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        createApplication();
    }

    private static void createApplication() {

        // magick player.png -crop 48x48 player/frame_%02d.png
        TexturePacker.process("./raw_assets/graphics", "assets/graphics", "mystic-woods");

        final TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.paddingX = 0;
        settings.paddingY = 0;
        settings.edgePadding = false;
        settings.duplicatePadding = false;
        settings.bleed = false;

        // npx tile-extruder --tileWidth 16 --tileHeight 16 --extrusion 6 --input objects.png --output objects_ext.png
        // for tiled, the margin will be the extrusion value, and the spacing will be double the extrusion value
        TexturePacker.process(settings, "./raw_assets/map", "assets/graphics/map", "map");

        new Lwjgl3Application(new MysticWoods(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Mystic Woods");
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        configuration.setWindowedMode(640, 480);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
