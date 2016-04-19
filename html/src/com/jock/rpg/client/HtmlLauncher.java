package com.jock.rpg.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.jock.rpg.SuperRPG;

public class HtmlLauncher extends GwtApplication {
        // To compile:
        // terminal -> cd SuperRPG
        // ./gradlew html:superDev
        // or
        // gradlew html:superDev
        // -> run http://localhost:8080/html/ on browser

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new SuperRPG();
        }
}