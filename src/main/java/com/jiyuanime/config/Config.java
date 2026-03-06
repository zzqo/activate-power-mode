package com.jiyuanime.config;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * 配置文件
 * Created by KADO on 15/12/17.
 */
@State(name = "activate-power-mode", storages = {@Storage("activate-power-mode.xml")})
public class Config implements PersistentStateComponent<Config.State> {

    @Nullable
    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull State state) {
        XmlSerializerUtil.copyBean(state, this.state);
    }

    public State state = new State();

    public Config() {

        defaultInitState();

    }

    public void defaultInitState() {

        state.isEnable = true;

        state.isSpark = true;

        state.isShake = true;

        state.isCombo = true;

        state.isColorful = false;

        state.particleMaxCount = 5;

        state.particleColor = null;
    }

    public static Config getInstance() {
        return ApplicationManager.getApplication().getService(Config.class);
    }

    public static final class State {

        /**
         * 是否开启
         */
        public boolean isEnable = true;

        /**
         * 是否震动
         */
        public boolean isShake = false;

        /**
         * 是否显示火花
         */
        public boolean isSpark = true;

        /**
         * 色彩鲜艳的配置项
         */
        public boolean isColorful = true;

        /**
         * 开启效果的界限
         */
        public int openFunctionBorder = 30;

        /**
         * 敲击的时间间隔
         */
        public long clickTimeInterval = 7777;

        /**
         * 敲击的最大连击数
         */
        public int maxClickCombo;

        /**
         * 是否开启 Combo
         */
        public boolean isCombo = false;

        /**
         * 每次生成的粒子量
         */
        public int particleMaxCount = 5;

        /**
         * 粒子颜色,为null则代表auto
         */
        public Color particleColor = null;

    }


}
