/*
 * The MIT License
 *
 * Copyright 2019 bschorn.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.schorn.ella.ws;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.schorn.ella.app.ActiveMain;
import org.schorn.ella.node.MetaReader;
import org.schorn.ella.schema.ActiveSchemaParser;
import org.schorn.ella.util.CommandLineArgs;

/**
 *
 * @author bschorn
 */
public class Main {
    private final String[] args;
    private final String context;
    private final String specFile;
    private final String metaFile;
    private final boolean createMeta;
    private final boolean loadMeta;
    private final boolean useSpring;
    private ActiveMain.Starter starter;

    public Main(String[] args) {
        this.args = args;
        CommandLineArgs.init(args);
        this.context = CommandLineArgs.getParameterValue("App.Context");
        this.specFile = CommandLineArgs.getParameterValue("Spec.File");
        this.metaFile = CommandLineArgs.getParameterValue("Meta.File");
        this.createMeta = CommandLineArgs.hasParameterFlag("Create.Meta");
        this.loadMeta = CommandLineArgs.hasParameterFlag("Load.Meta");
        this.useSpring = CommandLineArgs.hasParameterFlag("Use.Spring");
    }

    public void createMeta() throws Exception {
        Path specFilePath = Paths.get(specFile);
        if (Files.exists(specFilePath)) {
            ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(this.context, specFilePath.toString());
            Path metaFilePath = Paths.get(metaFile);
            Files.write(metaFilePath, schemaParser.getMeta().getBytes());
        }
    }

    public void loadMeta() throws Exception {
        if (this.loadMeta) {
            Path specFilePath = Paths.get(specFile);
            if (Files.exists(specFilePath)) {
                ActiveSchemaParser schemaParser = ActiveSchemaParser.compile(this.context, specFilePath.toString());
                MetaReader.MetaSupplier metaSupplier = new MetaReader.StringMetaSupplier(schemaParser.getMeta());
                this.starter.get().addContext(this.context, metaSupplier);
            }
        }
    }

    public void start() throws Exception {
        if (this.createMeta) {
            this.createMeta();
        } else {
            this.starter = new ActiveMain.Starter(args).create();
            if (this.loadMeta) {
                this.loadMeta();
            }
            this.starter.start();

            if (this.useSpring) {
                new EllaWsApplication.Starter(args).create().start();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Main main = new Main(args);
            main.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
