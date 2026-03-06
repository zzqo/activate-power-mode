package com.jiyuanime.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.jiyuanime.config.Config;
import com.jiyuanime.i18n.ActivatePowerModeBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Setting implements Configurable {
    private SettingForm form;

    private final Config.State state = Config.getInstance().state;


    @Nls
    @Override
    public String getDisplayName() {
        return ActivatePowerModeBundle.message("settings.title");
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        form = new SettingForm();
        return form.getRootComponent();
    }

    @Override
    public boolean isModified() {
        return form != null && form.isSettingsModified(state);
    }

    @Override
    public void apply() throws ConfigurationException {
        form.apply();
    }

    @Override
    public void disposeUIResources() {
        form = null;
    }
}
