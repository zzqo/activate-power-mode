package com.jiyuanime.ui;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.Comparing;
import com.intellij.ui.ColorPanel;
import com.jiyuanime.config.Config;
import com.jiyuanime.i18n.ActivatePowerModeBundle;

import javax.swing.*;

public class SettingForm {
    private JTextField particleMaxCountTextField;
    private ColorPanel colorChooser;
    private JPanel rootPanel;
    private JCheckBox colorAutoCheckBox;
    private JLabel particleColorLabel;
    private JLabel particleMaxCountLabel;

    private final Config.State state = Config.getInstance().state;

    public SettingForm() {
        initLocalization();
        initListener();
        initSetting();
    }

    private void initLocalization() {
        particleMaxCountLabel.setText(ActivatePowerModeBundle.message("settings.particle.max.count"));
        particleColorLabel.setText(ActivatePowerModeBundle.message("settings.particle.color"));
        colorAutoCheckBox.setText(ActivatePowerModeBundle.message("settings.particle.color.auto"));
    }

    private void initListener() {
        colorAutoCheckBox.addItemListener(e -> {
            JCheckBox item = (JCheckBox) e.getItem();

            colorChooser.setSelectedColor(null);
            colorChooser.setEditable(!item.isSelected());
        });
    }

    private void initSetting() {
        particleMaxCountTextField.setText(String.valueOf(state.particleMaxCount));
        if (state.particleColor == null) {
            colorAutoCheckBox.setSelected(true);
        } else {
            colorChooser.setSelectedColor(state.particleColor);
        }
    }

    public JComponent getRootComponent() {
        return rootPanel;
    }

    public boolean isSettingsModified(Config.State state) {
        try {
            return !Comparing.equal(state.particleMaxCount, Integer.parseInt(particleMaxCountTextField.getText())) ||
                    !Comparing.equal(state.particleColor, colorAutoCheckBox.isSelected() ? null : colorChooser.getSelectedColor());
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public void apply() throws ConfigurationException {
        try {
            int particleMaxCount = Integer.parseInt(particleMaxCountTextField.getText());
            if (particleMaxCount < 0) {
                throw new ConfigurationException(ActivatePowerModeBundle.message("validation.particle.max.count.greater"));
            }
            state.particleMaxCount = particleMaxCount;
        } catch (NumberFormatException e) {
            throw new ConfigurationException(ActivatePowerModeBundle.message("validation.particle.max.count.format"));
        }

        if (!colorAutoCheckBox.isSelected() && colorChooser.getSelectedColor() == null) {
            throw new ConfigurationException(ActivatePowerModeBundle.message("validation.particle.color.required"));
        }

        state.particleColor = colorAutoCheckBox.isSelected() ? null : colorChooser.getSelectedColor();
    }

}
