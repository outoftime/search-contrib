/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brewster.hashinganalyzer;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

/**
 *
 * @author mat
 */
public class HashingAnalyzerPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "hashing-analyzer";
    }

    @Override
    public String description() {
        return "TokenFilter to index fulltext using salted hashes";
    }
    
    @Override
    public void processModule(Module module) {
        if (module instanceof AnalysisModule) {
            final AnalysisModule analysisModule = (AnalysisModule) module;
            analysisModule.addProcessor(new HashingAnalysisBinderProcessor());
        }
    }
    
}
