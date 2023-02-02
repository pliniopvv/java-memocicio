/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.ram;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author GanGss
 */
public class ConfigMemory extends Properties {

    private String CONFIG_FILE_NAME = "config.ini";
    private static ConfigMemory instance;

    private ConfigMemory() {

    }

    public static ConfigMemory getInstance() {
        if (instance == null) {
            instance = new ConfigMemory();
            instance.load();
        }
        return instance;
    }

    public void save() {
        try {
            File f = new File(CONFIG_FILE_NAME);
            FileOutputStream fos = new FileOutputStream(f);
            super.store(fos, "ARQUIVO DE CONFIGURAÇÃO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void load() {
        File f = new File(CONFIG_FILE_NAME);
        if (!f.exists()) {
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(f);
            super.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(String prop, String val) {
        setProperty(prop, val);
        save();
    }

    public String get(String prop) {
        return getProperty(prop);
    }
}
