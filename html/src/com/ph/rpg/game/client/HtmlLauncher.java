package com.ph.rpg.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.ph.rpg.game.Game;

public class HtmlLauncher extends GwtApplication {
        // To compile:
        // terminal -> cd SuperRPG
        // ./gradlew html:superDEV
        // or
        // gradlew html:superDEV
        // -> run http://localhost:8080/html/ on browser

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Game();
        }
}