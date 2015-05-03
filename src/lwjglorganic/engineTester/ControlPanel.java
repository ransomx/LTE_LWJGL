/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjglorganic.engineTester;

import Entities.Entity;
import Entities.EntityObject;
import Entities.EntityObserver;
import Entities.Light;
import Entities.LightSource;
import java.util.Observable;
import java.util.Observer;
import shaders.ShaderVariables;

/**
 *
 * @author Dominik
 */
public class ControlPanel extends javax.swing.JPanel implements Observer {
    
    private static final long serialVersionUID = 1L;
    private Entity currentSelectedEntity;
    
    private void setType() {
        String toChange = "Nevybrán";
        if (currentSelectedEntity != null) {
            if (currentSelectedEntity instanceof LightSource) {
                toChange = "Světlo";
            } else if (currentSelectedEntity instanceof EntityObject) {
                toChange = "Objekt";
            }
        }
        itemType.setText(toChange);
    }
    
    private void setPosition() {
        String toChange = "";
        if (currentSelectedEntity != null) {
            toChange = "" + (int) currentSelectedEntity.getPosition().x
                    + ", " + (int) currentSelectedEntity.getPosition().y
                    + ", " + (int) currentSelectedEntity.getPosition().z;
        }
        itemPosition.setText(toChange);
    }

    // MATERIAL VARIABLE HANDLERS
    private void setEntityReflection() {
        float value = 0.0f;
        if (currentSelectedEntity != null) {
            value = currentSelectedEntity.getModel().getTexture().getReflectivity();
        }
        this.glosSlider.setValue((int) (value * (float) 100));
    }
    
    private void setEntityDampening() {
        float value = 0.0f;
        if (currentSelectedEntity != null) {
            value = currentSelectedEntity.getModel().getTexture().getShineDamper();
        }
        this.dimSlider.setValue((int) (value * (float) 100));
    }

    // COLOR HANDLERS
    private void setColorR(Light ls) {
        int value = 0;
        value = (int) ls.getColour().x;
        redSlider.setValue(value);
    }
    
    private void setColorB(Light ls) {
        int value = 0;
        value = (int) ls.getColour().z;
        blueSlider.setValue(value);
    }
    
    private void setColorG(Light ls) {
        int value = 0;
        value = (int) ls.getColour().y;
        greenSlider.setValue(value);
    }
    
    private void setMagnitude(Light ls) {
        colorMagnitude.setValue(256);
    }
    
    private void manageLights(Entity e) {
        LightSource ls = (LightSource) e;
        Light l = ls.getLight();
        setColorR(l);
        setColorG(l);
        setColorB(l);
        setMagnitude(l);
    }

    // PANEL VISIBILITY HANDLERS
    private void manageComponentVisibility() {
        manageObjectPanelVisibility();
        manageColorPanelVisibility();
    }
    
    private void manageObjectPanelVisibility() {
        if (currentSelectedEntity != null) {
            objectPanel.setVisible(true);
        } else {
            objectPanel.setVisible(false);
        }
    }
    
    private void manageColorPanelVisibility() {
        if (currentSelectedEntity instanceof LightSource) {
            colorPanel.setVisible(true);
        } else {
            colorPanel.setVisible(false);
        }
    }
    
    private void initDefaultShaderVariables() {
        ambSlider.setValue((int) (ShaderVariables.ambientModifier * 100));
        diffSlider.setValue((int) (ShaderVariables.diffuseModifier * 100));
        speSlider.setValue((int) (ShaderVariables.specularModifier * 100));
    }
    
    public ControlPanel() {
        initComponents();
        manageComponentVisibility();
        initDefaultShaderVariables();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        lightPanel = new javax.swing.JPanel();
        ambSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        diffSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        speSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        ambVal = new javax.swing.JLabel();
        diffVal = new javax.swing.JLabel();
        speVal = new javax.swing.JLabel();
        ambSwitch = new javax.swing.JCheckBox();
        diffSwitch = new javax.swing.JCheckBox();
        speSwitch = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        mode = new javax.swing.JLabel();
        objectPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        itemType = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        itemPosition = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dimSlider = new javax.swing.JSlider();
        glosSlider = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        dimVal = new javax.swing.JLabel();
        refVal = new javax.swing.JLabel();
        rotX = new javax.swing.JCheckBox();
        rotY = new javax.swing.JCheckBox();
        rotZ = new javax.swing.JCheckBox();
        rotationValue = new javax.swing.JSpinner();
        colorPanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        redSlider = new javax.swing.JSlider();
        jLabel13 = new javax.swing.JLabel();
        greenSlider = new javax.swing.JSlider();
        jLabel14 = new javax.swing.JLabel();
        blueSlider = new javax.swing.JSlider();
        jLabel15 = new javax.swing.JLabel();
        redVal = new javax.swing.JLabel();
        blueVal = new javax.swing.JLabel();
        greenVal = new javax.swing.JLabel();
        colorMagnitude = new javax.swing.JSlider();
        distVal = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 200));

        ambSlider.setMaximum(200);
        ambSlider.setPaintLabels(true);
        ambSlider.setName(""); // NOI18N
        ambSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ambSliderStateChanged(evt);
            }
        });
        ambSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ambSliderPropertyChange(evt);
            }
        });

        jLabel1.setText("Ambient složka");

        diffSlider.setMaximum(200);
        diffSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                diffSliderStateChanged(evt);
            }
        });

        jLabel2.setText("Difúzní složka");

        speSlider.setMaximum(200);
        speSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speSliderStateChanged(evt);
            }
        });

        jLabel3.setText("Zrcadlová složka (globální)");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ambSlider, org.jdesktop.beansbinding.ELProperty.create("${value/100}"), ambVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, speSlider, org.jdesktop.beansbinding.ELProperty.create("${value/100}"), diffVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, diffSlider, org.jdesktop.beansbinding.ELProperty.create("${value/100}"), speVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        ambSwitch.setSelected(true);
        ambSwitch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ambSwitchItemStateChanged(evt);
            }
        });

        diffSwitch.setSelected(true);
        diffSwitch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                diffSwitchItemStateChanged(evt);
            }
        });

        speSwitch.setSelected(true);
        speSwitch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                speSwitchItemStateChanged(evt);
            }
        });

        jLabel7.setText("Mód: ");

        javax.swing.GroupLayout lightPanelLayout = new javax.swing.GroupLayout(lightPanel);
        lightPanel.setLayout(lightPanelLayout);
        lightPanelLayout.setHorizontalGroup(
            lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lightPanelLayout.createSequentialGroup()
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lightPanelLayout.createSequentialGroup()
                        .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(speSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(diffSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lightPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ambSwitch))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lightPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diffSwitch))
                            .addComponent(ambSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ambVal)
                            .addComponent(diffVal)
                            .addComponent(speVal)))
                    .addGroup(lightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speSwitch))
                    .addGroup(lightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mode)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lightPanelLayout.setVerticalGroup(
            lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lightPanelLayout.createSequentialGroup()
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(mode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(ambSwitch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ambSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ambVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(diffSwitch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diffSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(speVal))
                .addGap(18, 18, 18)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speSwitch)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(speSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diffVal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Vybraný objekt");

        jLabel6.setText("Typ");

        itemType.setText("jLabel7");

        jLabel8.setText("Pozice");

        itemPosition.setText("jLabel9");

        jLabel10.setText("Tlumení světla");

        dimSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dimSliderStateChanged(evt);
            }
        });

        glosSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                glosSliderStateChanged(evt);
            }
        });

        jLabel11.setText("Odrazivost");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, dimSlider, org.jdesktop.beansbinding.ELProperty.create("${value/100}"), dimVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, glosSlider, org.jdesktop.beansbinding.ELProperty.create("${value/100}"), refVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        rotX.setText("Rot-X");
        rotX.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rotXItemStateChanged(evt);
            }
        });

        rotY.setText("Rot-Y");
        rotY.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rotYItemStateChanged(evt);
            }
        });
        rotY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotYActionPerformed(evt);
            }
        });

        rotZ.setText("Rot-Z");
        rotZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rotZItemStateChanged(evt);
            }
        });
        rotZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotZActionPerformed(evt);
            }
        });

        rotationValue.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.1f), Float.valueOf(-2.0f), Float.valueOf(2.0f), Float.valueOf(0.1f)));
        rotationValue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rotationValueStateChanged(evt);
            }
        });

        javax.swing.GroupLayout objectPanelLayout = new javax.swing.GroupLayout(objectPanel);
        objectPanel.setLayout(objectPanelLayout);
        objectPanelLayout.setHorizontalGroup(
            objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(objectPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(objectPanelLayout.createSequentialGroup()
                        .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(86, 86, 86)
                        .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemPosition)
                            .addComponent(itemType)))
                    .addGroup(objectPanelLayout.createSequentialGroup()
                        .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(dimSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dimVal))
                    .addGroup(objectPanelLayout.createSequentialGroup()
                        .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(glosSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refVal)))
                .addGap(252, 252, 252))
            .addGroup(objectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rotX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotZ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        objectPanelLayout.setVerticalGroup(
            objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(objectPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(itemType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(itemPosition))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dimSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dimVal))
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(glosSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(objectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotX)
                    .addComponent(rotY)
                    .addComponent(rotZ)
                    .addComponent(rotationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Barevné složky");

        redSlider.setMaximum(255);
        redSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                redSliderStateChanged(evt);
            }
        });

        jLabel13.setText("R");

        greenSlider.setMaximum(255);
        greenSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                greenSliderStateChanged(evt);
            }
        });

        jLabel14.setText("G");

        blueSlider.setMaximum(255);
        blueSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blueSliderStateChanged(evt);
            }
        });

        jLabel15.setText("B");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, redSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), redVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, blueSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), blueVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, greenSlider, org.jdesktop.beansbinding.ELProperty.create("${value}"), greenVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        colorMagnitude.setMajorTickSpacing(1);
        colorMagnitude.setMaximum(512);
        colorMagnitude.setToolTipText("");
        colorMagnitude.setValue(256);
        colorMagnitude.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                colorMagnitudeStateChanged(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, colorMagnitude, org.jdesktop.beansbinding.ELProperty.create("${value/256}"), distVal, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Síla světla");

        javax.swing.GroupLayout colorPanelLayout = new javax.swing.GroupLayout(colorPanel);
        colorPanel.setLayout(colorPanelLayout);
        colorPanelLayout.setHorizontalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(redVal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorPanelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(greenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(greenVal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, colorPanelLayout.createSequentialGroup()
                            .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(colorPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(blueVal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(colorPanelLayout.createSequentialGroup()
                                    .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5)
                                        .addComponent(colorMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(distVal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(24, 24, 24))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        colorPanelLayout.setVerticalGroup(
            colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanelLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(redSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(redVal))
                .addGap(18, 18, 18)
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(colorPanelLayout.createSequentialGroup()
                        .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(greenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blueSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(blueVal)))
                    .addComponent(greenVal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(colorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(distVal)
                    .addComponent(colorMagnitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(objectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(colorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(objectPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lightPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void ambSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ambSliderPropertyChange

    }//GEN-LAST:event_ambSliderPropertyChange

    private void ambSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ambSliderStateChanged
        float correctValue = ((float) ambSlider.getValue() / (float) 100);
        ShaderVariables.ambientModifier = correctValue;
    }//GEN-LAST:event_ambSliderStateChanged

    private void speSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speSliderStateChanged
        float correctValue = ((float) speSlider.getValue() / (float) 100);
        ShaderVariables.specularModifier = correctValue;
    }//GEN-LAST:event_speSliderStateChanged

    private void diffSwitchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_diffSwitchItemStateChanged
        ShaderVariables.diffuseSwitch = !ShaderVariables.diffuseSwitch;
    }//GEN-LAST:event_diffSwitchItemStateChanged

    private void speSwitchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_speSwitchItemStateChanged
        ShaderVariables.specularSwitch = !ShaderVariables.specularSwitch;
    }//GEN-LAST:event_speSwitchItemStateChanged

    private void diffSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_diffSliderStateChanged
        float correctValue = ((float) diffSlider.getValue() / (float) 100);
        ShaderVariables.diffuseModifier = correctValue;
    }//GEN-LAST:event_diffSliderStateChanged

    private void dimSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dimSliderStateChanged
        float correctValue = ((float) dimSlider.getValue() / (float) 100);
        if (currentSelectedEntity != null) {
            currentSelectedEntity.getModel().getTexture().setShineDamper(correctValue);
        }
    }//GEN-LAST:event_dimSliderStateChanged

    private void glosSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_glosSliderStateChanged
        float correctValue = ((float) glosSlider.getValue() / (float) 100);
        if (currentSelectedEntity != null) {
            currentSelectedEntity.getModel().getTexture().setReflectivity(correctValue);
        }
    }//GEN-LAST:event_glosSliderStateChanged

    private void ambSwitchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ambSwitchItemStateChanged
        ShaderVariables.ambientSwitch = !ShaderVariables.ambientSwitch;
    }//GEN-LAST:event_ambSwitchItemStateChanged

    private void colorMagnitudeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_colorMagnitudeStateChanged
        float correctValue = ((float) colorMagnitude.getValue() / 256);
        LightSource ls = (LightSource) currentSelectedEntity;
        
        ls.getLight().getColour().x = correctValue * redSlider.getValue();
        ls.getLight().getColour().y = correctValue * greenSlider.getValue();
        ls.getLight().getColour().z = correctValue * blueSlider.getValue();
    }//GEN-LAST:event_colorMagnitudeStateChanged

    private void redSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redSliderStateChanged
        float colorMag = ((float) colorMagnitude.getValue() / 256);
        LightSource ls = (LightSource) currentSelectedEntity;
        Light l = ls.getLight();
        l.getColour().x = colorMag * redSlider.getValue();
    }//GEN-LAST:event_redSliderStateChanged

    private void greenSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenSliderStateChanged
        float colorMag = ((float) colorMagnitude.getValue() / 256);
        LightSource ls = (LightSource) currentSelectedEntity;
        Light l = ls.getLight();
        l.getColour().y = colorMag * greenSlider.getValue();
    }//GEN-LAST:event_greenSliderStateChanged

    private void blueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blueSliderStateChanged
        float colorMag = ((float) colorMagnitude.getValue() / 256);
        LightSource ls = (LightSource) currentSelectedEntity;
        Light l = ls.getLight();
        l.getColour().z = colorMag * blueSlider.getValue();
    }//GEN-LAST:event_blueSliderStateChanged

    private void rotYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotYActionPerformed

    }//GEN-LAST:event_rotYActionPerformed

    private void rotZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotZActionPerformed

    }//GEN-LAST:event_rotZActionPerformed

    private void rotXItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rotXItemStateChanged
        if (rotX.isSelected()) {
            currentSelectedEntity.setRotModifierX(0.1f);
        } else {
            currentSelectedEntity.setRotModifierX(0.0f);
        }
    }//GEN-LAST:event_rotXItemStateChanged

    private void rotYItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rotYItemStateChanged
        if (rotY.isSelected()) {
            currentSelectedEntity.setRotModifierY(0.1f);
        } else {
            currentSelectedEntity.setRotModifierY(0.0f);
        }
    }//GEN-LAST:event_rotYItemStateChanged

    private void rotZItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rotZItemStateChanged
        if (rotZ.isSelected()) {
            currentSelectedEntity.setRotModifierZ(0.1f);
        } else {
            currentSelectedEntity.setRotModifierZ(0.0f);
        }
    }//GEN-LAST:event_rotZItemStateChanged

    private void rotationValueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rotationValueStateChanged
        if (rotZ.isSelected()) {
            currentSelectedEntity.setRotModifierZ((float)rotationValue.getValue());
        }
        if (rotY.isSelected()) {
            currentSelectedEntity.setRotModifierY((float)rotationValue.getValue());
        }
        if (rotX.isSelected()) {
            currentSelectedEntity.setRotModifierX((float)rotationValue.getValue());
        }
    }//GEN-LAST:event_rotationValueStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider ambSlider;
    private javax.swing.JCheckBox ambSwitch;
    private javax.swing.JLabel ambVal;
    private javax.swing.JSlider blueSlider;
    private javax.swing.JLabel blueVal;
    private javax.swing.JSlider colorMagnitude;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JSlider diffSlider;
    private javax.swing.JCheckBox diffSwitch;
    private javax.swing.JLabel diffVal;
    private javax.swing.JSlider dimSlider;
    private javax.swing.JLabel dimVal;
    private javax.swing.JLabel distVal;
    private javax.swing.JSlider glosSlider;
    private javax.swing.JSlider greenSlider;
    private javax.swing.JLabel greenVal;
    private javax.swing.JLabel itemPosition;
    private javax.swing.JLabel itemType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel lightPanel;
    private javax.swing.JLabel mode;
    private javax.swing.JPanel objectPanel;
    private javax.swing.JSlider redSlider;
    private javax.swing.JLabel redVal;
    private javax.swing.JLabel refVal;
    private javax.swing.JCheckBox rotX;
    private javax.swing.JCheckBox rotY;
    private javax.swing.JCheckBox rotZ;
    private javax.swing.JSpinner rotationValue;
    private javax.swing.JSlider speSlider;
    private javax.swing.JCheckBox speSwitch;
    private javax.swing.JLabel speVal;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        this.currentSelectedEntity = EntityObserver.selected;
        manageComponentVisibility();
        
        if (objectPanel.isVisible()) {
            setType();
            setPosition();
            setEntityDampening();
            setEntityReflection();
            setEntityRotation();
            
            if (colorPanel.isVisible()) {
                manageLights(EntityObserver.selected);
            }
        }
    }
    
    private void setEntityRotation() {
        if (currentSelectedEntity.getRotModifierX() != 0) {
            rotX.setSelected(true);
            rotationValue.setValue(currentSelectedEntity.getRotModifierX());
        } else {
            rotX.setSelected(false);
        }
        if (currentSelectedEntity.getRotModifierY() != 0) {
            rotY.setSelected(true);
            rotationValue.setValue(currentSelectedEntity.getRotModifierY());
        } else {
            rotY.setSelected(false);
        }
        if (currentSelectedEntity.getRotModifierZ() != 0) {
            rotZ.setSelected(true);
            rotationValue.setValue(currentSelectedEntity.getRotModifierZ());
        } else {
            rotZ.setSelected(false);
        }
    }
}
