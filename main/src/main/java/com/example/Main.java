package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {
    public  static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.todociber.hesidohackeado.bd");
        schema.enableKeepSectionsByDefault();
        createDataBase(schema);
        DaoGenerator generador = new DaoGenerator();
        generador.generateAll(schema,args[0]);
    }


    private static void createDataBase(Schema schema) {

        Entity Correos = schema.addEntity("Correos");
        Correos.addIdProperty();
        Correos.addStringProperty("email");


        Entity Hackeado = schema.addEntity("Hackeado");
        Hackeado.addIdProperty();
        Hackeado.addStringProperty("correo");
        Hackeado.addStringProperty("title");
        Hackeado.addStringProperty("author");
        Hackeado.addStringProperty("is_vrf");
        Hackeado.addStringProperty("date_created");
        Hackeado.addStringProperty("date_leaked");
        Hackeado.addStringProperty("emails_count");
        Hackeado.addStringProperty("details");
        Hackeado.addStringProperty("source_id");
        Hackeado.addStringProperty("source_url");
        Hackeado.addStringProperty("source_lines");
        Hackeado.addStringProperty("source_size");
        Hackeado.addStringProperty("source_network");
        Hackeado.addStringProperty("source_provider");


    }





}